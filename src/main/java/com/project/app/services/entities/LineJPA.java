package com.project.app.services.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "line")
public class LineJPA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_l")
    private Long id;

    @NotBlank
    @Column(name = "id_line")
    private String idLine;

    @Column(name = "Line_Description")
    private String lineDescription;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lineJPA")
    private Collection<TimeTableJPA> timeTables;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lineJPA")
    private Collection<BusJPA> busJPA;

    public LineJPA() {
    }

    public LineJPA(String idLine, String lineDescription) {
        this.idLine = idLine;
        this.lineDescription = lineDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Collection<TimeTableJPA> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(Collection<TimeTableJPA> timeTables) {
        this.timeTables = timeTables;
    }

    public Collection<BusJPA> getBusJPA() {
        return busJPA;
    }

    public void setBusJPA(Collection<BusJPA> busJPA) {
        this.busJPA = busJPA;
    }

    /** Helper Methods **/

    public void update(String idLine, String lineDescription) {
        this.idLine = idLine;
        this.lineDescription = lineDescription;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("ID Linea: ").append(this.idLine)
                .append("-DESCRIZIONE: ").append(this.lineDescription).toString();
    }

}
