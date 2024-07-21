package com.literatura.challenge_litura;

import com.literatura.challenge_litura.client.Principal;
import com.literatura.challenge_litura.model.GutendexResponse;
import com.literatura.challenge_litura.service.ConsumeApi;
import com.literatura.challenge_litura.service.IMapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChallengeLituraApplication implements CommandLineRunner {

	@Autowired
	private IMapData mapData;

	@Autowired
	private Principal principal;

	public static void main(String[] args){
		SpringApplication.run(ChallengeLituraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String url = "https://gutendex.com/books/?search=Romeo%20and%20Julieta";
		String response;
		principal.initAplication();
		/*
		try{
			response = ConsumeApi.getData(url);
			System.out.println(response);

			GutendexResponse g = mapData.mapData(response, GutendexResponse.class);
			System.out.println(g);
		} catch (IOException | InterruptedException e){
			System.out.println("Algo ha salido mal");
		}

		 */
	}
}
