package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;

import java.util.List;

public interface ListLocationCallback {

    void onListLocationsSuccess(List<WsLocation> wsLocations);

    void onListLocationsError(String message);
}
