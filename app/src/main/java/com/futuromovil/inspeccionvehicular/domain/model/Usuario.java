package com.futuromovil.inspeccionvehicular.domain.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    public Usuario(int id, String name, String username, String email, String phone,
                   String website) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
