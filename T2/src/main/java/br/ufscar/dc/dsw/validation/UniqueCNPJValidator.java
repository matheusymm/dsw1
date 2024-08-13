package br.ufscar.dc.dsw.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {
    @Autowired
    private ILocadoraDAO dao;

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if(dao != null) {
            Locadora locadora = dao.findByCNPJ(cnpj);
            return locadora == null;
        } else {
            return true;
        }
    }
}
