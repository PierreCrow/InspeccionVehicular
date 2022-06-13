package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsImage {

    @SerializedName("idVehiculoFrecuencia")
    @Expose
    private int idFrequency;

    @SerializedName("imagenCadena")
    @Expose
    private String image;

    public int getIdFrequency() {
        return idFrequency;
    }

    public void setIdFrequency(int idFrequency) {
        this.idFrequency = idFrequency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
