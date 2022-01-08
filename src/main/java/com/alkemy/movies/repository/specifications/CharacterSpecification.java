package com.alkemy.movies.repository.specifications;

import com.alkemy.movies.dto.CharacterFiltersDTO;
import com.alkemy.movies.entity.Character;
import com.alkemy.movies.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {
    public Specification<Character> getByFilters(CharacterFiltersDTO filtersDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates= new ArrayList<>();

            if(StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%"+filtersDTO.getName().toLowerCase()+"%"
                        )
                );
            }
            if(filtersDTO.getAge()>0){
                predicates.add(criteriaBuilder.equal(root.get("edad"),filtersDTO.getAge())
                );
            }
            if(!CollectionUtils.isEmpty(filtersDTO.getMovies())){
                Join<Movie,Character> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId= join.get("id");
                predicates.add(moviesId.in(filtersDTO.getMovies()));
            }
            query.distinct(true);

            String orderByField="nombre";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)):
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
