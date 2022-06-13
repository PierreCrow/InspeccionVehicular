package com.futuromovil.inspeccionvehicular.domain.repository;

import com.futuromovil.inspeccionvehicular.interactor.usuario.LoginCallback;
import com.futuromovil.inspeccionvehicular.interactor.usuario.QuantityPendingFreauencyCallback;

public interface UserRepository {

    void login(String email, String password, LoginCallback loginCallback);

    void quantityPendingFrequency(QuantityPendingFreauencyCallback quantityPendingFreauencyCallback);

}
