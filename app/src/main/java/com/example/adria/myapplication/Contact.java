package com.example.adria.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    String name, email, username,pass;



    public void setName(String name){
        this.name =name;}
    public String getName(){
        return this.name;
    }

    public void setEmail(String email){
        this.email =email;}
    public String getEmail(){
        return this.email;
    }
    public void setUsername(String username){
        this.username =username;}
    public String getUsername(){
        return this.name;
    }

    public void setPass(String pass){
        this.pass =pass;}
    public String getPass(){
        return this.pass;
    }
}
