package com.literatura.challenge_litura;

import com.literatura.challenge_litura.service.ConsumeApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChallengeLituraApplication implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(ChallengeLituraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String url = "https://gutendex.com/books/";
		String response;

		try{
			response = ConsumeApi.getData(url);
			System.out.println(response);
		} catch (IOException | InterruptedException e){
			System.out.println("Algo ha salido mal");
		}

	}
}
