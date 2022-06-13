package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceListActivities {

    @SerializedName("response")
    @Expose
    private WsListActivities wsListActivities;

    public WsListActivities getWsListActivities() {
        return wsListActivities;
    }

    public void setWsListActivities(WsListActivities wsListActivities) {
        this.wsListActivities = wsListActivities;
    }
}
