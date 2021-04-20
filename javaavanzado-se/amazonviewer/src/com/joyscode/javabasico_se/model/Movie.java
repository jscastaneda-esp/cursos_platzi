package com.joyscode.javabasico_se.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de manipular los datos de una pelicula.
 * Hereda de {@link Film}
 * Implementa {@link IVisualizable}
 * 
 * @author jscastaneda
 * @since 31/08/2018
 */
public class Movie extends Film implements IVisualizable {
    
    private int id;
    private int timeViewed;

    public Movie(String title, String genre, String creator, int duration, short year) {
        super(title, genre, creator, duration);
        super.setYear(year);
    }

    public void showData() {
//        System.out.println("Title:" + title);
//        System.out.println("Genre:" + genre);
//        System.out.println("Year:" + year);
    }

    public int getId() {
        return id;
    }

    public int getTimeViewed() {
        return timeViewed;
    }

    public void setTimeViewed(int timeViewed) {
        this.timeViewed = timeViewed;
    }

    @Override
    public String toString() {
        return "\n :: MOVIE ::" +
                "\n Title: " + getTitle() +
                "\n Genre: " + getGenre() +
                "\n Year: " + getYear() +
                "\n Creator: " + getCreator() +
                "\n Duration: " + getDuration();
    }

    @Override
    public Date startToSee(Date dateI) {
        return dateI;
    }

    @Override
    public void stopToSee(Date dateI, Date dateF) {
        if (dateF.getTime() > dateI.getTime()) {
            this.timeViewed = (int) (((dateF.getTime() - dateI.getTime()) * 60) / 1000);
        } else {
            this.timeViewed = 0;
        }
    }
    
    public static List<Movie> makeMoviesList() {
        List<Movie> movies = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) {
            movies.add(new Movie("Movie " + i, "Genre: " + i, "Creator " + i, 120+1, (short) (2017+i)));
        }
        
        return movies;
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
        System.out.println("Por: " + this.timeViewed + " segundos");
    }
}