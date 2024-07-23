package com.literatura.challenge_litura.client;

import com.literatura.challenge_litura.model.Author;
import com.literatura.challenge_litura.model.Book;
import com.literatura.challenge_litura.model.GutendexResponse;
import com.literatura.challenge_litura.model.StatisticsByLanguage;
import com.literatura.challenge_litura.service.AuthorService;
import com.literatura.challenge_litura.service.BookService;
import com.literatura.challenge_litura.service.ConsumeApi;
import com.literatura.challenge_litura.service.IMapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private IMapData mapData;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    private final String URL_BASE = "https://gutendex.com/books/";
    private final Scanner entry = new Scanner(System.in);

    private String menu(){
        return """
                \nBIENVENIDOS A FERNANDUS library
                
                OPCIONES:
                
                    1.Búsqueda de libro por título
                    2.Listarlibros
                    3.Listar autores
                    4.Autores vivos en determinado año
                    5.Estadísticas por lenguage
                    6.Estadísticas de descargas
                    7.Top 5 libros más descargados
                    8.Buscar autor por nombre
                    0.Salir
                    
                    -> Ingrese una opción:""";
    }

    public void initAplication(){

        int opc = -1;

        while (true){
            try{
                System.out.println(menu());
                opc = entry.nextInt();
            } catch (InputMismatchException ex){
                System.out.println("\nOpción inválida, ingrese una opción válida: ");
                continue;
            } finally {
                entry.nextLine(); // clean buffer
            }

            if(opc == 0) break;

            switch (opc){
                case 1:{
                    createBook();
                    break;
                }
                case 2:{
                    System.out.println("\nLibros guardados en la base de datos");
                    getBooks();
                    break;
                }
                case 3:{
                    System.out.println("\nLista de Autores: ");
                    getAuthors();
                    break;
                }
                case 4:{
                    try {
                        System.out.println("\nIngrese el año: ");
                        int year = entry.nextInt();
                        System.out.println("\nLista de autores: ");
                        getAuthorsVivosInYear(year);
                    } catch (InputMismatchException ex){
                        System.out.println("\nIngrese una opción válida");
                    } finally {
                        entry.nextLine(); // clean buffer
                    }

                    break;
                }
                case 5:{
                    System.out.println("\nEstadísticas por lenguages");
                    getStatisticsByLanguage();
                    break;
                }
                case 6:{
                    System.out.println("\nEstadísticas:");
                    getBooksStatistics();
                    break;
                }
                case 7:{
                    top5BookMostDownloaded();
                    break;
                }
                case 8:{
                    try {
                        System.out.println("\nIngrese el nombre a buscar: ");
                        String name = entry.nextLine();
                        searchAuthorByName(name);
                    } catch (InputMismatchException ex){
                        System.out.println("\nIngrese una opción válida");
                    }

                    break;
                }
                default:{
                    System.out.println("Opción inválida, Ingrese una opción correcta: \n");
                }
            }
        }

        System.out.println("""
                FIN DEL PROGRAMA
                VUELVA PRONTO :D
                """);
    }

    private void createBook(){
        try {
            System.out.println("\nIngrese el título del libro: ");
            String title = entry.nextLine();

            String titleFormat = title.replaceAll(" ", "%20");
            String url = URL_BASE + "?search=" + titleFormat;

            String json = ConsumeApi.getData(url);
            GutendexResponse response = mapData.mapData(json, GutendexResponse.class);

            // check if the book exists in the API
            if (response.books().isEmpty()){
                System.out.println("\nLibro no encontrado, pruebe con otro título");
                return;
            }

            // get the first book
            Book book = new Book(response.books().get(0));

            // review if the book exists in the database
            boolean bookExists = bookService.existsByTitle(book.getTitle());
            if (bookExists){
                System.out.println("El libro ya existe en la base de datos");
                System.out.println(book);
                return;
            }

            // if the book was not created
            bookService.createBook(book);
            System.out.println("\nLibro agregado exitosamente a la base de datos");

        } catch (IOException | InterruptedException ex){
            System.out.println("\nAlgo ha salido mal con el servidor, intentelo más adelante");
        } catch (InputMismatchException ex){
            System.out.println("\nIngrese una opción válida");
        }
    }

    private void getBooks(){
        List<Book> books = (List<Book>) bookService.getBooks();

        if (books.isEmpty()){
            System.out.println("\nNo hay registros que mostrar");
        } else{
            books.forEach(System.out::println);
        }
    }

    private void getAuthors(){
        List<Author> authors = (List<Author>) authorService.getAuthors();

        if (authors.isEmpty()){
            System.out.println("\nNo hay registros que mostrar");
        } else{
            authors.forEach(System.out::println);
        }
    }

    private void getAuthorsVivosInYear(int year){
        List<Author> authors = authorService.getAuthorsVivosInYear(year);

        if (authors.isEmpty()){
            System.out.println("\nNo hay registros que mostrar");
        } else{
            authors.forEach(System.out::println);
        }
    }

    private void getStatisticsByLanguage() {
        List<StatisticsByLanguage> statistics = bookService.getStatisticsByLanguage();

        if(statistics.isEmpty()){
            System.out.println("\nNo hay registros que mostrar");
        } else{
            statistics.forEach(System.out::println);
        }
    }

    private void getBooksStatistics(){
        DoubleSummaryStatistics statistics = bookService.getBooksStatistics();
        System.out.println("\nEl libro más popular tiene " + statistics.getMax() + " descargas");
        System.out.println("El libro menos popular tiene " + statistics.getMin() + " descargas");
        System.out.println("La media de descargas es " + statistics.getAverage());
    }

    private void top5BookMostDownloaded(){
        System.out.println("\nTOP 5: ");
        bookService.getTop5MostBooksDownloaded().forEach(System.out::println);
    }

    private void searchAuthorByName(String name){

        Author author = authorService.getAuthorByName(name);

        if (author == null){
            System.out.println("\nNo se encontró el autor");
        } else{
            System.out.println(author);
        }
    }
}
