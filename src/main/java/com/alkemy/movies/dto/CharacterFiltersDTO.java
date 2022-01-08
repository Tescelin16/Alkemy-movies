package com.alkemy.movies.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacterFiltersDTO {

    private String name;
    private int age;
    private List<Long> movies;
    private String order;

    public CharacterFiltersDTO(String name, int age, List<Long> movies, String order){
        this.name=name;
        this.age=age;
        this.movies=movies;
        this.order=order;
    }

    public boolean isASC(){ return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC")==0;}
}
