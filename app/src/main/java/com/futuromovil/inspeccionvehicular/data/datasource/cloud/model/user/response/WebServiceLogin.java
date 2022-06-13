package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebServiceLogin {

    @SerializedName("response")
    @Expose
    private WsLogin wsLogin;

    public WsLogin getWsLogin() {
        return wsLogin;
    }

    public void setWsLogin(WsLogin wsLogin) {
        this.wsLogin = wsLogin;
    }
}
