package com.futuromovil.inspeccionvehicular.interactor.master;

import com.futuromovil.inspeccionvehicular.domain.repository.MasterRepository;


public class MasterInteractor {

    private final MasterRepository masterRepository;

    public MasterInteractor(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public void listBrands(ListBrandsCallback listBrandsCallback) {
        masterRepository.listBrands(listBrandsCallback);
    }

    public void listModels(ListModelsCallback listModelsCallback) {
        masterRepository.listModels(listModelsCallback);
    }

    public void listVehicles(ListVehiclesCallback listVehiclesCallback) {
        masterRepository.listVehicles(listVehiclesCallback);
    }

    public void listLocations(ListLocationCallback listLocationCallback) {
        masterRepository.listLocations(listLocationCallback);
    }

    public void listCities(ListCitiesCallback listCitiesCallback) {
        masterRepository.listCities(listCitiesCallback);
    }

    public void listVehiclesByModel(int idModel,ListVehiclesByModelCallback listVehiclesCallback) {
        masterRepository.listVehiclesByModel(idModel,listVehiclesCallback);
    }


}
