package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceQuantityPendingFrequency {

    @SerializedName("response")
    @Expose
    private WsQuantityPendingFrequency wsQuantityPendingFrequency;

    public WsQuantityPendingFrequency getWsQuantityPendingFrequency() {
        return wsQuantityPendingFrequency;
    }

    public void setWsQuantityPendingFrequency(WsQuantityPendingFrequency wsQuantityPendingFrequency) {
        this.wsQuantityPendingFrequency = wsQuantityPendingFrequency;
    }
}
