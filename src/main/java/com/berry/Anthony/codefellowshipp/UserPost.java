package com.berry.Anthony.codefellowshipp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;

@Entity
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String body;
    Timestamp time;

    @ManyToOne
    AppUser appUser;

    public UserPost (){}

    public String getBody() {
        return body;
    }

    public Timestamp time() {
        return time;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDateTime(Timestamp time) {
        this.time = time;
    }

    public UserPost(String body, Timestamp time, AppUser appUser){
        this.body = body;
        this.time = time;
        this.appUser = appUser;
    }


}
