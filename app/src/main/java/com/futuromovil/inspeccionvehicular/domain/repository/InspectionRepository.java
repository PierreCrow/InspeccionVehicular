package com.futuromovil.inspeccionvehicular.domain.repository;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.interactor.inspection.ListActivitiesByIdVehicleCallback;
import com.futuromovil.inspeccionvehicular.interactor.inspection.RegisterVehicleFrequencyCallback;

public interface InspectionRepository {

    void listActivitiesByIdVehicle(String idVehicle, ListActivitiesByIdVehicleCallback listActivitiesByIdVehicleCallback);

    void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RegisterVehicleFrequencyCallback registerVehicleFrequencyCallback);


}
