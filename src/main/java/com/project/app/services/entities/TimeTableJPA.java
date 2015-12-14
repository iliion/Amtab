package com.project.app.services.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "line")
public class TimeTableJPA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    // @Column(name = "idBus")
    // private int idBus;
    // @Column(name = "idStop")
    // private String idStop;

    @Column(name = "direction")
    private String direction;

    @Column(name = "orario")
    private String orario;

    @Column(name = "progression")
    private int progression;

    @OneToOne(mappedBy = "lineTimeTable")
    private LineJPA line;

    @OneToMany(mappedBy = "busTimetable")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BusJPA> buses;

    @ManyToMany(mappedBy = "stopTimetables")
    private Collection<StopJPA> stops;

    public TimeTableJPA() {
    }

    public TimeTableJPA(String direction, String orario, int progression) {
        this.direction = direction;
        this.orario = orario;
        this.progression = progression;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }

    public LineJPA getLine() {
        return line;
    }

    public void setLine(LineJPA line) {
        this.line = line;
    }

    public Collection<BusJPA> getBuses() {
        return buses;
    }

    public void setBuses(Collection<BusJPA> buses) {
        this.buses = buses;
    }

    public Collection<StopJPA> getStops() {
        return stops;
    }

    public void setStops(Collection<StopJPA> stops) {
        this.stops = stops;
    }

    /** Helper Methods **/

    public void update(String direction, String orario, int progression) {
        this.direction = direction;
        this.orario = orario;
        this.progression = progression;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("ID Linea: ").append(this.direction).append("-ORARIO: ")
                .append(this.orario).append("-ORDER: ").append(this.progression).toString();
    }

}
