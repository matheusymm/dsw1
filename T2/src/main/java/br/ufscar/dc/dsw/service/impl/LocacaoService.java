package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.domain.Locadora;

@Service
@Transactional(readOnly = false)
public class LocacaoService implements ILocacaoService {
    @Autowired
    ILocacaoDAO dao;

    public void salvar(Locacao locacao) {
        dao.save(locacao);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Locacao buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Locacao> buscarTodos(Cliente cliente) {
        return dao.findAllByCliente(cliente);
    }

    @Transactional(readOnly = true)
    public Locacao buscarPorClienteELocadoraEData(Cliente cliente, Locadora locadora, String data) {
        return dao.findByClienteAndLocadoraAndData(cliente, locadora, data);
    }
}
