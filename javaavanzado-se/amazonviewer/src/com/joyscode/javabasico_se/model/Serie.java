package com.joyscode.javabasico_se.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de manipular los datos de una serie.
 * Hereda de {@link Film}
 * 
 * @author jscastaneda
 * @since 31/08/2018
 */
public class Serie extends Film {

    private int id;
    private int sessionQuantity;
    private List<Chapter> chapters;
    
    public Serie(String title, String genre, String creator, int duration, int sessionQuantity, List<Chapter> chapters) {
        super(title, genre, creator, duration);
        this.sessionQuantity = sessionQuantity;
        this.chapters = chapters;
    }

    public int getId() {
        return id;
    }
    
    public int getSessionQuantity() {
        return sessionQuantity;
    }

    public void setSessionQuantity(int sessionQuantity) {
        this.sessionQuantity = sessionQuantity;
    }
    
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "\n :: SERIE ::" + 
                "\n Title: " + getTitle() +
                "\n Genero: " + getGenre() + 
                "\n Year: " + getYear() + 
                "\n Creator: " + getCreator() +
                "\n Duration: " + getDuration();
    }
    
    public static List<Serie> makeSeriesList() {
        List<Serie> series = new ArrayList<>();
        
        for (int i = 1; i <= 5; i++) {
            series.add(new Serie("Serie "+i, "Genre "+i, "Creador "+i, 1200, 5, Chapter.makeChaptersList("Serie "+i)));
        }
        
        return series;
    }

    public void view() {
        int chapterViewedCount = 0;
        
        for (Chapter chapter : this.chapters) {
            if (chapter.isViewed()) {
                chapterViewedCount++;
            }
        }
        
        if (chapterViewedCount == this.chapters.size()) {
            this.setViewed(true);
        }
    }
}