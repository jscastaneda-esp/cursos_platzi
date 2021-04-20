package com.joyscode.javabasico_se.model;

import java.util.Date;

public class Magazine extends Publication {

    private int id;
    
    public Magazine(String title, Date edititionDate, String editorial) {
        super(title, edititionDate, editorial);
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "\n :: MAGAZINE ::" + 
                "\n Title: " + getTitle() +
                "\n Edition Date: " + getEdititionDate() + 
                "\n Editorial: " + getEditorial();
    }
}