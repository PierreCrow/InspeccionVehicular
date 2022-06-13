package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceListVehicles {

    @SerializedName("response")
    @Expose
    private WsListVehicles wsListVehicles;

    public WsListVehicles getWsListVehicles() {
        return wsListVehicles;
    }

    public void setWsListVehicles(WsListVehicles wsListVehicles) {
        this.wsListVehicles = wsListVehicles;
    }
}
