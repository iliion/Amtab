package com.project.app.services.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.app.services.entities.PositionJPA;

/**
 * Repository interface for PositionJPA entities.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "position", path = "position")
public interface PositionRepository extends BaseRepository<PositionJPA, Long> {

    PositionJPA findByIdPosition(@Param("idPosition") Long idPosition);

    @Override
    void delete(PositionJPA deleted);

    @Override
    List<PositionJPA> findAll();

    // Optional<PositionJPA> findOne(Long id);
    @Override
    PositionJPA findOne(Long idPosition);

    @Override
    PositionJPA save(PositionJPA persisted);


}