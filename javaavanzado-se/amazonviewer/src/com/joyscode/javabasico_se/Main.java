package com.joyscode.javabasico_se;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.joyscode.javabasico_se.model.Book;
import com.joyscode.javabasico_se.model.Chapter;
import com.joyscode.javabasico_se.model.Movie;
import com.joyscode.javabasico_se.model.Serie;

/**
 * Clase principal de la aplicación
 *
 * @author jscastaneda
 * @since 31/08/2018
 */
@SuppressWarnings("resource")
public class Main {
    
    static List<Movie> movies = Movie.makeMoviesList();
    static List<Book> books = Book.makeMoviesList();

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        System.out.println("BIENVENIDOS A AMAZON VIEWER");
        
        int exit = 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("Selecciona la opción que desee ingresando el número");
            System.out.println("1. Movies");
            System.out.println("2. Series");
            System.out.println("3. Books");
            System.out.println("4. Magazines");
            System.out.println("5. Report");
            System.out.println("6. Report Today");
            System.out.println("0. Exit");
            
            //Leer la respuesta del usuario
            System.out.print("Ingrese la opción: ");
            int response = sc.nextInt();
            switch (response) {
                case 0:
                    //salir
                    System.out.println();
                    System.out.println("GRACIAS POR UTILIZAR AMAZON VIEWER");
                    exit = 0;
                    break;
                case 1:
                    showMovies();
                    break;
                case 2:
                    showSeries();
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    showMagazines();
                    break;
                case 5:
                    makeReport();
                    break;
                case 6:
                    makeReport(new Date());
                    break;    
                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    break;
            }
        } while (exit != 0);
    }
    
    public static void showMovies() {
        int exit = 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println(":: MOVIES ::");
            
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i+1) + ". " + movies.get(i).getTitle() + ", Visto: " + movies.get(i).getViewed());
            }
            
            System.out.println("0. Regresar al Menú");
            
            // Leer la respuesta del usuario
            System.out.print("Ingrese la opción: ");
            int response = sc.nextInt();
            if (response == 0) {
                exit = 0;
            } else {
                try {
                    movies.get((response - 1)).view();;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                    System.out.println("....¡¡Código de pelicula invalido!!....");
                }
            }
        } while (exit != 0);
    }
    
    public static void showSeries() {
        int exit = 1;
        Scanner sc = new Scanner(System.in);
        List<Serie> series = Serie.makeSeriesList();
        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println(":: SERIES ::");
            
            for (int i = 0; i < series.size(); i++) {
                System.out.println((i+1) + ". " + series.get(i).getTitle() + ", Visto: " + series.get(i).getViewed());
            }
            
            System.out.println("0. Regresar al Menú");
            
            // Leer la respuesta del usuario
            System.out.print("Ingrese la opción: ");
            int response = sc.nextInt();
            if (response == 0) {
                exit = 0;
            } else {
                try {
                    Serie serieSelect = series.get((response - 1));
                    showChapters(serieSelect.getTitle(), serieSelect.getChapters());
                    serieSelect.view();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                    System.out.println("....¡¡Código de serie invalido!!....");
                }
            }
        } while (exit != 0);
    }
    
    public static void showChapters(String titleSerie, List<Chapter> chaptersOfSerieSelected) {
        int exit = 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println(":: CHAPTERS OF " + titleSerie + " ::");
            
            for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
                System.out.println((i+1) + ". " + chaptersOfSerieSelected.get(i).getTitle() + ", Visto: " + chaptersOfSerieSelected.get(i).getViewed());
            }
            
            System.out.println("0. Regresar al Menu");
            
            // Leer la respuesta del usuario
            System.out.print("Ingrese la opción: ");
            int response = sc.nextInt();
            if (response == 0) {
                exit = 0;
            } else {
                try {
                    chaptersOfSerieSelected.get((response - 1)).view();;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                    System.out.println("....¡¡Código de capitulo invalido!!....");
                }
            }
        } while (exit != 0);
    }

    public static void showBooks() {
        int exit = 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("====================================");
            System.out.println(":: BOOKS ::");
            
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i+1) + ". " + books.get(i).getTitle() + ", Visto: " + books.get(i).getReaded());
            }
            
            System.out.println("0. Regresar al Menú");
            
            // Leer la respuesta del usuario
            System.out.print("Ingrese la opción: ");
            int response = sc.nextInt();
            if (response == 0) {
                exit = 0;
            } else {
                try {
                    books.get((response - 1)).view();;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println();
                    System.out.println("....¡¡Código de libro invalido!!....");
                }
            }
        } while (exit != 0);
    }

    public static void showMagazines() {
        int exit = 0;
        do {
            System.out.println();
            System.out.println("===============");
            System.out.println(":: MAGAZINES ::");
        } while (exit != 0);
    }
    
    public static void makeReport() {
        Report report = new Report();
        report.setNameFile("reports/reportViews");
        report.setTitle(":: VISTOS ::");
        report.setExtention("txt");
        report.setContent("");
        
        for (Movie movie : movies) {
            if (movie.isViewed()) {
                report.setContent(report.getContent() + movie.toString() + "\n");
            }
        }
        
        report.makeReport();
    }
    
    public static void makeReport(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Report report = new Report();
        report.setNameFile("reports/reportViews_" + df.format(date));
        report.setTitle(":: VISTOS (" + df.format(date) + ") ::");
        report.setExtention("txt");
        report.setContent("");
        
        for (Movie movie : movies) {
            if (movie.isViewed()) {
                report.setContent(report.getContent() + movie.toString() + "\n");
            }
        }
        
        report.makeReport();
        System.out.println("Reporte generado con nombre " + report.getNameFile() + "." + report.getExtention());
    }
}
