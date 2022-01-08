package com.alkemy.movies.mapper;

import com.alkemy.movies.dto.CharacterBasicDTO;
import com.alkemy.movies.dto.CharacterDTO;
import com.alkemy.movies.dto.MovieDTO;
import com.alkemy.movies.entity.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterDTO characterEntity2characterDTO(Character entity, boolean loadMovies){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        dto.setEdad(entity.getEdad());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());
        if(loadMovies){
            List<MovieDTO> movieDTOS= this.movieMapper.movieList2MovieDTOList(entity.getMovies(),false);
            dto.setMovies(movieDTOS);
        }
        return dto;
    }

    public Character characterDTO2CharacterEntity(CharacterDTO dto){
        Character entity=new Character();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setHistoria(dto.getHistoria());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());

        return entity;
    }

    public void characterEntityRefreshValue( Character entity, CharacterDTO dto){
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setHistoria(dto.getHistoria());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
    }

    public List<CharacterDTO> characterEntitySet2DTOList (Collection<Character> entities, boolean loadPaises){
        List<CharacterDTO> dtos= new ArrayList<>();
        for (Character entity:entities) {
            dtos.add(this.characterEntity2characterDTO(entity,loadPaises));
        }
        return dtos;
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
