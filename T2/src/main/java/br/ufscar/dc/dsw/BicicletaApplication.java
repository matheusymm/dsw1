package br.ufscar.dc.dsw;

 import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.context.annotation.Bean;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

 import br.ufscar.dc.dsw.dao.*;
 import br.ufscar.dc.dsw.domain.*;

@SpringBootApplication
public class BicicletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BicicletaApplication.class, args);
	}

	 @Bean
	 public CommandLineRunner demo(IClienteDAO clienteDAO, BCryptPasswordEncoder encoder, ILocadoraDAO locadoraDAO, ILocacaoDAO locacaoDAO) {
	 	return (args) -> {
	 		/*Cliente admin = new Cliente();
	 		admin.setEmail("admin@ufscar.br");
	 		admin.setSenha(encoder.encode("admin"));
	 		admin.setCpf("111.111.111-11");
	 		admin.setNome("admin");
	 		admin.setTelefone("(11) 11111-1111");
	 		admin.setSexo("Masculino");
	 		admin.setDataNascimento("01/01/2000");
	 		admin.setPapel("ROLE_ADMIN");
	 		admin.setEnabled(true);
	 		clienteDAO.save(admin);

	 		Cliente cliente = new Cliente();
	 		cliente.setEmail("user@ufscar.br");
	 		cliente.setSenha(encoder.encode("user"));
	 		cliente.setCpf("222.222.222-22");
	 		cliente.setNome("user");
	 		cliente.setTelefone("(22) 22222-2222");
	 		cliente.setSexo("Feminino");
	 		cliente.setDataNascimento("02/02/2000");
	 		cliente.setPapel("ROLE_CLIENTE");
	 		cliente.setEnabled(true);
	 		clienteDAO.save(cliente);

	 		Locadora l1 = new Locadora();
	 		l1.setEmail("loc1@ufscar.br");
	 		l1.setSenha(encoder.encode("loc1"));
	 		l1.setCNPJ("11.111.111/1111-11");
	 		l1.setNome("Locadora 1");
	 		l1.setCidade("São Carlos");
	 		l1.setPapel("ROLE_LOCADORA");
	 		locadoraDAO.save(l1);

	 		Locadora l2 = new Locadora();
	 		l2.setEmail("loc2@ufscar.br");
	 		l2.setSenha(encoder.encode("loc2"));
	 		l2.setCNPJ("22.222.222/2222-22");
	 		l2.setNome("Locadora 2");
	 		l2.setCidade("Araraquara");
	 		l2.setPapel("ROLE_LOCADORA");
	 		locadoraDAO.save(l2);*/
	 	};
	 }
}
