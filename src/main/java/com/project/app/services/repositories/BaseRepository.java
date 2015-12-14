package com.project.app.services.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    void delete(T deleted);

    List<T> findAll();

    // Optional<T> findOne(ID id);
    T findOne(ID id);

    T save(T persisted);
}