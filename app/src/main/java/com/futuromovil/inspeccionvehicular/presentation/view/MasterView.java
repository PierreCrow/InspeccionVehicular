package com.futuromovil.inspeccionvehicular.presentation.view;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;

import java.util.List;

public interface MasterView extends BaseView {

    void listBrandsSucces(List<WsBrand> wsBrands);

    void listModelsSucces(List<WsModel> wsModels);

    void listVehiclesSucces(List<WsVehicle> wsVehicles);

    void listLocationsSucces(List<WsLocation> wsLocations);

    void listCitiesSucces(List<WsCity> wsCities);

    void listVehiclesByModelSucces(List<WsVehicle> wsVehicles);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
