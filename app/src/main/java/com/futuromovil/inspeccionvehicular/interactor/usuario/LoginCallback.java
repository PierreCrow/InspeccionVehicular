package com.futuromovil.inspeccionvehicular.interactor.usuario;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;

public interface LoginCallback {

    void onLoginSuccess(WsLoginData wsLoginData);

    void onLoginError(String message);
}
