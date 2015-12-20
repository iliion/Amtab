package com.project.app.services.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.project.app.services.entities.TimeTableJPA;

/**
 * Repository interface for StopJPA entities.
 */

@Repository
@RepositoryRestResource(collectionResourceRel = "timetable", path = "timetable")
public interface TimeTableRepository extends BaseRepository<TimeTableJPA, Long> {

    TimeTableJPA findByIdTimetable(@Param("idTimetable") Long idTimetable);

    List<TimeTableJPA> findByIdLine(@Param("IdLine") String idLine);
    
    List<TimeTableJPA> findByIdStop(@Param("IdStop") String idStop);
    
    List<TimeTableJPA> findByIdBus(@Param("IdBus") String idBus);
    
    @Override
    void delete(TimeTableJPA deleted);

    @Override
    List<TimeTableJPA> findAll();

    @Override
    TimeTableJPA findOne(Long id);

    @Override
    TimeTableJPA save(TimeTableJPA persisted);

}