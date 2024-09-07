package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.service.spec.*;

@RestController
public class LocacaoRestController {
    @Autowired
    private ILocacaoService service;

    @Autowired
    private IClienteService serviceCliente;

    @Autowired
    private ILocadoraService serviceLocadora;

    @GetMapping(path="/locacoes")
    public ResponseEntity<List<Locacao>> lista() {
        List<Locacao> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path="/locacoes/{id}")
    @ResponseBody
    public ResponseEntity<Locacao> lista(@PathVariable("id") Long id) {
        Locacao locacao = service.buscarPorId(id);
        if (locacao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(locacao);
    }

    @GetMapping(path="/locacoes/clientes/{id}")
    @ResponseBody
    public ResponseEntity<List<Locacao>> listaPorCliente(@PathVariable("id") Long id) {
        Cliente cliente = serviceCliente.buscarPorId(id);
        List<Locacao> lista = service.buscarTodos(cliente);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path="/locacoes/locadoras/{id}")
    @ResponseBody
    public ResponseEntity<List<Locacao>> listaPorLocadora(@PathVariable("id") Long id) {
        Locadora locadora = serviceLocadora.buscarPorId(id);
        List<Locacao> lista = service.buscarTodos(locadora);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }
}
