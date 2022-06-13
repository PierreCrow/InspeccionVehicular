package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;

import java.util.List;

public interface ListModelsCallback {

    void onListModelsSuccess(List<WsModel> wsModels);

    void onListModelsError(String message);
}
