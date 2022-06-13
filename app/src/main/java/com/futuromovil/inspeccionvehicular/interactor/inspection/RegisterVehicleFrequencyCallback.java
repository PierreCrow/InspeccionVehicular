package com.futuromovil.inspeccionvehicular.interactor.inspection;

public interface RegisterVehicleFrequencyCallback {

    void onRegisterVehicleFrequencySuccess(String message);

    void onRegisterVehicleFrequencyError(String message);
}
