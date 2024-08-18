package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/home")    
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
}
