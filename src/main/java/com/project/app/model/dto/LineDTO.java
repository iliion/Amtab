package com.project.app.model.dto;

public class LineDTO {

    private Long id;

    private String idLine;

    private String lineDescription;

    public String getIdLine() {
        return idLine;
    }

    public void setIdLine(String idLine) {
        this.idLine = idLine;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
