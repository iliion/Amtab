package com.project.app.services.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position")
public class PositionJPA implements Serializable {

    /**
    *
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_position")
    private Long idPosition;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lon")
    private String lon;

    @OneToOne(mappedBy = "stopPosition")
    private StopJPA stopJPA;

    public PositionJPA() {
    }

    public PositionJPA(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public StopJPA getStopJPA() {
        return stopJPA;
    }

    public void setStopJPA(StopJPA stopJPA) {
        this.stopJPA = stopJPA;
    }

    public Long getId() {
        return idPosition;
    }

    public void setId(Long idPosition) {
        this.idPosition = idPosition;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    /** Helper Methods **/

    public void update(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("LON: ").append(this.lon).append("-LAN: ")
                .append(this.lat).toString();
    }

}
