package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceListModels {

    @SerializedName("response")
    @Expose
    private WsListModels wsListModels;

    public WsListModels getWsListModels() {
        return wsListModels;
    }

    public void setWsListModels(WsListModels wsListModels) {
        this.wsListModels = wsListModels;
    }
}
