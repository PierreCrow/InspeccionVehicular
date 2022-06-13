package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.user.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsLoginData {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nomUsuario")
    @Expose
    private String nomUsuario;

    @SerializedName("claUsuario")
    @Expose
    private String claUsuario;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("fecVencimientoToken")
    @Expose
    private String fecVencimientoToken;

    @SerializedName("nomCompleto")
    @Expose
    private String nomCompleto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFecVencimientoToken() {
        return fecVencimientoToken;
    }

    public void setFecVencimientoToken(String fecVencimientoToken) {
        this.fecVencimientoToken = fecVencimientoToken;
    }

    public String getNomCompleto() {
        return nomCompleto;
    }

    public void setNomCompleto(String nomCompleto) {
        this.nomCompleto = nomCompleto;
    }
}
