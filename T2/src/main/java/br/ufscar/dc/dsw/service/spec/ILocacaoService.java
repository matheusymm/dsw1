package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocacaoService {
    Locacao buscarPorId(Long id);
    List<Locacao> buscarTodos(Cliente cliente);
    void salvar(Locacao locadora);
    void excluir(Long id);
    Locacao buscarPorClienteELocadoraEData(Cliente cliente, Locadora locadora, String data);
}
