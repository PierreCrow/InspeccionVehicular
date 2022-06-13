package com.futuromovil.inspeccionvehicular.data.datasource.cloud.model.inspection.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsGeneralInfo {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("idVehiculo")
    @Expose
    private int idVehiculo;

    @SerializedName("idTipoFrecuencia")
    @Expose
    private int idTipoFrecuencia;

    @SerializedName("idUbicacion")
    @Expose
    private int idUbicacion;

    @SerializedName("odometro")
    @Expose
    private int odometro;

    @SerializedName("idUnidadMedida")
    @Expose
    private int idUnidadMedida;

    @SerializedName("fecRecepcion")
    @Expose
    private String fecRecepcion;

    @SerializedName("fecEntrega")
    @Expose
    private String fecEntrega;

    @SerializedName("observacion")
    @Expose
    private String observacion;

    @SerializedName("fecRevision")
    @Expose
    private String fecRevision;

    @SerializedName("fecProgRevision")
    @Expose
    private String fecProgRevision;

    @SerializedName("sec")
    @Expose
    private int sec;

    @SerializedName("estadoInspeccion")
    @Expose
    private int estadoInspeccion;

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

    @SerializedName("listVehiculoFrecuenciaActividads")
    @Expose
    private List<WsActivity> wsActivities;


    @SerializedName("listVehiculoFrecuenciaImgs")
    @Expose
    private List<WsImage> wsImages;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdTipoFrecuencia() {
        return idTipoFrecuencia;
    }

    public void setIdTipoFrecuencia(int idTipoFrecuencia) {
        this.idTipoFrecuencia = idTipoFrecuencia;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getOdometro() {
        return odometro;
    }

    public void setOdometro(int odometro) {
        this.odometro = odometro;
    }

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getFecRecepcion() {
        return fecRecepcion;
    }

    public void setFecRecepcion(String fecRecepcion) {
        this.fecRecepcion = fecRecepcion;
    }

    public String getFecEntrega() {
        return fecEntrega;
    }

    public void setFecEntrega(String fecEntrega) {
        this.fecEntrega = fecEntrega;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getFecRevision() {
        return fecRevision;
    }

    public void setFecRevision(String fecRevision) {
        this.fecRevision = fecRevision;
    }

    public String getFecProgRevision() {
        return fecProgRevision;
    }

    public void setFecProgRevision(String fecProgRevision) {
        this.fecProgRevision = fecProgRevision;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public int getEstadoInspeccion() {
        return estadoInspeccion;
    }

    public void setEstadoInspeccion(int estadoInspeccion) {
        this.estadoInspeccion = estadoInspeccion;
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

    public List<WsActivity> getWsActivities() {
        return wsActivities;
    }

    public void setWsActivities(List<WsActivity> wsActivities) {
        this.wsActivities = wsActivities;
    }

    public List<WsImage> getWsImages() {
        return wsImages;
    }

    public void setWsImages(List<WsImage> wsImages) {
        this.wsImages = wsImages;
    }
}
