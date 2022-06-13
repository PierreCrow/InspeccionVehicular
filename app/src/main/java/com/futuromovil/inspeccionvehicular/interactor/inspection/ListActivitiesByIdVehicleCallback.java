package com.futuromovil.inspeccionvehicular.interactor.inspection;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsActivity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;

import java.util.List;

public interface ListActivitiesByIdVehicleCallback {

    void onListctivitiesSuccess(WsGeneralInfo wsGeneralInfo);

    void onListctivitiesError(String message);
}
