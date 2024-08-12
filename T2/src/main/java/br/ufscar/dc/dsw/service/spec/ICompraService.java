package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;

public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	List<Locacao> buscarTodosPorCliente(Cliente user);
	
	void salvar(Locacao editora);
}