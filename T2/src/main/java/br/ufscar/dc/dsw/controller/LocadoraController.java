package br.ufscar.dc.dsw.controller;

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

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.security.LocadoraDetails;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {
	@Autowired
	private ILocadoraService serviceLocadora;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Locadora getAuthenticatedLocadora() {
		LocadoraDetails locadoraDetails = (LocadoraDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return locadoraDetails.getLocadora();
	}

    @GetMapping("/home")
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
        serviceLocadora.salvar(locadora);
        attr.addFlashAttribute("sucess", "Locacao alterado com sucesso.");

        return "redirect:/locadoras/perfil";
    }
}
