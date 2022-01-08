package com.alkemy.movies.service;

import com.alkemy.movies.dto.MovieBasicDTO;
import com.alkemy.movies.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieBasicDTO> getAll();

    MovieDTO save(MovieDTO dto);

    MovieDTO update (Long id, MovieDTO dto);
}
