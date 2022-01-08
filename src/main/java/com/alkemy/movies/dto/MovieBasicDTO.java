package com.alkemy.movies.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieBasicDTO {
    private Long id;
    private String titulo;
    private String imagen;
    private String fechaCreacion;
}
