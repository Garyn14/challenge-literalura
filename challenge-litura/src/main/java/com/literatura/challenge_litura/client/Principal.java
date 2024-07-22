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
                    0.Salir
                    
                    -> Ingrese una opción válida:""";
    }

    public void initAplication(){

        int opc = -1;

        while (true){
            System.out.println(menu());
            opc = entry.nextInt();
            entry.nextLine();

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
                    System.out.println("\nIngrese el año: ");
                    int year = entry.nextInt();
                    entry.nextLine(); // clean buffer
                    System.out.println("\nLista de autores: ");
                    getAuthorsVivosInYear(year);
                    break;
                }
                case 5:{
                    System.out.println("\nEstadísticas por lenguages");
                    getStatisticsByLanguage();
                    break;
                }
                default:{
                    System.out.println("Opción inválida, Ingrese una opción correcta: \n");
                }
            }

        }
    }

    private void createBook(){
        System.out.println("\nIngrese el título del libro: ");
        String title = entry.nextLine();

        String titleFormat = title.replaceAll(" ", "%20");
        String url = URL_BASE + "?search=" + titleFormat;

        try {
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
            System.out.println("Algo ha salido mal");
        }
    }

    private void getBooks(){
        List<Book> books = (List<Book>) bookService.getBooks();

        if (books.isEmpty()){
            System.out.println("\nNo hay registros que mostrar");
        } else{
            books.forEach(System.out::println);
        }

        bookService.getBooks().forEach(System.out::println);
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
}
