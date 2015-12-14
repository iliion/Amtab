package com.project.app.model.dto;

import com.project.app.model.objects.Position;

public class StopDTO {

    private Long id;

    private String idStop;

    private String stopDescription;

    private Position stopPosition;

    public StopDTO() {
    }

    public String getStopDescription() {
        return stopDescription;
    }

    public void setStopDescription(String stopDescription) {
        this.stopDescription = stopDescription;
    }

    public String getIdStop() {
        return idStop;
    }

    public void setIdStop(String idStop) {
        this.idStop = idStop;
    }

    public Position getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(Position stopPosition) {
        this.stopPosition = stopPosition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
