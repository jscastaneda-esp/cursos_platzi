package com.joyscode.javabasico_se.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter extends Movie {
    
    private int id;
    private int sessionNumber;
    
    public Chapter(String title, String genre, String creator, int duration, short year, int sessionNumber) {
        super(title, genre, creator, duration, year);
        this.sessionNumber = sessionNumber;
    }
    
     @Override
    public int getId() {
        return id;
    }

    public int getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }
    
    @Override
    public String toString() {
        return "\n :: CHAPTER ::" +
                "\n Title: " + getTitle() +
                "\n Year: " + getYear() + 
                "\n Creator: " + getCreator() +
                "\n Duration: " + getDuration();
    }
    
    public static List<Chapter> makeChaptersList() {
        List<Chapter> chapters = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) {
            chapters.add(new Chapter("Capituo "+i, "genero "+i, "creator" +i, 45, (short)(2017+i), i));
        }
        
        return chapters;
    }
}