package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceListCities {

    @SerializedName("response")
    @Expose
    private WsListCities wsListCities;

    public WsListCities getWsListCities() {
        return wsListCities;
    }

    public void setWsListCities(WsListCities wsListCities) {
        this.wsListCities = wsListCities;
    }
}
