package com.aula.projetovd;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.aula.projetovd.entity.Pedido;
import com.aula.projetovd.entity.Cliente;
import com.aula.projetovd.repository.RepositoryCliente;
import com.aula.projetovd.repository.RepositoryPedido;

@SpringBootApplication
public class ProjetovdApplication {

	@Bean
	public CommandLineRunner init(@Autowired RepositoryCliente repositoryCliente, 
								  @Autowired RepositoryPedido repositoryPedido){
		return args -> {
			
			salvarCli(repositoryCliente);
			Cliente alguem = new Cliente("Liry");
			repositoryCliente.save(alguem);

			Pedido p = new Pedido();
			p.setCliente(alguem);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));

			repositoryPedido.save(p);

			repositoryCliente.findAll().forEach(System.out::println);

			Cliente cliente = repositoryCliente.findClienteFetchPedidots(alguem.getId());
			System.out.println(cliente);
			System.out.println(cliente.getPedidos());
	};}

	public void salvarCli(RepositoryCliente repositoryCliente) {
		repositoryCliente.save(new Cliente("Liryel"));
		repositoryCliente.save(new Cliente("Lara"));
		repositoryCliente.save(new Cliente("Jotinha"));
		repositoryCliente.save(new Cliente("Luan"));
		repositoryCliente.save(new Cliente("Fernanda"));
		repositoryCliente.save(new Cliente("Clarinha"));
		repositoryCliente.save(new Cliente("Geovana"));
		repositoryCliente.save(new Cliente("Suzana"));
		repositoryCliente.save(new Cliente("Paulo"));
		repositoryCliente.save(new Cliente("Sandro"));
		repositoryCliente.save(new Cliente("João"));
		repositoryCliente.save(new Cliente("Cezinha"));
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjetovdApplication.class, args);
	}

}
