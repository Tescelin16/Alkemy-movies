package com.alkemy.movies.mapper;

import com.alkemy.movies.dto.GenreDTO;
import com.alkemy.movies.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public Genre genreDTO2GenreEntity(GenreDTO dto){
        Genre entity= new Genre();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        return entity;
    }

    public GenreDTO genreEntity2GenreDTO(Genre entity){
       GenreDTO dto= new GenreDTO();
       dto.setId(entity.getId());
       dto.setNombre(entity.getNombre());
       dto.setImagen(entity.getImagen());
       return dto;
    }
}
