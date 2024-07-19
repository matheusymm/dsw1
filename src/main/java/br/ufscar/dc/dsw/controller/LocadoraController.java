package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Locadora locadora = (Locadora) request.getSession().getAttribute("locadoraLogada");
        Erro erros = new Erro();

        if(locadora == null) {
            listaLocadoras(request, response);
            return;
        }

        if(!locadora.getPapel().equals("admin")) {
            erros.add("Acesso não autorizado!");
			erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
			request.setAttribute("mensagens", erros);
			RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
			rd.forward(request, response);
			return;
        }
                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaLocadoras", dao.getAll());
        request.getRequestDispatcher("/logado/locadora/lista.jsp").forward(request, response);
    }

    private void listaLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listaLocadoras", dao.getAll());
        request.getRequestDispatcher("/listaParaCliente.jsp").forward(request, response);
    }

    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/logado/locadora/formCadastro.jsp").forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");
        String papel = request.getParameter("papel");

        Locadora locadora = new Locadora(email, senha, cnpj, nome, cidade, papel);
        dao.insert(locadora);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Locadora locadora = new Locadora(id);
        dao.delete(locadora);
        response.sendRedirect("lista");
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("locadora", dao.getById(id));
        request.getRequestDispatcher("/logado/locadora/formulario.jsp").forward(request, response);
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");
        String papel = request.getParameter("papel");

        Locadora locadora = new Locadora(id, email, senha, cnpj, nome, cidade, papel);
        dao.update(locadora);
        response.sendRedirect("lista");
    }
}
