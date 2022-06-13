package com.futuromovil.inspeccionvehicular.domain.repository;

import com.futuromovil.inspeccionvehicular.interactor.master.ListBrandsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListCitiesCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListLocationCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListModelsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesByModelCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesCallback;

public interface MasterRepository {

    void listBrands(ListBrandsCallback listBrandsCallback);

    void listModels(ListModelsCallback listModelsCallback);

    void listVehicles(ListVehiclesCallback listVehiclesCallback);

    void listLocations(ListLocationCallback listLocationCallback);

    void listCities(ListCitiesCallback listCitiesCallback);

    void listVehiclesByModel(int idModel, ListVehiclesByModelCallback listVehiclesCallback);

}
