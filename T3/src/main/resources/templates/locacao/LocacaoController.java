package br.ufscar.dc.dsw.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.security.ClienteDetails;
import br.ufscar.dc.dsw.security.LocadoraDetails;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
	@Autowired
	private ILocacaoService service;

	@Autowired
	private ILocadoraService serviceLocadora;

	@Autowired
	private ILocadoraService serviceCliente;

	private Cliente getCliente() {
		ClienteDetails clienteDetails = (ClienteDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return clienteDetails.getCliente();
	}

	private Locadora getLocadora() {
		LocadoraDetails locadoraDetails = (LocadoraDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return locadoraDetails.getLocadora();
	}
	
	@GetMapping("/cadastrarCliente")
	public String cadastrarCliente(ModelMap model, Locacao locacao) {
		model.addAttribute("locadoras", serviceLocadora.buscarTodos());
		locacao.setCliente(this.getCliente());
		locacao.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").toString());

		return "locacao/cadastro";
	}
	
	@GetMapping("/listarCliente")
	public String listarCliente(ModelMap model) {
		model.addAttribute("locacoes",service.buscarTodos(this.getCliente()));
		
		return "locacao/lista";
	}

	@GetMapping("/cadastrarLocadora")
	public String cadastrarLocadora(ModelMap model, Locacao locacao) {
		model.addAttribute("locadoras", serviceCliente.buscarTodos());
		locacao.setLocadora(this.getLocadora());
		locacao.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").toString());

		return "locacao/cadastro";
	}
	
	@GetMapping("/listarLocadora")
	public String listarLocadora(ModelMap model) {
		model.addAttribute("locacoes",service.buscarTodos(this.getLocadora()));
		
		return "locacao/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Locacao locacao, BindingResult result, RedirectAttributes attr) throws ParseException {
		String data = locacao.getData();
		LocalDateTime dataHora = LocalDateTime.parse(data);

		int min = dataHora.getMinute();

		if(min != 0) {
			dataHora = dataHora.plusHours(1);
		}
		dataHora = dataHora.withMinute(0).withSecond(0);

		data = dataHora.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
		
		locacao.setCliente(this.getCliente());
		locacao.setData(data);

		Locacao locacaoExistente = service.buscarPorClienteELocadoraEData(locacao.getCliente(), locacao.getLocadora(), locacao.getData());

		if(locacaoExistente != null) {
			attr.addFlashAttribute("error", "Já existe uma locação para este cliente ou esta locadora nesta data e hora.");
			return "redirect:/locacoes/cadastrar";
		}
		
		service.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locacao inserida com sucesso.");
		
		return "redirect:/locacoes/listar";
	}
}