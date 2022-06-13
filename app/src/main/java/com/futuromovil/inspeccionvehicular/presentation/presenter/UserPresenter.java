package com.futuromovil.inspeccionvehicular.presentation.presenter;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.UserDataStoreFactory;
import com.futuromovil.inspeccionvehicular.data.repository.UserDataRepository;
import com.futuromovil.inspeccionvehicular.domain.repository.UserRepository;
import com.futuromovil.inspeccionvehicular.interactor.usuario.LoginCallback;
import com.futuromovil.inspeccionvehicular.interactor.usuario.QuantityPendingFreauencyCallback;
import com.futuromovil.inspeccionvehicular.interactor.usuario.UserInteractor;
import com.futuromovil.inspeccionvehicular.presentation.view.UserView;

public class UserPresenter
        implements Presenter<UserView>,
        LoginCallback, QuantityPendingFreauencyCallback {

    private UserView userView;
    private UserInteractor userInteractor;

    public void login(String email, String pass) {
        userInteractor.login(email, pass, this);
    }

    public void quantityPendingFrequency() {
        userInteractor.quantityPendingFrequency(this);
    }

    @Override
    public void addView(UserView view) {
        this.userView = view;
        UserRepository userRepository = new UserDataRepository(new UserDataStoreFactory(this.userView.getContext()));
        userInteractor = new UserInteractor(userRepository);
    }

    @Override
    public void removeView(UserView view) {

    }

    @Override
    public void onLoginSuccess(WsLoginData wsLoginData) {
        userView.loginSuccess(wsLoginData);
    }

    @Override
    public void onLoginError(String message) {
        userView.showErrorMessage(message);
    }

    @Override
    public void onQuantityPendingFreauencySuccess(Integer quantity) {
        userView.quantityPendingFrequencySuccess(quantity);
    }

    @Override
    public void onQuantityPendingFreauencyError(String message) {
        userView.showErrorMessage(message);
    }
}
