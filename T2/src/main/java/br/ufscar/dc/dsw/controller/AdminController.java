package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admins")
public class AdminController {
	@Autowired
	private IClienteService serviceCliente; 

	@Autowired
	private ILocadoraService serviceLocadora;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/home")
	public String home(ModelMap model) {
		return "admin/home";
	}

	// CRUD de Clientes
	@GetMapping("/cadastrarCli")
	public String cadastrar(Cliente cliente) {
		return "admin/cadastroCli";
	}
	
	@GetMapping("/listarCli")
	public String listarCliente(ModelMap model) {
		model.addAttribute("clientes", serviceCliente.buscarTodos());
		return "admin/listaCli";
	}

	@PostMapping("/salvarCli")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroCli";
		}
		
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		serviceCliente.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");
		return "redirect:/admins/listarCli";
	}

	@GetMapping("/editarCli/{id}")
	public String preEditarCliente(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", serviceCliente.buscarPorId(id));
		return "admin/cadastroCli";
	}
	
	@PostMapping("/editarCli")
	public String editarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "admin/cadastroCli";
		}
		
		serviceCliente.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admins/listarCli";
	}
	
	@GetMapping("/excluirCli/{id}")
	public String excluirCliente(@PathVariable("id") Long id, ModelMap model) {
		serviceCliente.excluir(id);
		model.addAttribute("sucess", "Cliente excluído com sucesso.");
		return listarCliente(model);
	}

	// CRUD de Locadoras
	@GetMapping("/cadastrarLocadora")
    public String cadastrarLocadora(Locadora locadora) {
        return "admin/cadastroLocadora";
    }

    @GetMapping("/listarLocadora")
    public String listarLocadora(ModelMap model) {
        model.addAttribute("locadoras", serviceLocadora.buscarTodos());
        return "admin/listaLocadora";
    }

    @PostMapping("/salvarLocadora")
    public String salvarLocadora(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroLocadora";
        }

		locadora.setSenha(encoder.encode(locadora.getSenha()));
		locadora.setPapel("ROLE_LOCADORA");
        serviceLocadora.salvar(locadora);
        attr.addFlashAttribute("success", "Locadora inserida com sucesso.");
        
        return "redirect:/admins/listarLocadora";
    }

    @GetMapping("/editarLocadora/{id}")
    public String preEditarLocadora(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("locadora", serviceLocadora.buscarPorId(id));
        return "admin/cadastroLocadora";
    }

    @PostMapping("/editarLocadora")
    public String editarLocadora(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastroLocadora";
        }

        serviceLocadora.salvar(locadora);
        attr.addFlashAttribute("success", "Locadora editada com sucesso.");
        return "redirect:/admins/listarLocadora";
    }

    @GetMapping("/excluirLocadora/{id}")
    public String excluirLocadora(@PathVariable("id") Long id, ModelMap model) {
        if(serviceLocadora.locadoraTemLocacoes(id)) {
            model.addAttribute("fail", "Locadora não excluída. Possui locação(ões) vinculada(s).");
        } else {
            serviceLocadora.excluir(id);    
            model.addAttribute("success", "Locadora excluída com sucesso.");
        }
        return listarLocadora(model);
    }
}
