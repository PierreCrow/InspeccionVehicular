package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsLogin {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    @SerializedName("data")
    @Expose
    private WsLoginData wsLoginData;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public WsLoginData getWsLoginData() {
        return wsLoginData;
    }

    public void setWsLoginData(WsLoginData wsLoginData) {
        this.wsLoginData = wsLoginData;
    }
}
