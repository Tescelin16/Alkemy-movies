package com.alkemy.movies.service.impl;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.dto.CharacterFiltersDTO;
import com.alkemy.movies.entity.Character;
import com.alkemy.movies.exception.ParamNotFound;
import com.alkemy.movies.mapper.CharacterMapper;
import com.alkemy.movies.repository.CharacterRepository;
import com.alkemy.movies.repository.specifications.CharacterSpecification;
import com.alkemy.movies.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterSpecification characterSpecification;

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
        CharacterDTO characterDTO=characterMapper.characterEntity2characterDTO(entity,true);
        return characterDTO;
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO dto) {
        Optional<Character> entity= characterRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id de personaje no válido");
        }
        this.characterMapper.characterEntityRefreshValue(entity.get(), dto);
        Character entitySaved= this.characterRepository.save(entity.get());
        CharacterDTO result= this.characterMapper.characterEntity2characterDTO(entitySaved,true);
        return result;
    }

    @Override
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public List<CharacterDTO> getByFilters(String name, int age, List<Long> movies, String order) {
        CharacterFiltersDTO filtersDTO= new CharacterFiltersDTO(name, age, movies, order);
        List<Character> entities= this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos=this.characterMapper.characterEntitySet2DTOList(entities,true);
        return dtos;
    }

}
