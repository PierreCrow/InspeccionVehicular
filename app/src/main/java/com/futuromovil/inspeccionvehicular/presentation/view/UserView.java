package com.futuromovil.inspeccionvehicular.presentation.view;


import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;

public interface UserView extends BaseView {


    void loginSuccess(WsLoginData wsLoginData);

    void quantityPendingFrequencySuccess(Integer quantity);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
