package com.alkemy.movies.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private Long peso;
    private String historia;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Movie> movies= new ArrayList<>();

}
