package com.alkemy.movies.service.impl;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.entity.Character;
import com.alkemy.movies.mapper.CharacterMapper;
import com.alkemy.movies.repository.CharacterRepository;
import com.alkemy.movies.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;

    @Override
    public List<CharacterBasicDTO> getAll(){
        List<Character> characters= characterRepository.findAll();
        List<CharacterBasicDTO> characterBasicDTOS= characterMapper.characterSet2BasicDTOList(characters);
        return characterBasicDTOS;
    }

    @Override
    public CharacterDTO save(CharacterDTO dto) {
        Character character=characterMapper.characterDTO2CharacterEntity(dto);
        Character entity=characterRepository.save(character);
        CharacterDTO characterDTO=characterMapper.characterEntity2characterDTO(entity);
        return characterDTO;
    }

}
