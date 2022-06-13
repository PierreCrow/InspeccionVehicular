package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceRegisterResponse {

    @SerializedName("response")
    @Expose
    private WsRegisterResponse wsRegisterResponse;

    public WsRegisterResponse getWsRegisterResponse() {
        return wsRegisterResponse;
    }

    public void setWsRegisterResponse(WsRegisterResponse wsRegisterResponse) {
        this.wsRegisterResponse = wsRegisterResponse;
    }
}
