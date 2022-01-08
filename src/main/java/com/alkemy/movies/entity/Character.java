package com.alkemy.movies.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SQLDelete(sql= "UPDATE character SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
@Table(name = "Personajes")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private Long peso;
    private String historia;
    private boolean deleted=Boolean.FALSE;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<Movie> movies= new ArrayList<>();

}
