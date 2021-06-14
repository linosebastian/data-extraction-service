package com.healthcare.platform.solutions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hps_trial_user_data")
public class PGEntity {
    @Id
    @Column(name = "user_id")
    private String user_id;
    @Column(name = "username")
    private String username;
    @Column(name = "user_email")
    private String user_email;

    public PGEntity() {
    }

    public PGEntity(String user_id, String username, String user_email) {
        this.user_id = user_id;
        this.username = username;
        this.user_email = user_email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
