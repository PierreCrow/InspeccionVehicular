package com.futuromovil.inspeccionvehicular.data.repository;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.MasterDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.MasterDataStoreFactory;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.domain.repository.MasterRepository;
import com.futuromovil.inspeccionvehicular.interactor.master.ListBrandsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListCitiesCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListLocationCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListModelsCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesByModelCallback;
import com.futuromovil.inspeccionvehicular.interactor.master.ListVehiclesCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import java.util.List;

public class MasterDataRepository implements MasterRepository {

    private final MasterDataStoreFactory masterDataStoreFactory;

    public MasterDataRepository(MasterDataStoreFactory masterDataStoreFactory) {
        this.masterDataStoreFactory = masterDataStoreFactory;
    }


    @Override
    public void listBrands(ListBrandsCallback listBrandsCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listBrands(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listBrandsCallback.onListBrandsError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsBrand> wsBrands = (List<WsBrand>) object;
                listBrandsCallback.onListBrandsSuccess(wsBrands);
            }
        });
    }

    @Override
    public void listModels(ListModelsCallback listModelsCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listModels(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listModelsCallback.onListModelsError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsModel> wsModels = (List<WsModel>) object;
                listModelsCallback.onListModelsSuccess(wsModels);
            }
        });
    }

    @Override
    public void listVehicles(ListVehiclesCallback listVehiclesCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listVehicles(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listVehiclesCallback.onListVehiclesError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsVehicle> wsVehicles = (List<WsVehicle>) object;
                listVehiclesCallback.onListVehiclesSuccess(wsVehicles);
            }
        });
    }

    @Override
    public void listLocations(ListLocationCallback listLocationCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listLocations(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listLocationCallback.onListLocationsError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsLocation> wsLocations = (List<WsLocation>) object;
                listLocationCallback.onListLocationsSuccess(wsLocations);
            }
        });
    }

    @Override
    public void listCities(ListCitiesCallback listCitiesCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listCities(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listCitiesCallback.onListCitiesError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsCity> wsCities = (List<WsCity>) object;
                listCitiesCallback.onListCitiesSuccess(wsCities);
            }
        });
    }

    @Override
    public void listVehiclesByModel(int idModel, ListVehiclesByModelCallback listVehiclesCallback) {
        final MasterDataStore masterDataStore = masterDataStoreFactory.create(Constants.STORE.CLOUD);
        masterDataStore.listVehiclesByModel(idModel,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                listVehiclesCallback.onListVehiclesByModelError(message);
            }
            @Override
            public void onSuccess(Object object) {
                List<WsVehicle> wsVehicles = (List<WsVehicle>) object;
                listVehiclesCallback.onListVehiclesByModelSuccess(wsVehicles);
            }
        });
    }


}
