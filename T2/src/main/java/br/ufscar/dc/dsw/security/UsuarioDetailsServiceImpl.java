package br.ufscar.dc.dsw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
 
public class UsuarioDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IClienteDAO dao;

    @Autowired  
    private ILocadoraDAO daoLocadora;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Cliente cliente = dao.findByEmail(username);

        if (cliente != null) {
            return new ClienteDetails(cliente);
        }

        Locadora locadora = daoLocadora.findByEmail(username);

        if (locadora != null) {
            return new LocadoraDetails(locadora);
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}