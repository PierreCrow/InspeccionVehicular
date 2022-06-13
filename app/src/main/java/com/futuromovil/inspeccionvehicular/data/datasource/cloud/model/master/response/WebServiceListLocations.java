package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceListLocations {

    @SerializedName("response")
    @Expose
    private WsListLocations wsListLocations;

    public WsListLocations getWsListLocations() {
        return wsListLocations;
    }

    public void setWsListLocations(WsListLocations wsListLocations) {
        this.wsListLocations = wsListLocations;
    }
}
