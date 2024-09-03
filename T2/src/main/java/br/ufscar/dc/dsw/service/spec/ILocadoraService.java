package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.validation.CNPJAlreadyInUseException;

public interface ILocadoraService {
    Locadora buscarPorId(Long id);
    List<Locadora> buscarTodos();
    List<Locadora> buscarPorCidade(String cidade);
    List<String> buscarCidades();
    void salvar(Locadora locadora) throws CNPJAlreadyInUseException;
    void excluir(Long id);
    boolean locadoraTemLocacoes(Long id);
}
