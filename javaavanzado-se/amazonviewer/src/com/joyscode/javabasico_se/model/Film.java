package com.joyscode.javabasico_se.model;

/**
 * Clase para definicion basica de una filmacion
 *
 * @author jscastaneda
 * @since 31/08/2018
 */
public abstract class Film {

    private String title;
    private String genre;
    private String creator;
    private int duration;
    private short year;
    private boolean viewed;

    public Film(String title, String genre, String creator, int duration) {
        super();
        this.title = title;
        this.genre = genre;
        this.creator = creator;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }
    
    public boolean isViewed() {
        return viewed;
    }

    public String getViewed() {
        if (viewed) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    
    public abstract void view();
}
