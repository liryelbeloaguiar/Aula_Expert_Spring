package com.aula.projetovd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aula.projetovd.domain.entity.Cliente;
import com.aula.projetovd.domain.repository.RepositoryCliente;

@SpringBootApplication
public class ProjetovdApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired RepositoryCliente repositoryCliente) {
		return args -> {
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetovdApplication.class, args);
	}

}
