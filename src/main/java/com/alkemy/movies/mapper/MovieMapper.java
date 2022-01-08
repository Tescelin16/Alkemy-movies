package com.alkemy.movies.mapper;

import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.dto.MovieBasicDTO;
import com.alkemy.movies.dto.MovieDTO;
import com.alkemy.movies.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private CharacterMapper characterMapper;

    public MovieDTO movieEntity2MovieDTO(Movie entity, boolean loadCharacters){
        MovieDTO movieDTO= new MovieDTO();
        movieDTO.setId(entity.getId());
        movieDTO.setImagen(entity.getImagen());
        movieDTO.setTitulo(entity.getTitulo());
        movieDTO.setFechaCreacion(entity.getFechaCreacion().toString());
        movieDTO.setCalificacion(entity.getCalificacion());
        movieDTO.setGenreDTO(this.genreMapper.genreEntity2GenreDTO(entity.getGenre()));
        if(loadCharacters){
            List<CharacterDTO> characterDTOS=this.characterMapper.characterEntitySet2DTOList(entity.getCharacters(),false);
            movieDTO.setCharacters(characterDTOS);
        }
        return movieDTO;
    }

    public MovieBasicDTO movieEntity2BasicDTO(Movie entity){
        MovieBasicDTO basicDTO= new MovieBasicDTO();
        basicDTO.setId(entity.getId());
        basicDTO.setImagen(entity.getImagen());
        basicDTO.setTitulo(entity.getTitulo());
        basicDTO.setFechaCreacion(entity.getFechaCreacion().toString());
        return basicDTO;
    }

    public Movie movieDTO2MovieEntity (MovieDTO dto){
        Movie entity= new Movie();
        entity.setId(dto.getId());
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGenre(genreMapper.genreDTO2GenreEntity(dto.getGenreDTO()));
        return entity;
    }

    public void movieEntityRefreshValue(Movie entity, MovieDTO movieDTO){
        entity.setId(movieDTO.getId());
        entity.setTitulo(movieDTO.getTitulo());
        entity.setImagen(movieDTO.getImagen());
        entity.setCalificacion(movieDTO.getCalificacion());
        entity.setFechaCreacion(this.string2LocalDate(movieDTO.getFechaCreacion()));
       /* entity.setCharacters(movieDTO.getCharacters());
        entity.setGenre(movieDTO.getGenreDTO());*/
    }

    private LocalDate string2LocalDate (String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate,formatter);
        return date;
    }

    public List<MovieBasicDTO> movieList2MovieBasicDTOList(List<Movie> movies){
        List<MovieBasicDTO> basicDTOS= new ArrayList<>();
        for (Movie entity:movies) {
            basicDTOS.add(this.movieEntity2BasicDTO(entity));
        }
        return basicDTOS;
    }

    public List<MovieDTO> movieList2MovieDTOList (List<Movie> movies, boolean loadCharacters){
        List<MovieDTO> moviesDTO = new ArrayList<>();
        for (Movie movie:movies) {
            moviesDTO.add(this.movieEntity2MovieDTO(movie,loadCharacters));
        }
        return moviesDTO;
    }
}
