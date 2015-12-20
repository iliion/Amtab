package com.project.app.services.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.app.services.entities.LineJPA;

/**
 * Repository interface for StopJPA entities.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "line", path = "line")
public interface LineRepository extends BaseRepository<LineJPA, Long> {

    LineJPA findById(@Param("id") Long id);

    LineJPA findByIdLine(@Param("idLine") String idLine);

    @Override
    void delete(LineJPA deleted);

    @Override
    List<LineJPA> findAll();

    // Optional<StopJPA> findOne(Long id);
    @Override
    LineJPA findOne(Long id);

    @Override
    LineJPA save(LineJPA persisted);


}