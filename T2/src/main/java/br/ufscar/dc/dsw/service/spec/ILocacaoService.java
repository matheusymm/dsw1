package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;

public interface ILocacaoService {
    Locacao buscarPorId(Long id);
    List<Locacao> buscarTodos(Cliente user);
    void salvar(Locacao locadora);
}
