package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsActivity {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("idVehiculoFrecuencia")
    @Expose
    private int idVehiculoFrecuencia;

    @SerializedName("idActividad")
    @Expose
    private int idActividad;

    @SerializedName("descActividad")
    @Expose
    private String descActividad;

    @SerializedName("valorActividad")
    @Expose
    private boolean valorActividad;

    @SerializedName("idTipoActividad")
    @Expose
    private int idTipoActividad;

    @SerializedName("descTipoActividad")
    @Expose
    private String descTipoActividad;

    @SerializedName("idUsuCreacion")
    @Expose
    private int idUsuCreacion;

    @SerializedName("fecCreacion")
    @Expose
    private String fecCreacion;

    @SerializedName("idUsuModificacion")
    @Expose
    private int idUsuModificacion;

    @SerializedName("fecModificacion")
    @Expose
    private String fecModificacion;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehiculoFrecuencia() {
        return idVehiculoFrecuencia;
    }

    public void setIdVehiculoFrecuencia(int idVehiculoFrecuencia) {
        this.idVehiculoFrecuencia = idVehiculoFrecuencia;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getDescActividad() {
        return descActividad;
    }

    public void setDescActividad(String descActividad) {
        this.descActividad = descActividad;
    }

    public boolean isValorActividad() {
        return valorActividad;
    }

    public void setValorActividad(boolean valorActividad) {
        this.valorActividad = valorActividad;
    }

    public int getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(int idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getDescTipoActividad() {
        return descTipoActividad;
    }

    public void setDescTipoActividad(String descTipoActividad) {
        this.descTipoActividad = descTipoActividad;
    }

    public int getIdUsuCreacion() {
        return idUsuCreacion;
    }

    public void setIdUsuCreacion(int idUsuCreacion) {
        this.idUsuCreacion = idUsuCreacion;
    }

    public String getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(String fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public int getIdUsuModificacion() {
        return idUsuModificacion;
    }

    public void setIdUsuModificacion(int idUsuModificacion) {
        this.idUsuModificacion = idUsuModificacion;
    }

    public String getFecModificacion() {
        return fecModificacion;
    }

    public void setFecModificacion(String fecModificacion) {
        this.fecModificacion = fecModificacion;
    }
}
