package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.validation.CPFAlreadyInUseException;
public interface IClienteService {
	Cliente buscarPorId(Long id);
	Cliente buscarPorCPF(String cpf);
	List<Cliente> buscarTodos();
	void salvar(Cliente cliente) throws CPFAlreadyInUseException;
	void excluir(Long id);
}
