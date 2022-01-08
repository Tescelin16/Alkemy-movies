package com.alkemy.movies.service.impl;

import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.dto.MovieBasicDTO;
import com.alkemy.movies.dto.MovieDTO;
import com.alkemy.movies.entity.Movie;
import com.alkemy.movies.exception.ParamNotFound;
import com.alkemy.movies.mapper.MovieMapper;
import com.alkemy.movies.repository.CharacterRepository;
import com.alkemy.movies.repository.MovieRepository;
import com.alkemy.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<MovieBasicDTO> getAll() {
        List<Movie> movies= movieRepository.findAll();
        List<MovieBasicDTO> dtos= movieMapper.movieList2MovieBasicDTOList(movies);
        return dtos;
    }

    @Override
    public MovieDTO save(MovieDTO dto){
        Movie movie= movieMapper.movieDTO2MovieEntity(dto);
        var characters= characterRepository.findByIdIn(dto.getCharacters().stream()
                .map(CharacterDTO::getId).collect(Collectors.toSet()));
        movie.setCharacters(characters);
        Movie movieSaved= movieRepository.save(movie);
        MovieDTO movieDTO=movieMapper.movieEntity2MovieDTO(movieSaved,true);
        return movieDTO;
    }

    @Override
    public MovieDTO update(Long id, MovieDTO dto) {
        Optional<Movie> entity= this.movieRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id movie no válido.");
        }
        this.movieMapper.movieEntityRefreshValue(entity.get(), dto);
        Movie movieSaved= this.movieRepository.save(entity.get());
        MovieDTO result= this.movieMapper.movieEntity2MovieDTO(movieSaved,true);
        return result;
    }
}
