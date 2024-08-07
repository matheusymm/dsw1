package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(urlPatterns = "/adminsCliente/*")
public class ClienteAdminController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClienteDAO dao;

    @Override
    public void init() {
        dao = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cliente admin = (Cliente) request.getSession().getAttribute("clienteLogado");
        Erro erros = new Erro();

        if(admin == null) {
            response.sendRedirect(request.getContextPath() + "/loginCliente");
            return;
        } 

        if(!admin.getPapel().equals("admin")) {
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
        List<Cliente> listaClientes = dao.getAll();
        request.setAttribute("listaClientes", listaClientes);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/listaAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private Map<Long, String> getLocacao() {
        Map <Long,String> locacao = new HashMap<>();
        // for (Locacao loc: new LocacaoDAO().getAll()) {
        //     locacao.put(loc.getId(), loc.getNome());
        // }
        return locacao;
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/formularioAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Cliente cliente = dao.getById(id);
        request.setAttribute("cliente", cliente);
        request.setAttribute("Locacao", getLocacao());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/cliente/formularioAdmin.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
        String papel = request.getParameter("papel");
        
        Cliente cliente = new Cliente(email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
        dao.insert(cliente);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(request.getParameter("id"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));
        String papel = request.getParameter("papel");
        
        // Long locacaoID = Long.parseLong(request.getParameter("Locacao"));
        // Locacao locacao = new LocacaoDAO().get(locacaoID);
        
        Cliente cliente = new Cliente(id, email, senha, nome, cpf, telefone, sexo, dataNascimento, papel);
        dao.update(cliente);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Cliente cliente = new Cliente(id);
        dao.delete(cliente);
        response.sendRedirect("lista");
    }
}
