package com.futuromovil.inspeccionvehicular.data.datasource.db.store.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "DbUsuario")
public class DbTabla
    implements Serializable {

        @PrimaryKey(autoGenerate = true)
        private int id;
        private String idCloud;
        private int id_code;
        private String user;
        private String full_name;
        private String mail;
        private String password;
        private String photo;
        private String referred_code;

    public DbTabla(String idCloud, int id_code, String user, String full_name, String mail, String password, String photo, String referred_code) {
            this.idCloud = idCloud;
            this.id_code = id_code;
            this.user=user;
            this.full_name = full_name;
            this.mail = mail;
            this.password = password;
            this.photo = photo;
            this.referred_code = referred_code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdCloud() {
            return idCloud;
        }

        public void setIdCloud(String idCloud) {
            this.idCloud = idCloud;
        }

        public int getId_code() {
            return id_code;
        }

        public void setId_code(int id_code) {
            this.id_code = id_code;
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

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getReferred_code() {
            return referred_code;
        }

        public void setReferred_code(String referred_code) {
            this.referred_code = referred_code;
        }
}
