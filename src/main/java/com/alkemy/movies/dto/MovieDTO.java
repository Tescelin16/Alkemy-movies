package com.alkemy.movies.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String titulo;
    private String imagen;
    private String fechaCreacion;
    private int calificacion;
    private GenreDTO genreDTO;
    private List<CharacterDTO> characters;
}
