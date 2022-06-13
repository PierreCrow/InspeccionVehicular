package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;

import java.util.List;

public interface ListVehiclesCallback {

    void onListVehiclesSuccess(List<WsVehicle> wsVehicles);

    void onListVehiclesError(String message);
}
