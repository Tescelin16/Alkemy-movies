package com.alkemy.movies.service;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    List<CharacterBasicDTO> getAll();

    CharacterDTO save(CharacterDTO dto);

    CharacterDTO update(Long id, CharacterDTO dto);

    void delete(Long id);

    List<CharacterDTO> getByFilters (String name, int age, List<Long> movies, String order);
}
