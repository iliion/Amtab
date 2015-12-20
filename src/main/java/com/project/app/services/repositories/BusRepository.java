package com.project.app.services.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.app.services.entities.BusJPA;

/**
 * Repository interface for StopJPA entities.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "bus", path = "bus")
public interface BusRepository extends BaseRepository<BusJPA, Long> {

    BusJPA findById(@Param("id") Long id);

    BusJPA findByIdBus(@Param("idBus") int idBus);

    @Override
    void delete(BusJPA deleted);

    @Override
    List<BusJPA> findAll();

    @Override
    BusJPA findOne(Long id);

    @Override
    BusJPA save(BusJPA persisted);

}