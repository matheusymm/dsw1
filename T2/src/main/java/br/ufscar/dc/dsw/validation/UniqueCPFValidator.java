package br.ufscar.dc.dsw.validation;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {
    @Autowired
    private IClienteDAO dao;
    
    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if(dao != null) {
            Cliente cliente = dao.findByCpf(cpf);
            
            return cliente == null;
        } else {
            return true;
        }
    }
}