package com.project.app.services.converters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericEntitiesConverter<T, E> {

    List<E> mapEntitiesIntoDTOs(Iterable<T> entities);

    E mapEntityIntoDTO(T entity);

    Page<E> mapEntityPageIntoDTOPage(Pageable pageRequest, Page<T> source);

}