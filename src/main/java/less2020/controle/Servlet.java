package less2020.controle;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import less2020.command.ICommand;
import less2020.command.impl.AlterarCommand;
import less2020.command.impl.ConsultarCommand;
import less2020.command.impl.ExcluirCommand;
import less2020.command.impl.SalvarCommand;
import less2020.command.impl.VisualizarCommand;
import less2020.core.aplicacao.Resultado;
import less2020.dominio.IEntidade;
import less2020.vh.IViewHelper;
import less2020.vh.impl.ClienteViewHelper;
import less2020.vh.impl.FornecedorViewHelper;
import less2020.vh.impl.HomeViewHelper;
import less2020.vh.impl.MarcaViewHelper;
import less2020.vh.impl.ModeloViewHelper;
import less2020.vh.impl.ProdutoViewHelper;

/**
 * Servlet implementation class Servlet
 */
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	private static String uri = null;
	private static HttpServletRequest request;
	private static String operacao = null;

	private static IViewHelper vh;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// Parametros definidos como <init-param> no web.xml
		Enumeration<String> parametros = config.getInitParameterNames();

		while (parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			uri = config.getInitParameter(parametro);
			Resultado resultado = doProcess();

			/*
			 * Executa o m�todo setView do view helper espec�fico passando o resultado
			 * da consulta com os dados de dom�nio que ser� colocado no contexto para montar 
			 * as combos
			 */
			vh.setView(resultado, config);
		}	
		
		

	}

	/**
	 * Default constructor.
	 */
	public Servlet() {

		/*
		 * Utilizando o command para chamar a fachada e indexando cada command pela
		 * opera��o garantimos que esta servelt atender� qualquer opera��o
		 */
		commands = new HashMap<String, ICommand>();

		commands.put("SALVAR", new SalvarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());

		ConsultarCommand consultarCMD = new ConsultarCommand();
		commands.put("CONSULTAR", consultarCMD);
		commands.put("PREPARAR_PRODUTO", consultarCMD);

		commands.put("VISUALIZAR", new VisualizarCommand());
		commands.put("ALTERAR", new AlterarCommand());

		/*
		 * Utilizando o ViewHelper para tratar especifica��es de qualquer tela e
		 * indexando cada viewhelper pela url em que esta servlet � chamada no form
		 * garantimos que esta servelt atender� qualquer entidade
		 */

		vhs = new HashMap<String, IViewHelper>();
		/*
		 * A chave do mapa � o mapeamento da servlet para cada form que est� configurado
		 * no web.xml e sendo utilizada no action do html
		 */
		vhs.put("/les22018-web/SalvarFornecedor", new FornecedorViewHelper());
		vhs.put("/les22018-web/SalvarCliente", new ClienteViewHelper());
		vhs.put("/les22018-web/SalvarProduto", new ProdutoViewHelper());
		vhs.put("/les22018-web/ConsultarMarca", new MarcaViewHelper());
		vhs.put("/les22018-web/ConsultarModelo", new ModeloViewHelper());
		vhs.put("/les22018-web/", new HomeViewHelper());

	}

	/**
	 * TODO Descri��o do M�todo
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	@SuppressWarnings("static-access")
	protected void doProcessRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Obt�m a uri que invocou esta servlet (O que foi definido no methdo do form
		// html)
		uri = request.getRequestURI();

		this.request = request;

		// Obt�m a opera��o executada
		operacao = request.getParameter("operacao");

		Resultado resultado = doProcess();

		/*
		 * Executa o m�todo setView do view helper espec�fico para definir como dever�
		 * ser apresentado o resultado para o usu�rio
		 */
		vh.setView(resultado, request, response);
	}

	protected Resultado doProcess() throws ServletException {

		// Obt�m um viewhelper indexado pela uri que invocou esta servlet
		vh = vhs.get(uri);

		// O viewhelper retorna a entidade especifica para a tela que chamou esta
		// servlet
		IEntidade entidade = vh.getEntidade(request);

		// Obt�m a opera��o executada

		if (request == null) {
			operacao = "CONSULTAR";
		} else {
			operacao = request.getParameter("operacao");
		}

		if(operacao != null) {
			// Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(operacao);

			/*
			 * Executa o command que chamar� a fachada para executar a opera��o requisitada
			 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro
			 * ou entidades de retorno
			 */
			Resultado resultado = command.execute(entidade);

			return resultado;
		}
		
		return null;
	}
}
