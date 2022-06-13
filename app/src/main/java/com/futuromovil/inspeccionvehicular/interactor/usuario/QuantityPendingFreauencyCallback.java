package com.futuromovil.inspeccionvehicular.interactor.usuario;

public interface QuantityPendingFreauencyCallback {

    void onQuantityPendingFreauencySuccess(Integer quantity);

    void onQuantityPendingFreauencyError(String message);
}
