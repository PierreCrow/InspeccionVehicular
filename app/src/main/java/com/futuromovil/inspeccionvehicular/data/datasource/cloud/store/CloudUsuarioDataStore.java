package com.futuromovil.inspeccionvehicular.data.datasource.cloud.store;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.apiclient.ApiClient;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.parameter.WsLoginParameter;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WebServiceLogin;
import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WebServiceQuantityPendingFrequency;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.UsuarioDataStore;
import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CloudUsuarioDataStore implements UsuarioDataStore {
    private static final String TAG = "CloudUsuarioDataStore";


    public CloudUsuarioDataStore() {

    }

    @Override
    public void login(String email, String password, RepositoryCallback repositoryCallback) {

        WsLoginParameter wsLoginParameter = new WsLoginParameter();
        wsLoginParameter.setNomUsuario(email);
        wsLoginParameter.setClaUsuario(password);
        Call<WebServiceLogin> call = ApiClient.getApiClient("").login(wsLoginParameter);
        call.enqueue(new Callback<WebServiceLogin>() {
            @Override
            public void onResponse(Call<WebServiceLogin> call, Response<WebServiceLogin> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceLogin webServiceLogin = response.body();

                        if (webServiceLogin.getWsLogin().isSuccess()) {
                            repositoryCallback.onSuccess(webServiceLogin.getWsLogin().getWsLoginData());
                        } else {
                            repositoryCallback.onError(webServiceLogin.getWsLogin().getMensaje());
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
            public void onFailure(Call<WebServiceLogin> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });


    }

    @Override
    public void quantityPendingFrequency(RepositoryCallback repositoryCallback) {

        Call<WebServiceQuantityPendingFrequency> call = ApiClient.getApiClient("").getQuantityPendingFrequency();
        call.enqueue(new Callback<WebServiceQuantityPendingFrequency>() {
            @Override
            public void onResponse(Call<WebServiceQuantityPendingFrequency> call, Response<WebServiceQuantityPendingFrequency> response) {

                if (response.code() == Constants.HTTPS_CODE_RESPONSE.OK) {
                    if (response.body() != null) {

                        WebServiceQuantityPendingFrequency webServiceQuantityPendingFrequency = response.body();

                        if (webServiceQuantityPendingFrequency.getWsQuantityPendingFrequency().isSuccess()) {
                            repositoryCallback.onSuccess(webServiceQuantityPendingFrequency.getWsQuantityPendingFrequency().getQuantity());
                        } else {
                            repositoryCallback.onError(webServiceQuantityPendingFrequency.getWsQuantityPendingFrequency().getMensaje());
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
            public void onFailure(Call<WebServiceQuantityPendingFrequency> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }


}
