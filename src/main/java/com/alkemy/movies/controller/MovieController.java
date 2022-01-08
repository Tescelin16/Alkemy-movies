package com.alkemy.movies.controller;

import com.alkemy.movies.dto.MovieBasicDTO;
import com.alkemy.movies.dto.MovieDTO;
import com.alkemy.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getAll(){
        List<MovieBasicDTO> movieBasicDTOS= movieService.getAll();
        return ResponseEntity.ok().body(movieBasicDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto){
        MovieDTO result= movieService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO){
        MovieDTO dto= movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }




}
