// package br.ufscar.dc.dsw.filter;

// import java.io.IOException;

// import javax.servlet.DispatcherType;
// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.FilterConfig;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.annotation.WebFilter;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import br.ufscar.dc.dsw.domain.Cliente;

// //talvez colocar "adm" na frente do nove de cada servlet para bloquear
// @WebFilter(dispatcherTypes= {DispatcherType.REQUEST}, urlPatterns={"/clientes/*", "/locacoes/*", "/locadoras/*"}) //está filtrando tudo desde o inicio "/*" marca a raiz do projeto
// public class ClienteFilter implements Filter{

// 	@Override
// 	public void init(FilterConfig filterConfig) throws ServletException {

// 	}

// 	@Override
// 	public void doFilter(ServletRequest request, ServletResponse response,
// 			FilterChain chain)
// 			throws IOException, ServletException {
// 		HttpServletRequest httpRequest = (HttpServletRequest) request;
// 		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
// 		String uri = httpRequest.getRequestURI();
// 		HttpSession sessao = httpRequest.getSession(false);
// 		String RoleSession = "admin";
		
// 		boolean naoLogado = true;
// 		if(httpRequest.getSession().getAttribute("clienteLogado") == null) {
// 			System.out.println("ainda não autenticado");
// 			naoLogado = true;
// 		}
// 		else {
// 			naoLogado = false;
// 			Cliente usu = (Cliente)httpRequest.getSession().getAttribute("clienteLogado");
// 			RoleSession = usu.getPapel();
// 		}
	
// 		//bloqueia o acesso a outras áreas da navegação
// 		if ((sessao!=null)&&(naoLogado==false)&&(RoleSession.equals("admin")) || uri.lastIndexOf("home.jsp")!=-1) {
// 			chain.doFilter(request, response);
// 		}else {
// 			System.out.println(uri);
// 			httpResponse.sendRedirect("home.jsp");
// 		}
// 	}

// 	@Override
// 	public void destroy() {

// 	}

// }