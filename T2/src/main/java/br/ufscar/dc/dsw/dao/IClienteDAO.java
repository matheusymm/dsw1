package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long> {
	Cliente findById(long id);
	Cliente findByCpf(String cpf);
	List<Cliente> findAll();
	Cliente save(Cliente cliente);
	void deleteById(Long id);

	@Query("SELECT c FROM Cliente c WHERE c.email = :email")
	public Cliente getClienteByEmail(String email);
}
