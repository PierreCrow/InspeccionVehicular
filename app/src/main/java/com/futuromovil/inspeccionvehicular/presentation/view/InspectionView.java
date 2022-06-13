package com.futuromovil.inspeccionvehicular.presentation.view;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;

import java.util.List;

public interface InspectionView extends BaseView {

    void listActivitiesSuccess(WsGeneralInfo wsGeneralInfo);

    void registerVehicleFrequencySuccess(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
