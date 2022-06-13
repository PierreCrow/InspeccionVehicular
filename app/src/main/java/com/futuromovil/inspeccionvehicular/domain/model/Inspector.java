package com.futuromovil.inspeccionvehicular.domain.model;

import java.io.Serializable;

public class Inspector implements Serializable {

    private int id;
    private String user;
    private String full_name;
    private String mail;
    private String password;
    private String token;
    private boolean logged;
    private boolean fotoLoaded;

    private String foto1;
    private String foto2;
    private String foto3;


    public Inspector(int id, String user, String full_name, String mail, String password,
                     String token, boolean logged, boolean fotoLoaded, String foto1, String foto2, String foto3) {
        this.id = id;
        this.user = user;
        this.full_name = full_name;
        this.mail = mail;
        this.password = password;
        this.token = token;
        this.logged=logged;
        this.fotoLoaded=fotoLoaded;
        this.foto1=foto1;
        this.foto2=foto2;
        this.foto3=foto3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isFotoLoaded() {
        return fotoLoaded;
    }

    public void setFotoLoaded(boolean fotoLoaded) {
        this.fotoLoaded = fotoLoaded;
    }

    public String getFoto1() {
        return foto1;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }
}
