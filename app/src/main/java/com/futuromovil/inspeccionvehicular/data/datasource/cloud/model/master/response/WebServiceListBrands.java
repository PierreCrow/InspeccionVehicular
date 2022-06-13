package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebServiceListBrands {

    @SerializedName("response")
    @Expose
    private WsListBrands wsListBrands;

    public WsListBrands getWsListBrands() {
        return wsListBrands;
    }

    public void setWsListBrands(WsListBrands wsListBrands) {
        this.wsListBrands = wsListBrands;
    }
}
