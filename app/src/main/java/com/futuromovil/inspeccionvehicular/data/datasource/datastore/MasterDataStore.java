package com.futuromovil.inspeccionvehicular.data.datasource.datastore;

import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;

public interface MasterDataStore {

    void listBrands(RepositoryCallback repositoryCallback);

    void listModels(RepositoryCallback repositoryCallback);

    void listVehicles(RepositoryCallback repositoryCallback);

    void listLocations(RepositoryCallback repositoryCallback);

    void listCities(RepositoryCallback repositoryCallback);

    void listVehiclesByModel(int idModel, RepositoryCallback repositoryCallback);

}
