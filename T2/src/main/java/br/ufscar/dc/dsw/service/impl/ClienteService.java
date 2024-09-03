package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.validation.CPFAlreadyInUseException;

@Service
@Transactional(readOnly=false)
public class ClienteService implements IClienteService {
	@Autowired
	private IClienteDAO dao;

	@Transactional(readOnly=true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly=true)
	public Cliente buscarPorCPF(String cpf) {
		return dao.findByCpf(cpf);
	}

	@Transactional(readOnly=true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Cliente cliente) throws CPFAlreadyInUseException {
		// Verificação de unicidade do CPF
        Cliente existingCliente = dao.findByCpf(cliente.getCpf());

        if (existingCliente != null && !existingCliente.getId().equals(cliente.getId())) {
            throw new CPFAlreadyInUseException("CPF já está em uso por outro cliente.");
        }

        dao.save(cliente);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}
}