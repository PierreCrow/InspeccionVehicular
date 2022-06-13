package com.futuromovil.inspeccionvehicular.interactor.inspection;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.domain.repository.InspectionRepository;

public class InspectionInteractor {

    private final InspectionRepository inspectionRepository;

    public InspectionInteractor(InspectionRepository inspectionRepository) {
        this.inspectionRepository = inspectionRepository;
    }

    public void listActivitiesByIdVehicle(String idVehicle,ListActivitiesByIdVehicleCallback listActivitiesByIdVehicleCallback) {
        inspectionRepository.listActivitiesByIdVehicle(idVehicle,listActivitiesByIdVehicleCallback);
    }

    public void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RegisterVehicleFrequencyCallback registerVehicleFrequencyCallback) {
        inspectionRepository.registerVehicleFrequency(wsGeneralInfo,registerVehicleFrequencyCallback);
    }


}
