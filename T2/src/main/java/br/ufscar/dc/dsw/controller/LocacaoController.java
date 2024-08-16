package br.ufscar.dc.dsw.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
// import br.ufscar.dc.dsw.security.ClienteDetails;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	
	@Autowired
	private ILocacaoService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao) {
		// locacao.setCliente(this.getCliente());
		locacao.setData("31/08/2020");
		//compra.setValor(compra.getLivro().getPreco());
		return "locacao/cadastro";
	}
	
	// private Cliente getCliente() {
	// 	ClienteDetails usuarioDetails = (ClienteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// return clienteDetails.getCliente();
	// }
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
					
		// model.addAttribute("locacoes",service.buscarTodos(this.getCliente()));
		
		return "locacao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Locacao locacao, BindingResult result, RedirectAttributes attr) {
		
		String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		// locacao.setCliente(this.getCliente());
		locacao.setData(data);
		
		service.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locacao inserida com sucesso.");
		return "redirect:/locacoes/listar";
	}
}