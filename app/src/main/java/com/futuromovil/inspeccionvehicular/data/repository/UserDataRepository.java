package com.futuromovil.inspeccionvehicular.data.repository;

import com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response.WsLoginData;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.UsuarioDataStore;
import com.futuromovil.inspeccionvehicular.data.datasource.datastore.UserDataStoreFactory;

import com.futuromovil.inspeccionvehicular.domain.repository.RepositoryCallback;
import com.futuromovil.inspeccionvehicular.domain.repository.UserRepository;
import com.futuromovil.inspeccionvehicular.interactor.usuario.LoginCallback;
import com.futuromovil.inspeccionvehicular.interactor.usuario.QuantityPendingFreauencyCallback;
import com.futuromovil.inspeccionvehicular.presentation.utils.Constants;

public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;

    public UserDataRepository(UserDataStoreFactory userDataStoreFactory) {
        this.userDataStoreFactory = userDataStoreFactory;
    }

    @Override
    public void login(String email, String password, LoginCallback loginCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.login(email, password, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                loginCallback.onLoginError(message);
            }

            @Override
            public void onSuccess(Object object) {
                WsLoginData wsLoginData = (WsLoginData) object;
                loginCallback.onLoginSuccess(wsLoginData);
            }
        });
    }

    @Override
    public void quantityPendingFrequency(QuantityPendingFreauencyCallback quantityPendingFreauencyCallback) {
        final UsuarioDataStore usuarioDataStore = userDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.quantityPendingFrequency(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                quantityPendingFreauencyCallback.onQuantityPendingFreauencyError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Integer quantity = (Integer) object;
                quantityPendingFreauencyCallback.onQuantityPendingFreauencySuccess(quantity);
            }
        });
    }

}
