package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@RestController
public class LocadoraRestController {
	
	@Autowired
	private ILocadoraService service;
	
	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		}catch(IOException e) {
			return false;
		}
	}

	private void parse(Locadora locadora, JSONObject json) {
		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				locadora.setId(((Integer) id).longValue());
			}else {
				locadora.setId(((Long)id));
			}
		}
		locadora.setCidade((String) json.get("cidade"));//
		locadora.setCNPJ((String) json.get("cnpj"));
		locadora.setEmail((String) json.get("email"));
		locadora.setNome((String) json.get("nome"));//
		locadora.setPapel((String) json.get("papel"));//
		locadora.setSenha((String) json.get("senha"));//
	}
	
	@GetMapping(path = "/locadoras")
	public ResponseEntity<List<Locadora>> lista() {
		List<Locadora> lista = service.buscarTodos();
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> lista(@PathVariable("id") long id){
		Locadora locadora = service.buscarPorId(id);
		if(locadora == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locadora);
	}

	@GetMapping(path = "/locadoras/cidade/{cidade}")
	public ResponseEntity<List<Locadora>> lista(@PathVariable("cidade") String cidade){
		if(cidade == null || cidade.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		List<Locadora> lista = service.buscarPorCidade(cidade);
		if(lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping(path ="/locadoras")
	@ResponseBody
	public ResponseEntity<Locadora> cria(@RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toString())) {
				Locadora locadora = new Locadora();
				parse(locadora, json);
				service.salvar(locadora);
				return ResponseEntity.ok(locadora);
			}else{
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	@PutMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> atualiza(@PathVariable("id")long id, @RequestBody JSONObject json){
		try {
			if(isJSONValid(json.toJSONString())) {
				Locadora locadora = new Locadora();
				parse(locadora, json);
				service.salvar(locadora);
				return ResponseEntity.ok(locadora);
				
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	@DeleteMapping(path = "/locadoras/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Locadora locadora = service.buscarPorId(id);
		if (locadora == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}

}
