
package less2020.vh.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.IEntidade;
import less2020.vh.IViewHelper;



public class HomeViewHelper implements IViewHelper {

	public IEntidade getEntidade(HttpServletRequest request) {
		
		return null;
	}

	
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response)  throws ServletException {

		try {
			response.sendRedirect("home.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}


	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
