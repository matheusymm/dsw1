package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.security.LocadoraDetails;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/locadorasAAA")
public class LocadoraController {
	@Autowired
	private ILocadoraService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Locadora getAuthenticatedLocadora() {
		LocadoraDetails locadoraDetails = (LocadoraDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return locadoraDetails.getLocadora();
	}

    @GetMapping("")
	public String home() {
		return "locadora/home";
	}

	@GetMapping("/perfil")
    public String exibirPerfil(ModelMap model) {
        Locadora locadora = getAuthenticatedLocadora();
        model.addAttribute("locadora", locadora);
        return "locadora/formulario";
    }

    @PostMapping("/perfil")
    public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "locadora/formulario";
        }

        locadora.setSenha(encoder.encode(locadora.getSenha()));
        service.salvar(locadora);
        attr.addFlashAttribute("sucess", "Locacao alterado com sucesso.");

        return "redirect:/locadoras/perfil";
    }

    // CRUD de Locadoras
	@GetMapping("/cadastrar")
    public String cadastrarLocadora(Locadora locadora) {
        return "admin/cadastroLocadora";
    }

    @GetMapping("/listar")
    public String listarLocadora(ModelMap model) {
        model.addAttribute("locadoras", service.buscarTodos());
        return "admin/listaLocadora";
    }

    @PostMapping("/salvar")
    public String salvarLocadora(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroLocadora";
        }

		locadora.setSenha(encoder.encode(locadora.getSenha()));
		locadora.setPapel("ROLE_LOCADORA");
        service.salvar(locadora);
        attr.addFlashAttribute("sucess", "Locadora inserida com sucesso.");
        return "redirect:/admins/listarLocadora";
    }

    @GetMapping("/editar/{id}")
    public String preEditarLocadora(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("locadora", service.buscarPorId(id));
        return "admin/cadastroLocadora";
    }

    @PostMapping("/editar")
    public String editarLocadora(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroLocadora";
        }

        service.salvar(locadora);
        attr.addFlashAttribute("sucess", "Locadora editada com sucesso.");
        return "redirect:/admins/listarLocadora";
    }

    @GetMapping("/excluir/{id}")
    public String excluirLocadora(@PathVariable("id") Long id, ModelMap model) {
        if(service.locadoraTemLocacoes(id)) {
            model.addAttribute("fail", "Locadora não excluída. Possui locação(ões) vinculada(s).");
        } else {
            service.excluir(id);    
            model.addAttribute("sucess", "Locadora excluída com sucesso.");
        }
        return listarLocadora(model);
    }

    @GetMapping("/cidades") 
    public String lista(ModelMap model) {
        List<String> cidades = service.buscarCidades();
        model.addAttribute("cidades", cidades);
        model.addAttribute("locadoras", service.buscarTodos());
        return "lista";
    }

    @GetMapping("/cidades/{nome}")
    public String listaPorCidade(@RequestParam(value="nome", required=false) String cidade, ModelMap model) {
        List<Locadora> locadoras;

        if(cidade != null && !cidade.isEmpty()) {
            locadoras = service.buscarPorCidade(cidade);
        } else {
            locadoras = service.buscarTodos();
        }

        model.addAttribute("locadoras", locadoras);
        model.addAttribute("cidadeSelecionada", cidade);
        return "/lista";
    }
}
