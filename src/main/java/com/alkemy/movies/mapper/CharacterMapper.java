package com.alkemy.movies.mapper;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.entity.Character;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterDTO characterEntity2characterDTO(Character entity){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        dto.setEdad(entity.getEdad());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());

        return dto;
    }

    public Character characterDTO2CharacterEntity(CharacterDTO dto){
        Character entity=new Character();
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setHistoria(dto.getHistoria());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());

        return entity;
    }

    public List<CharacterBasicDTO> characterSet2BasicDTOList(Collection<Character> characters){
        List<CharacterBasicDTO> dtoList=new ArrayList<>();
        CharacterBasicDTO characterBasicDTO;
        for (Character entity:characters) {
            characterBasicDTO=new CharacterBasicDTO();
            characterBasicDTO.setId(entity.getId());
            characterBasicDTO.setNombre(entity.getNombre());
            characterBasicDTO.setImagen(entity.getImagen());
            dtoList.add(characterBasicDTO);
        }
        return dtoList;
    }
}
