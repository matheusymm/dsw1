package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
public class HomeController {
    @Autowired
    private ILocadoraService serviceLocadora;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/lista") 
    public String lista(ModelMap model) {
        List<String> cidades = serviceLocadora.buscarCidades();
        model.addAttribute("cidades", cidades);
        model.addAttribute("locadoras", serviceLocadora.buscarTodos());
        return "lista";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam(value="cidade", required=false) String cidade, ModelMap model) {
        List<Locadora> locadoras;

        if(cidade != null && !cidade.isEmpty()) {
            locadoras = serviceLocadora.buscarPorCidade(cidade);
        } else {
            locadoras = serviceLocadora.buscarTodos();
        }

        model.addAttribute("locadoras", locadoras);
        model.addAttribute("cidadeSelecionada", cidade);
        return "/lista";
    }
}
