package com.alkemy.movies.service;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {

    List<CharacterBasicDTO> getAll();

    CharacterDTO save(CharacterDTO dto);
}
