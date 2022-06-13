package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsLoginParameter {

    @SerializedName("NomUsuario")
    @Expose
    private String nomUsuario;

    @SerializedName("ClaUsuario")
    @Expose
    private String claUsuario;


    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getClaUsuario() {
        return claUsuario;
    }

    public void setClaUsuario(String claUsuario) {
        this.claUsuario = claUsuario;
    }


}
