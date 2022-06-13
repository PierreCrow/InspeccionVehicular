package com.futuromovil.inspeccionvehicular.data.datasource.datastore;


import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;


public interface InspectionDataStore {

    void listActivitiesByIdVehicle(String idVehicle,RepositoryCallback repositoryCallback);

    void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RepositoryCallback repositoryCallback);

}
