package com.alkemy.movies.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private Long peso;
    private String historia;
    private List<MovieDTO> movies;
}
