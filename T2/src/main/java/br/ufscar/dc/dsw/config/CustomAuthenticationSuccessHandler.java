package br.ufscar.dc.dsw.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // Lógica de redirecionamento personalizada
        String redirectUrl = "/home";

        // Verifique os papéis do usuário autenticado
        if (authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            redirectUrl = "/clientes/home";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("CLIENTE"))) {
            redirectUrl = "/clientes/home";
        } else if (authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("LOCADORA"))) {
            redirectUrl = "/locadoras/home";
        }

        // Redireciona o usuário para a URL determinada
        response.sendRedirect(redirectUrl);
    }
}
