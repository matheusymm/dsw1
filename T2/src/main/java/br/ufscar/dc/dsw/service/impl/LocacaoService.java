package br.ufscar.dc.dsw.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Service
@Transactional(readOnly = false)
public class LocacaoService implements ILocacaoService {

    @Autowired
    ILocacaoDAO dao;

    public void salvar(Locacao locacao) {
        dao.save(locacao);
    }

    @Transactional(readOnly = true)
    public Locacao buscarPorID(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly = true)
    public List<Locacao> buscarTodosPorCliente(Cliente user) {
        return dao.findAllByCliente(user);
    }
}