package com.futuromovil.inspeccionvehicular.presentation.presenter;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.MasterDataStoreFactory;
import com.futuromovil.inspeccionvehicular.data.repository.MasterDataRepository;
import com.futuromovil.inspeccionvehicular.domain.repository.MasterRepository;
import com.futuromovil.inspeccionvehicular.interactor.master.ListBrandsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListCitiesCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListLocationCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListModelsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesByModelCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.MasterInteractor;
import com.futuromovil.inspeccionvehicular.presentation.view.MasterView;

import java.util.List;

public class MasterPresenter
        implements Presenter<MasterView>,
        ListBrandsCallback, ListModelsCallback, ListVehiclesCallback,ListLocationCallback,ListCitiesCallback, ListVehiclesByModelCallback {

    private MasterView masterView;
    private MasterInteractor masterInteractor;


    public void listBrands() {
        masterInteractor.listBrands(this);
    }

    public void listModels() {
        masterInteractor.listModels(this);
    }

    public void listVehicles() {
        masterInteractor.listVehicles(this);
    }

    public void listLocations() {
        masterInteractor.listLocations(this);
    }

    public void listCities() {
        masterInteractor.listCities(this);
    }

    public void listVehiclesByModel(int idModel) {
        masterInteractor.listVehiclesByModel(idModel,this);
    }

    @Override
    public void addView(MasterView view) {
        this.masterView = view;
        MasterRepository requestRepository = new MasterDataRepository(new MasterDataStoreFactory(this.masterView.getContext()));
        masterInteractor = new MasterInteractor(requestRepository);
    }

    @Override
    public void removeView(MasterView view) {

    }

    @Override
    public void onListBrandsSuccess(List<WsBrand> wsBrands) {
        masterView.listBrandsSucces(wsBrands);
    }

    @Override
    public void onListBrandsError(String message) {
        masterView.showErrorMessage(message);
    }

    @Override
    public void onListModelsSuccess(List<WsModel> wsModels) {
        masterView.listModelsSucces(wsModels);
    }

    @Override
    public void onListModelsError(String message) {
        masterView.showErrorMessage(message);
    }

    @Override
    public void onListVehiclesSuccess(List<WsVehicle> wsVehicles) {
        masterView.listVehiclesSucces(wsVehicles);
    }

    @Override
    public void onListVehiclesError(String message) {
        masterView.showErrorMessage(message);
    }

    @Override
    public void onListCitiesSuccess(List<WsCity> wsCities) {
        masterView.listCitiesSucces(wsCities);
    }

    @Override
    public void onListCitiesError(String message) {
        masterView.showErrorMessage(message);
    }

    @Override
    public void onListLocationsSuccess(List<WsLocation> wsLocations) {
        masterView.listLocationsSucces(wsLocations);
    }

    @Override
    public void onListLocationsError(String message) {
        masterView.showErrorMessage(message);
    }

    @Override
    public void onListVehiclesByModelSuccess(List<WsVehicle> wsVehicles) {
        masterView.listVehiclesByModelSucces(wsVehicles);
    }

    @Override
    public void onListVehiclesByModelError(String message) {
        masterView.showErrorMessage(message);
    }
}
