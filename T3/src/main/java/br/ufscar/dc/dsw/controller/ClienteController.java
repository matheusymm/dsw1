package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.security.ClienteDetails;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private IClienteService service;

    @Autowired
	private BCryptPasswordEncoder encoder;

    private Cliente getAuthenticatedCliente() {
        ClienteDetails clienteDetails = (ClienteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return clienteDetails.getCliente();
    }

    @GetMapping("")    
    public String home() {
        return "cliente/home";
    }               

    @GetMapping("/perfil")
    public String exibirPerfil(ModelMap model) {
        Cliente cliente = getAuthenticatedCliente();
        model.addAttribute("cliente", cliente);
        return "cliente/formulario";
    }

    @PostMapping("/perfil")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "cliente/formulario";
        }

        cliente.setSenha(encoder.encode(cliente.getSenha()));
        service.salvar(cliente);
        attr.addFlashAttribute("sucess", "Cliente alterado com sucesso.");

        return "redirect:/clientes/perfil";
    }

	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "admin/cadastroCli";
	}
	
	@GetMapping("/listar")
	public String listarCliente(ModelMap model) {
		model.addAttribute("clientes", service.buscarTodos());
		return "admin/listaCli";
	}

	@PostMapping("/salvar")
	public String salvarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "admin/cadastroCli";
		}
		
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		service.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso.");
		return "redirect:/admins/listarCli";
	}

	@GetMapping("/editar/{id}")
	public String preEditarCliente(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", service.buscarPorId(id));
		return "admin/cadastroCli";
	}
	
	@PostMapping("/editar")
	public String editarCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "admin/cadastroCli";
		}
		
		service.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/admins/listarCli";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirCliente(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "Cliente excluído com sucesso.");
		return listarCliente(model);
	}
}
