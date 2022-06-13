package com.futuromovil.inspeccionvehicular.interactor.usuario;

import com.futuromovil.inspeccionvehicular.domain.repository.UserRepository;

public class UserInteractor {

    private final UserRepository userRepository;

    public UserInteractor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(String email, String pass, LoginCallback loginCallback) {
        userRepository.login(email, pass, loginCallback);
    }

    public void quantityPendingFrequency(QuantityPendingFreauencyCallback quantityPendingFreauencyCallback) {
        userRepository.quantityPendingFrequency(quantityPendingFreauencyCallback);
    }

}
