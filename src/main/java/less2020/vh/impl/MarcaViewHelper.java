
package less2020.vh.impl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.IEntidade;
import less2020.dominio.Marca;
import less2020.vh.IViewHelper;

public class MarcaViewHelper implements IViewHelper {

	public IEntidade getEntidade(HttpServletRequest request) {

		return new Marca();
	}

	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		

	}

	public void setView(Resultado resultado, ServletConfig config) {
		config.getServletContext().setAttribute("resultadoMarca", resultado);
		
	}

}
