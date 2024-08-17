package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("locadoras", serviceLocadora.buscarTodos());
        return "lista";
    }

    @GetMapping("/buscar")
    public String buscar(ModelMap model, String cidade) {
        model.addAttribute("locadoras", serviceLocadora.buscarPorCidade(cidade));
        return "/lista";
    }
}
