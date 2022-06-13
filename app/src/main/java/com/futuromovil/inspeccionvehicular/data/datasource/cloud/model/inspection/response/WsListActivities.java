package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsListActivities {

    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("mensaje")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private List<WsGeneralInfo> wsGeneralInfo;

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

    public List<WsGeneralInfo> getWsGeneralInfo() {
        return wsGeneralInfo;
    }

    public void setWsGeneralInfo(List<WsGeneralInfo> wsGeneralInfo) {
        this.wsGeneralInfo = wsGeneralInfo;
    }
}
