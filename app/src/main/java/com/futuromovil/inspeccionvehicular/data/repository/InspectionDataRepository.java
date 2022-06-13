package com.futuromovil.inspeccionvehicular.data.repository;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.InspectionDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.InspectionDataStoreFactory;
import com.futuromovil.inspeccionvehicular.domain.repository.InspectionRepository;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.interactor.inspection.ListActivitiesByIdVehicleCallback;
import com.futuromovil.inspeccionvehicular.interactor.inspection.RegisterVehicleFrequencyCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import java.util.List;

public class InspectionDataRepository implements InspectionRepository {

    private final InspectionDataStoreFactory inspectionDataStoreFactory;

    public InspectionDataRepository(InspectionDataStoreFactory inspectionDataStoreFactory) {
        this.inspectionDataStoreFactory = inspectionDataStoreFactory;
    }

    @Override
    public void listActivitiesByIdVehicle(String idVehicle, ListActivitiesByIdVehicleCallback listActivitiesByIdVehicleCallback) {
        final InspectionDataStore inspectionDataStore = inspectionDataStoreFactory.create(Constants.STORE.CLOUD);
        inspectionDataStore.listActivitiesByIdVehicle(idVehicle,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listActivitiesByIdVehicleCallback.onListctivitiesError(message);
            }
            @Override
            public void onSuccess(Object object) {
                WsGeneralInfo wsGeneralInfo = (WsGeneralInfo) object;
                listActivitiesByIdVehicleCallback.onListctivitiesSuccess(wsGeneralInfo);
            }
        });
    }

    @Override
    public void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RegisterVehicleFrequencyCallback registerVehicleFrequencyCallback) {

        final InspectionDataStore inspectionDataStore = inspectionDataStoreFactory.create(Constants.STORE.CLOUD);
        inspectionDataStore.registerVehicleFrequency(wsGeneralInfo,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                registerVehicleFrequencyCallback.onRegisterVehicleFrequencyError(message);
            }
            @Override
            public void onSuccess(Object object) {
                String mensaje = (String) object;
                registerVehicleFrequencyCallback.onRegisterVehicleFrequencySuccess(mensaje);
            }
        });
    }
}
