package com.futuromovil.inspeccionvehicular.data.datasource.cloud.store;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.apiclient.ApiClient;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WebServiceListActivities;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListBrands;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListCities;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListLocations;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListModels;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WebServiceListVehicles;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsBrand;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsCity;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsLocation;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsModel;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response.WsVehicle;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.MasterDataStore;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloudMasterDataStore implements MasterDataStore {
    private static final String TAG = "CloudUsuarioDataStore";

    public CloudMasterDataStore() {
    }

    @Override
    public void listBrands(RepositoryCallback repositoryCallback) {

        Call<WebServiceListBrands> call = ApiClient.getApiClient("").listBrands();
        call.enqueue(new Callback<WebServiceListBrands>() {
            @Override
            public void onResponse(Call<WebServiceListBrands> call, Response<WebServiceListBrands> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListBrands wsListBrands = response.body();

                        if (wsListBrands.getWsListBrands().isSuccess()) {
                            List<WsBrand> wsBrands = wsListBrands.getWsListBrands().getWsBrands();
                            repositoryCallback.onSuccess(wsBrands);
                        } else {
                            repositoryCallback.onError(wsListBrands.getWsListBrands().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WebServiceListBrands> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void listModels(RepositoryCallback repositoryCallback) {

        Call<WebServiceListModels> call = ApiClient.getApiClient("").listModels();
        call.enqueue(new Callback<WebServiceListModels>() {
            @Override
            public void onResponse(Call<WebServiceListModels> call, Response<WebServiceListModels> response) {
                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListModels webServiceListModels = response.body();

                        if (webServiceListModels.getWsListModels().isSuccess()) {
                            List<WsModel> wsModels = webServiceListModels.getWsListModels().getWsModels();
                            repositoryCallback.onSuccess(wsModels);
                        } else {
                            repositoryCallback.onError(webServiceListModels.getWsListModels().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WebServiceListModels> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void listVehicles(RepositoryCallback repositoryCallback) {

        Call<WebServiceListVehicles> call = ApiClient.getApiClient("").listVehicles();
        call.enqueue(new Callback<WebServiceListVehicles>() {
            @Override
            public void onResponse(Call<WebServiceListVehicles> call, Response<WebServiceListVehicles> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListVehicles webServiceListVehicles = response.body();

                        if (webServiceListVehicles.getWsListVehicles().isSuccess()) {
                            List<WsVehicle> wsVehicles = webServiceListVehicles.getWsListVehicles().getWsVehicles();
                            repositoryCallback.onSuccess(wsVehicles);
                        } else {
                            repositoryCallback.onError(webServiceListVehicles.getWsListVehicles().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WebServiceListVehicles> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void listLocations(RepositoryCallback repositoryCallback) {


        Call<WebServiceListLocations> call = ApiClient.getApiClient("").listLocations();
        call.enqueue(new Callback<WebServiceListLocations>() {
            @Override
            public void onResponse(Call<WebServiceListLocations> call, Response<WebServiceListLocations> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListLocations webServiceListLocations = response.body();

                        if (webServiceListLocations.getWsListLocations().isSuccess()) {
                            List<WsLocation> wsLocations = webServiceListLocations.getWsListLocations().getWsLocations();
                            repositoryCallback.onSuccess(wsLocations);
                        } else {
                            repositoryCallback.onError(webServiceListLocations.getWsListLocations().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WebServiceListLocations> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void listCities(RepositoryCallback repositoryCallback) {

        Call<WebServiceListCities> call = ApiClient.getApiClient("").listCities();
        call.enqueue(new Callback<WebServiceListCities>() {
            @Override
            public void onResponse(Call<WebServiceListCities> call, Response<WebServiceListCities> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListCities webServiceListCities = response.body();

                        if (webServiceListCities.getWsListCities().isSuccess()) {
                            List<WsCity> wsCities = webServiceListCities.getWsListCities().getWsCities();
                            repositoryCallback.onSuccess(wsCities);
                        } else {
                            repositoryCallback.onError(webServiceListCities.getWsListCities().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<WebServiceListCities> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void listVehiclesByModel(int idModel, RepositoryCallback repositoryCallback) {

        Integer modelo=idModel;
        String url = Constants.URLS.LIST_VEHICLES_BY_MODEL + modelo.toString();

        Call<WebServiceListVehicles> call = ApiClient.getApiClient("").listVehiclesByModel(url);
        call.enqueue(new Callback<WebServiceListVehicles>() {
            @Override
            public void onResponse(Call<WebServiceListVehicles> call, Response<WebServiceListVehicles> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListVehicles webServiceListVehicles = response.body();

                        if (webServiceListVehicles.getWsListVehicles().isSuccess()) {
                            List<WsVehicle> wsVehicles = webServiceListVehicles.getWsListVehicles().getWsVehicles();
                            repositoryCallback.onSuccess(wsVehicles);
                        } else {
                            repositoryCallback.onError(webServiceListVehicles.getWsListVehicles().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(response.message());
                    }
                } else {
                    if (response.code() == Constants.HTTPS_CODE_RESPONSE.UNAUTHORIZED) {
                        repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNAUTHORIZED_CODE);
                    } else {
                        if (response.code() == Constants.HTTPS_CODE_RESPONSE.NOT_FOUND) {
                            repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.NOT_FOUND);
                        } else {
                            if (response.code() == Constants.HTTPS_CODE_RESPONSE.INTERNAL_SERVER_ERROR) {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.INTERNAL_SERVER_ERROR);
                            } else {
                                repositoryCallback.onError(Constants.HTTPS_MESSAGE_RESPONSE.UNKNOWN_ERROR);
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<WebServiceListVehicles> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }


}
