package com.joyscode.javabasico_se.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase encargada de manipular los datos de un libro.
 * Hereda de {@link Publication}
 * Implementa {@link IVisualizable}
 * 
 * @author jscastaneda
 * @since 31/08/2018
 */
public class Book extends Publication implements IVisualizable {
    
    private int id;
    private String isbn;
    private boolean readed;
    private int timeReaded;
    
    public Book(String title, Date edititionDate, String editorial, String[] authors) {
        super(title, edititionDate, editorial);
        super.setAuthors(authors);
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isReaded() {
        return readed;
    }
    
    public String getReaded() {
        if (readed) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public int getTimeReaded() {
        return timeReaded;
    }

    public void setTimeReaded(int timeReaded) {
        this.timeReaded = timeReaded;
    }
    
    @Override
    public String toString() {
        String detailBook = "\n :: BOOK ::" + 
                            "\n Title: " + getTitle() +
                            "\n Editorial: " + getEditorial() + 
                            "\n Edition Date: " + getEdititionDate() +
                            "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailBook += "\t" + getAuthors()[i];
        }
        return  detailBook;
    }

    @Override
    public Date startToSee(Date dateI) {
        return dateI;
    }

    @Override
    public void stopToSee(Date dateI, Date dateF) {
        if (dateF.getTime() > dateI.getTime()) {
            this.timeReaded = (int) (((dateF.getTime() - dateI.getTime()) * 60) / 1000);
        } else {
            this.timeReaded = 0;
        }
    }
    
    public static List<Book> makeMoviesList() {
        List<Book> books = new ArrayList<>();
        String[] authors = new String[3];
        for (int i = 0; i < 3; i++) {
            authors[i] = "author "+i;
        }
        for (int i = 1; i <= 5; i++) {
            books.add(new Book("Book " + i, new Date(), "Editorial " + i, authors));
        }
        
        return books;
    }
    
    public void view() {
        this.readed = true;
        Date dateI = this.startToSee(new Date());
        
        for (int i = 0; i < 100000; i++) {
            System.out.println("..........");
        }
        
        // Terminar de ver
        this.stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Viste: " + this);
        System.out.println("Por: " + this.timeReaded + " segundos");
    }
}