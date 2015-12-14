package com.project.app.model.dto;

public class BusDTO {

    private Long id;

    private int idBus;

    public BusDTO() {
    }

    public BusDTO(int idBus) {
        this.idBus = idBus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

}
