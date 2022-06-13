package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;

import java.util.List;

public interface ListCitiesCallback {

    void onListCitiesSuccess(List<WsCity> wsCities);

    void onListCitiesError(String message);
}
