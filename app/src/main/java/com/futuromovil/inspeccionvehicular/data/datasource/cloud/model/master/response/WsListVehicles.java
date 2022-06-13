package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.master.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsListVehicles {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("mensaje")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<WsVehicle> wsVehicles;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<WsVehicle> getWsVehicles() {
        return wsVehicles;
    }

    public void setWsVehicles(List<WsVehicle> wsVehicles) {
        this.wsVehicles = wsVehicles;
    }
}
