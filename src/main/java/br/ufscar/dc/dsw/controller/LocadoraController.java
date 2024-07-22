package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.util.Email;
import br.ufscar.dc.dsw.util.Erro;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;

@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private LocadoraDAO dao;
    private ClienteDAO daoCliente;
    private LocacaoDAO daoLocacao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
        daoLocacao = new LocacaoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {                
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insereLocacao":
                    insereLocacao(request, response);
                    break;
                case "/novo":
                    apresentaFormRegistro(request, response);
                    break;
                case "/insercaoLocadora":
                    insereLocadora(request, response);
                    break;
                case "/listaLocadoras":
                    listaLocadoras(request, response);
                    break;
                case "/buscaCidade":
                    listaLocadorasCidade(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                	//atualize(request, response);criar o caso atualize
                    break;
                case "/atualize":
                	atualize(request, response);
                	break;
                case "/remocao":
                    remove(request, response);
                    break;
                default:
                    listaLocacoes(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	String dataLocacaoStr = request.getParameter("dataLocacao");
    	LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);
    	String cnpj = request.getParameter("cnpjLocadora");
    	LocacaoDAO locacaoDAO = new LocacaoDAO();
    	Locacao locacao = locacaoDAO.getBydataLocacao(dataLocacao, cnpj);
    	request.setAttribute("locacao", locacao);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/formEdicao.jsp");
		dispatcher.forward(request, response);
	}

	private void listaLocacoes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Locacao> listaLocacoes = daoLocacao.getByCnpj(((Locadora) session.getAttribute("locadoraLogada")).getCnpj());
        request.setAttribute("listaLocacoes", listaLocacoes);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/listaLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaParaCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocadorasCidade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidade = request.getParameter("cidade");
        List<Locadora> listaLocadoras = dao.getByCidade(cidade);
        request.setAttribute("listaLocadoras", listaLocadoras);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaParaCliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String cnpjLocadora = ((Locadora) session.getAttribute("locadoraLogada")).getCnpj();
        request.setAttribute("cnpjLocadora", cnpjLocadora);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/formularioLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void insereLocacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        String dataLocacaoStr = request.getParameter("dataLocacao");
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);
        Erro erros = new Erro();

        Locacao locacao = new Locacao(cpfCliente, cnpjLocadora, dataLocacao);
        Boolean inseriu = daoLocacao.insert(locacao);

        if(inseriu){
            Cliente cliente = daoCliente.getByCPF(cpfCliente);
            Locadora locadora = dao.getByCNPJ(cnpjLocadora);
            String assunto = "Nova Locação de Bicicleta";
            String mensagem = "Uma nova locação foi realizada por " + cliente.getNome() + " para a locadora " + locadora.getNome() + " no dia " + dataLocacaoStr;
            Email.sendEmail(assunto, cliente.getEmail(), mensagem);
            Email.sendEmail(assunto, locadora.getEmail(), mensagem);
        } else {
            erros.add("Já existe uma locação nesta data");
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            return;
        }

        response.sendRedirect("lista");
    }

    private void apresentaFormRegistro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registro/registraLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void insereLocadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");
        String papel = "locadora";

        Locadora locadora = new Locadora(email, senha, cnpj, nome, cidade, papel);

        dao.insert(locadora);
        
        response.sendRedirect(request.getContextPath() + "/loginLocadora");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String cpfCliente = request.getParameter("cpfCliente");
        String cnpjLocadora = request.getParameter("cnpjLocadora");
        String dataLocacaoStr = request.getParameter("dataLocacao");
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr);
        
        Locacao locacao = new Locacao(id, cpfCliente, cnpjLocadora, dataLocacao);
        daoLocacao.update(locacao);
        response.sendRedirect("listaAdmin");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Locacao locacao = new Locacao(id);
        daoLocacao.delete(locacao);
        response.sendRedirect("lista");
    }
}
