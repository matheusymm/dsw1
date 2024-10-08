package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Service
@Transactional(readOnly=false)
public class LocadoraService implements ILocadoraService {
    @Autowired
    private ILocadoraDAO dao;

    public void salvar(Locadora locadora) {
        dao.save(locadora);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly=true)
    public Locadora buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

    @Transactional(readOnly=true)
    public List<Locadora> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly=true)
    public List<Locadora> buscarPorCidade(String cidade) {
        return dao.findByCidade(cidade);
    }

    @Transactional(readOnly=true)
    public boolean locadoraTemLocacoes(Long id) {
        return !dao.findById(id.longValue()).getLocacoes().isEmpty();
    }

    @Transactional(readOnly=true)
    public List<String> buscarCidades() {
        return dao.findCidades();
    }
}
