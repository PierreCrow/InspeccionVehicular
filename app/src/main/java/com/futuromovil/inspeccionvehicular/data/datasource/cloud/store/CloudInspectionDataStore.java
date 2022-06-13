package com.futuromovil.inspeccionvehicular.data.datasource.cloud.store;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.apiclient.ApiClient;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WebServiceListActivities;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WebServiceRegisterResponse;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response.WsGeneralInfo;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.InspectionDataStore;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CloudInspectionDataStore implements InspectionDataStore {
    private static final String TAG = "CloudUsuarioDataStore";


    public CloudInspectionDataStore() {
    }


    @Override
    public void listActivitiesByIdVehicle(String idVehicle, RepositoryCallback repositoryCallback) {
        String url = Constants.URLS.LIST_ACTIVITIES_BY_IDVEHICLE + idVehicle;

        Call<WebServiceListActivities> call = ApiClient.getApiClient("").listActivitiesByIdVehicle(url);
        call.enqueue(new Callback<WebServiceListActivities>() {
            @Override
            public void onResponse(Call<WebServiceListActivities> call, Response<WebServiceListActivities> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceListActivities webServiceListActivities = response.body();

                        if (webServiceListActivities.getWsListActivities().isSuccess()) {
                            if (webServiceListActivities.getWsListActivities().getWsGeneralInfo().size() == 0) {
                                repositoryCallback.onError("No hay actividades");
                            } else {
                                repositoryCallback.onSuccess(webServiceListActivities.getWsListActivities().getWsGeneralInfo().get(0));
                            }
                        } else {
                            repositoryCallback.onError(webServiceListActivities.getWsListActivities().getMessage());
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
            public void onFailure(Call<WebServiceListActivities> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void registerVehicleFrequency(WsGeneralInfo wsGeneralInfo, RepositoryCallback repositoryCallback) {

        Call<WebServiceRegisterResponse> call = ApiClient.getApiClient("").registerVehicleFrequency(wsGeneralInfo);
        call.enqueue(new Callback<WebServiceRegisterResponse>() {
            @Override
            public void onResponse(Call<WebServiceRegisterResponse> call, Response<WebServiceRegisterResponse> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceRegisterResponse wsRegisterResponse = response.body();

                        if (wsRegisterResponse.getWsRegisterResponse().isSuccess()) {
                            repositoryCallback.onSuccess(wsRegisterResponse.getWsRegisterResponse().getMessage());

                        } else {
                            repositoryCallback.onError(wsRegisterResponse.getWsRegisterResponse().getMessage());
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
            public void onFailure(Call<WebServiceRegisterResponse> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }
}
