package com.example.kostadin.youlocal;
import java.io.Serializable;

/**
 * Created by Kostadin on 7/22/2016.
 */
// a class to store the data received from the server
public class User implements Serializable {
    String username,URL,info;

   // getter and setter for the username
    public void setName(String name) {
        this.username = name;
    }
    public String getName(){
        return username;
    }
    // getter and setter for info
    public void setInfo(String description){
        this.info=description;
    }
    public String getInfo(){
        return info;
    }
    // getter and setter for avatar
    public void setAvatar(String avatar){
        this.URL=avatar;
    }
    public String getAvatar(){
        return URL;
    }


}
