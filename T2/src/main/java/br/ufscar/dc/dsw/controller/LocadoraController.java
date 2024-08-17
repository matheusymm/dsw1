package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {
    @Autowired
    private ILocadoraService service;

    @GetMapping("/home")
	public String home() {
		return "locadora/home";
	}

    @GetMapping("/cadastrar")
    public String cadastrar(Locadora locadora) {
        return "locadora/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("locadoras", service.buscarTodos());
        return "locadora/lista";
    }

    // TODO: Não aparece a mensagem de sucesso ao cadastrar uma locadora
    @PostMapping("/salvar")
    public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "locadora/cadastro";
        }

        service.salvar(locadora);
        attr.addFlashAttribute("success", "Locadora inserida com sucesso.");
        
        return "redirect:/locadoras/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("locadora", service.buscarPorId(id));
        return "locadora/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "locadora/cadastro";
        }

        service.salvar(locadora);
        attr.addFlashAttribute("success", "Locadora editada com sucesso.");
        return "redirect:/locadoras/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        if(service.locadoraTemLocacoes(id)) {
            model.addAttribute("fail", "Locadora não excluída. Possui locação(ões) vinculada(s).");
        } else {
            service.excluir(id);    
            model.addAttribute("success", "Locadora excluída com sucesso.");
        }
        return listar(model);
    }
}
