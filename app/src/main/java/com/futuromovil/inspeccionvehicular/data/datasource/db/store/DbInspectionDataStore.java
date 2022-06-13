package com.futuromovil.inspeccionvehicular.data.datasource.db.store;

import android.content.Context;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.InspectionDataStore;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;


public class DbInspectionDataStore implements InspectionDataStore {

    public DbInspectionDataStore(Context context) {

    }

    @Override
    public void listActivitiesByIdVehicle(String idVehicle, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RepositoryCallback repositoryCallback) {

    }
}
