package com.project.app.services.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.project.app.model.dto.BusDTO;
import com.project.app.model.dto.LineDTO;
import com.project.app.model.dto.PositionDTO;
import com.project.app.model.dto.StopDTO;
import com.project.app.services.entities.BusJPA;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.entities.PositionJPA;
import com.project.app.services.entities.StopJPA;

/**
 * This class is a converter class that is used to transform JPA objects into DTO objects.
 * @author Ilias Ioannou
 */

public class EntitiesConverter {

    public static List<StopDTO> mapStopEntitiesIntoDTOs(Iterable<StopJPA> entities) {
        List<StopDTO> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapStopEntityIntoDTO(e)));

        return dtos;
    }

    public static List<LineDTO> mapLineEntitiesIntoDTOs(Iterable<LineJPA> entities) {
        List<LineDTO> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapLineEntityIntoDTO(e)));

        return dtos;
    }

    public static List<BusDTO> mapBusEntitiesIntoDTOs(Iterable<BusJPA> entities) {
        List<BusDTO> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapBusEntityIntoDTO(e)));

        return dtos;
    }

    public static List<PositionDTO> mapPositionEntitiesIntoDTOs(Iterable<PositionJPA> entities) {
        List<PositionDTO> dtos = new ArrayList<>();

        entities.forEach(e -> dtos.add(mapPositionEntityIntoDTO(e)));

        return dtos;
    }

    /**
     **/

    public static StopDTO mapStopEntityIntoDTO(StopJPA entity) {
        StopDTO dto = new StopDTO();

        dto.setIdStop(entity.getIdStop());
        dto.setStopDescription(entity.getStopDescription());

        return dto;
    }

    public static LineDTO mapLineEntityIntoDTO(LineJPA entity) {
        LineDTO dto = new LineDTO();

        dto.setIdLine(entity.getIdLine());
        dto.setLineDescription(entity.getLineDescription());

        return dto;
    }

    public static BusDTO mapBusEntityIntoDTO(BusJPA entity) {
        BusDTO dto = new BusDTO();

        dto.setIdBus(entity.getIdBus());

        return dto;
    }

    public static PositionDTO mapPositionEntityIntoDTO(PositionJPA entity) {
        PositionDTO dto = new PositionDTO();

        dto.setIdPosition(entity.getId());
        dto.setLatitudine(entity.getLat());
        dto.setLongitudine(entity.getLon());

        return dto;
    }

    /**
     * Transforms {@code Page<ENTITY>} objects into {@code Page<DTO>} objects.
     * @param pageRequest The information of the requested page.
     * @param source The {@code Page<ENTITY>} object.
     * @return The created {@code Page<DTO>} object.
     */
    static Page<StopDTO> mapStopEntityPageIntoDTOPage(Pageable pageRequest, Page<StopJPA> source) {
        List<StopDTO> dtos = mapStopEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }

    static Page<LineDTO> mapLineEntityPageIntoDTOPage(Pageable pageRequest, Page<LineJPA> source) {
        List<LineDTO> dtos = mapLineEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }

    static Page<BusDTO> mapBusEntityPageIntoDTOPage(Pageable pageRequest, Page<BusJPA> source) {
        List<BusDTO> dtos = mapBusEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }

    static Page<PositionDTO> mapPositionEntityPageIntoDTOPage(Pageable pageRequest,
            Page<PositionJPA> source) {
        List<PositionDTO> dtos = mapPositionEntitiesIntoDTOs(source.getContent());
        return new PageImpl<>(dtos, pageRequest, source.getTotalElements());
    }

}
