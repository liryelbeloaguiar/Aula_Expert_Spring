package com.aula.projetovd;

import java.util.List;

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
	public CommandLineRunner init(@Autowired RepositoryCliente repositoryCliente){
		return args -> {
			repositoryCliente.salvar( new Cliente("Liryel"));
			repositoryCliente.salvar( new Cliente("Lara"));
			repositoryCliente.salvar( new Cliente("Jotinha"));

			System.out.println("Todos");
			List<Cliente> todosClientes = repositoryCliente.obterTodos();
			todosClientes.forEach(System.out::println);

			todosClientes.forEach(c -> {
				c.setNome(c.getNome()+" Atualizado");
				repositoryCliente.atualizar(c);
			});

			System.out.println("Todos Atualizados");
			todosClientes = repositoryCliente.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Todos que contenha -Jo-");
			repositoryCliente.pesquisaPorNome("Jo").forEach(System.out::println);

			/* 
			//Comando Delete não está funcionando
			System.out.println("Todos depois do delete");
			repositoryCliente.obterTodos().forEach(c-> {
				repositoryCliente.deletar(2);;
			});

			if(todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado");
			}else{
				todosClientes.forEach(System.out::println);
			}
			*/

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetovdApplication.class, args);
	}

}
