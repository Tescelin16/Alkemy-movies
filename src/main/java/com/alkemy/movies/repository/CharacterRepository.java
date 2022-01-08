package com.alkemy.movies.repository;

import com.alkemy.movies.entity.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface CharacterRepository extends JpaRepository<Character,Long>, JpaSpecificationExecutor<Character> {
    List<Character> findAll(Specification<Character> specification);
    Set<Character> findByIdIn(Collection<Long> ids);
}
