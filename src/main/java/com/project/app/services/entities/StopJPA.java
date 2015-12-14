package com.project.app.services.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "stop")
// @EntityListeners(RepoListener.class)
public class StopJPA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_s")
    private Long id;

    @NotBlank
    @Column(name = "id_stop", nullable = false)
    private String idStop;

    @Column(name = "stop_description")
    private String stopDescription;

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_position")
    private PositionJPA stopPosition;

    // @ManyToMany
    // @JoinTable(name = "stop_line", joinColumns = @JoinColumn(name = "stop_id"),
    // inverseJoinColumns = @JoinColumn(name = "line_id"))
    // private Collection<LineJPA> lines;

    @ManyToMany
    @JoinTable(name = "stop_timetable", joinColumns = @JoinColumn(name = "stop_id"), inverseJoinColumns = @JoinColumn(name = "timetable_id"))
    private Collection<TimeTableJPA> stopTimetables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public PositionJPA getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(PositionJPA stopPosition) {
        this.stopPosition = stopPosition;
    }

    // public Collection<LineJPA> getLines() {
    // return lines;
    // }
    //
    // public void setLines(Collection<LineJPA> lines) {
    // this.lines = lines;
    // }

    public Collection<TimeTableJPA> getStopTimetables() {
        return stopTimetables;
    }

    public void setStopTimetables(Collection<TimeTableJPA> stopTimetables) {
        this.stopTimetables = stopTimetables;
    }

    /** Helper Methods **/

    public void update(String stopDescription, String idStop) {
        this.stopDescription = stopDescription;
        this.idStop = idStop;
    }

    public void update(String stopDescription, String idStop, PositionJPA positionJPA) {
        this.stopDescription = stopDescription;
        this.idStop = idStop;
        this.stopPosition = positionJPA;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("FERMATA: ").append(this.idStop).append("-DESCRIZIONE: ")
                .append(this.stopDescription).append("\nLAT: ").append(this.stopPosition.getLat())
                .append("\nLON: ").append(this.stopPosition.getLon()).toString();
    }
}
