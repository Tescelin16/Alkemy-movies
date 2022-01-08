package com.alkemy.movies.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;

    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;
    private int calificacion;

    @ManyToOne(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name="genre_id", insertable = false, updatable = false)
    private Genre genre;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name="character_movie",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> characters;

    @Override
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(getClass()!= obj.getClass())
            return false;
        final Movie other= (Movie) obj;
        return other.id == this.id;
    }
}
