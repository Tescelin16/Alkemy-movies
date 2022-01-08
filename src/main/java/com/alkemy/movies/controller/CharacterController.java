package com.alkemy.movies.controller;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.dto.MovieDTO;
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
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
    @RequestParam (required = false) String name,
    @RequestParam (required = false) int age,
    @RequestParam (required = false) List<Long> movies,
    @RequestParam (required = false, defaultValue = "ASC")  String order){
        List<CharacterDTO> characterDTOS=this.characterService.getByFilters (name,age,movies,order);
        return ResponseEntity.ok(characterDTOS);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterBasicDTO>> getAll(){
        List<CharacterBasicDTO> dtos= characterService.getAll();
        return ResponseEntity.ok().body(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto){
        CharacterDTO result= characterService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO){
        CharacterDTO dto= characterService.save(characterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
