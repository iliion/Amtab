package com.project.app.services.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bus")
public class BusJPA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_b")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "id_bus")
    private int idBus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_line")
    private LineJPA lineJPA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_timetable")
    private TimeTableJPA busTimetable;

    public BusJPA() {
    }

    public BusJPA(int idBus) {
        this.idBus = idBus;
    }

    public int getIdBus() {
        return idBus;
    }

    public void setIdBus(int idBus) {
        this.idBus = idBus;
    }

    public TimeTableJPA getBusTimetable() {
        return busTimetable;
    }

    public void setBusTimetable(TimeTableJPA busTimetable) {
        this.busTimetable = busTimetable;
    }

    public LineJPA getLineJPA() {
        return lineJPA;
    }

    public void setLineJPA(LineJPA lineJPA) {
        this.lineJPA = lineJPA;
    }

    /** Helper Methods **/

    public void update(int idBus) {
        this.idBus = idBus;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("ID Corsa: ").append(this.idBus).toString();
    }

}
