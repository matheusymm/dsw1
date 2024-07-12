package br.com.locacao.controller.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.locacao.persistencia.entidade.Usuario;

//talvez colocar "adm" na frente do nove de cada servlet para bloquear
@WebFilter(dispatcherTypes= {DispatcherType.REQUEST}, urlPatterns={ "/IndexUsu/*"}) //está filtrando tudo desde o inicio "/*" marca a raiz do projeto
public class FiltroAutenticacaoUsu implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		HttpSession sessao = httpRequest.getSession(false);
		
		boolean naoLogado = true;
		if(httpRequest.getSession().getAttribute("usuAutenticado") == null) {
			System.out.println("ainda não autenticado");
			naoLogado = true;
		}
		else {
			naoLogado = false;
		}
			
		//bloqueia o acesso a outras áreas da navegação
		if(((sessao!=null)&&(naoLogado==false)) || uri.lastIndexOf("Entrada.html")!=-1||uri.indexOf("/fabricaweb/IndexUsu")!=-1) {
			chain.doFilter(request, response);
		}else {
			System.out.println(uri);
			httpResponse.sendRedirect("Entrada.html");
		}
	}

	@Override
	public void destroy() {

	}

}


