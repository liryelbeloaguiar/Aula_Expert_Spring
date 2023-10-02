package com.aula.projetovd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aula.projetovd.entity.Cliente;
import com.aula.projetovd.repository.RepositoryCliente;

@SpringBootApplication
public class ProjetovdApplication {

	@Bean
	public CommandLineRunner commandLineRunner(@Autowired RepositoryCliente repositoryCliente){
		return args -> {

			Cliente c = new Cliente("Liryel");
			repositoryCliente.save(c);
			Cliente c1 = new Cliente("Lara");
			repositoryCliente.save(c1);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetovdApplication.class, args);
	}

}
