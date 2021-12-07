package com.alkemy.movies.controller;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<CharacterBasicDTO>> getAll(){
        List<CharacterBasicDTO> dtos= characterService.getAll();
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO){
        CharacterDTO dto= characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
