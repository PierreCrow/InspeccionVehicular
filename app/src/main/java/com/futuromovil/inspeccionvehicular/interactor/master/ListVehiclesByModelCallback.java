package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;

import java.util.List;

public interface ListVehiclesByModelCallback {

    void onListVehiclesByModelSuccess(List<WsVehicle> wsVehicles);

    void onListVehiclesByModelError(String message);
}
