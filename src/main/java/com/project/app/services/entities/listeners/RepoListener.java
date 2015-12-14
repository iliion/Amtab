package com.project.app.services.entities.listeners;

import java.io.IOException;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.app.config.ApplicationContextProvider;
import com.project.app.model.StopPosition;
import com.project.app.services.PopulateDbService;
import com.project.app.services.entities.StopJPA;

@Service
public class RepoListener {

    @Autowired
    PopulateDbService service;

    @PrePersist
    @PreUpdate
    void onPrePersist(StopJPA stopJPA) throws IOException {

        ApplicationContextProvider.autowire(this, this.service);
        StopPosition[] stopPositionArray = service.getPositionOfBusStops();
        stopJPA.setIdStop(stopPositionArray[0].getIdFermata());
        stopJPA.setStopDescription(stopPositionArray[0].getDescrizioneFermata());
    }
}
