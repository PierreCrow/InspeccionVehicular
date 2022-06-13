package com.futuromovil.inspeccionvehicular.presentation.presenter;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.InspectionDataStoreFactory;
import com.futuromovil.inspeccionvehicular.data.repository.InspectionDataRepository;
import com.futuromovil.inspeccionvehicular.domain.repository.InspectionRepository;
import com.futuromovil.inspeccionvehicular.interactor.inspection.InspectionInteractor;
import com.futuromovil.inspeccionvehicular.interactor.inspection.ListActivitiesByIdVehicleCallback;
import com.futuromovil.inspeccionvehicular.interactor.inspection.RegisterVehicleFrequencyCallback;
import com.futuromovil.inspeccionvehicular.presentation.view.InspectionView;

import java.util.List;

public class InspectionPresenter implements Presenter<InspectionView>,
        ListActivitiesByIdVehicleCallback,RegisterVehicleFrequencyCallback {

    private InspectionView inspectionView;
    private InspectionInteractor inspectionInteractor;

    public void listActivitiesByIdVehicle(String idVehicle) {
        inspectionInteractor.listActivitiesByIdVehicle(idVehicle,this);
    }

    public void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo) {
        inspectionInteractor.registerVehicleFrequency(wsGeneralInfo,this);
    }

    @Override
    public void addView(InspectionView view) {
        this.inspectionView = view;
        InspectionRepository requestRepository = new InspectionDataRepository(new InspectionDataStoreFactory(this.inspectionView.getContext()));
        inspectionInteractor = new InspectionInteractor(requestRepository);
    }

    @Override
    public void removeView(InspectionView view) {
    }


    @Override
    public void onListctivitiesSuccess(WsGeneralInfo wsGeneralInfo) {
        inspectionView.listActivitiesSuccess(wsGeneralInfo);
    }

    @Override
    public void onListctivitiesError(String message) {
        inspectionView.showErrorMessage(message);
    }

    @Override
    public void onRegisterVehicleFrequencySuccess(String message) {
        inspectionView.registerVehicleFrequencySuccess(message);
    }

    @Override
    public void onRegisterVehicleFrequencyError(String message) {
        inspectionView.showErrorMessage(message);
    }
}
