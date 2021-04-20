package com.joyscode.javabasico_se.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de manipular los datos de un capitulo.
 * Hereda de {@link Movie}
 *
 * @author jscastaneda
 * @since 31/08/2018
 */
public class Chapter extends Movie {

    private int id;
    private int sessionNumber;
    private String titleSerie;
    
    public Chapter(String title, String genre, String creator, int duration, short year, int sessionNumber, String titleSerie) {
        super(title, genre, creator, duration, year);
        this.sessionNumber = sessionNumber;
        this.titleSerie = titleSerie;
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

    public String getTitleSerie() {
        return titleSerie;
    }

    public void setTitleSerie(String titleSerie) {
        this.titleSerie = titleSerie;
    }

    @Override
    public String toString() {
        return  "\n :: SERIE ::" + 
                "\n Title: " + this.titleSerie + 
                "\n :: CHAPTER ::" + 
                "\n Title: " + getTitle() + 
                "\n Year: " + getYear() + 
                "\n Creator: " + getCreator() + 
                "\n Duration: " + getDuration();
    }
    
    public static List<Chapter> makeChaptersList(String titleSerie) {
        List<Chapter> chapters = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) {
            chapters.add(new Chapter("Chapter "+i, "Genre "+i, "Creator" +i, 45, (short)(2017+i), i, titleSerie));
        }
        
        return chapters;
    }
    
    @Override
    public void view() {
        super.setViewed(true);
        Date dateI = this.startToSee(new Date());
        
        for (int i = 0; i < 100000; i++) {
            System.out.println("..........");
        }
        
        // Terminar de ver
        this.stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Viste: " + this);
        System.out.println("Por: " + super.getTimeViewed() + " segundos");
    }
}