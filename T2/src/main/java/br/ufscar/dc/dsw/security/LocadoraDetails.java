package br.ufscar.dc.dsw.security;

import java.util.Arrays;
import java.util.Collection;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.domain.Locadora;
 
@SuppressWarnings("serial")
public class LocadoraDetails implements UserDetails {
    private Locadora locadora;
     
    public LocadoraDetails(Locadora locadora) {
        this.locadora = locadora;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(locadora.getPapel());
        return Arrays.asList(authority);
    }
 
    @Override
    public String getPassword() {
        return locadora.getSenha();
    }
 
    @Override
    public String getUsername() {
        String username = locadora.getEmail();
        username = username.substring(0, username.indexOf('@'));
        return username;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }

	public Locadora getLocadora() {
		return locadora;
	}
}