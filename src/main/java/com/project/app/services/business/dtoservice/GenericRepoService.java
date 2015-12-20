package com.project.app.services.business.dtoservice;

import java.util.List;

public interface GenericRepoService<T> {

    /** From JPAs to DTOs **/

    public abstract T create(T newEntry);

    public abstract T delete(Long id);

    public abstract List<T> findAll();

    public abstract T findById(Long id);

    public abstract T update(T updatedEntry);

}