package com.project.app.services.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.app.services.entities.StopJPA;

/**
 * Repository interface for StopJPA entities.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "stop", path = "stop")
public interface StopRepository extends BaseRepository<StopJPA, Long> {

    StopJPA findById(@Param("id") Long id);

    StopJPA findByIdStop(@Param("idStop") String idStop);

    @Override
    void delete(StopJPA deleted);

    @Override
    List<StopJPA> findAll();

    // Optional<StopJPA> findOne(Long id);
    @Override
    StopJPA findOne(Long id);

    @Override
    StopJPA save(StopJPA persisted);


}