package com.project.app.services.business;

import java.util.List;

public interface GenericService<T> {

    public abstract List<T> findAll();

    public abstract T findById(Long id);

    public abstract T update(T updatedEntry);

}