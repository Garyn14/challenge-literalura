package com.literatura.challenge_litura.client;

import com.literatura.challenge_litura.model.Book;
import com.literatura.challenge_litura.model.BookApi;
import com.literatura.challenge_litura.model.GutendexResponse;
import com.literatura.challenge_litura.repository.BookRepository;
import com.literatura.challenge_litura.service.BookService;
import com.literatura.challenge_litura.service.ConsumeApi;
import com.literatura.challenge_litura.service.IMapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private IMapData mapData;

    @Autowired
    private BookService bookService;

    private final String URL_BASE = "https://gutendex.com/books/";
    private final Scanner entry = new Scanner(System.in);

    private String menu(){
        return """
                \nBIENVENIDOS A FERNANDUS library
                
                OPCIONES:
                
                    1.Búsqueda de libro por título
                    2.Listar todos los libros
                    3.Listar autores de libros agregados
                    4.Autores vivos en determinado año
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
                            break;
                        }

                        // get the first book
                        Book book = new Book(response.books().get(0));

                        // review if the book exists in the database
                        boolean bookExists = bookService.existsByTitle(book.getTitle());
                        if (bookExists){
                            System.out.println("El libro ya existe en la base de datos");
                            System.out.println(book);
                            break;
                        }

                        // if the book was not created
                        System.out.println(bookService.createBook(book));
                        System.out.println("\nLibro agregado exitosamente a la base de datos");

                    } catch (IOException | InterruptedException ex){
                        System.out.println("Algo ha salido mal");
                    }

                    break;
                }
                case 2:{
                    System.out.println("Opción 02");
                    break;
                }
                case 3:{
                    System.out.println("Opción 3");
                    break;
                }
                default:{
                    System.out.println("Opción inválida, Ingrese una opción correcta");
                }
            }

        }
    }
}
