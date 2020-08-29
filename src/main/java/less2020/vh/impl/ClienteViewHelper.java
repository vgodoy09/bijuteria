
package less2020.vh.impl;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.Cliente;
import less2020.dominio.EntidadeDominio;
import less2020.vh.IViewHelper;



public class ClienteViewHelper implements IViewHelper {

	/** 
	 * TODO Descri��o do M�todo
	 * @param request
	 * @param response
	 * @return
	 * @see les22018.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String nome = "Thiago";//request.getParameter("txtNome");
		String cpf = "809890890";//request.getParameter("txtCpf");
		String id = "1";//request.getParameter("txtId");
		
		
		Cliente c = new Cliente();
		c.setNome(nome);
		
		if(id != null && !id.trim().equals("")){
			c.setId(Integer.parseInt(id));
		}
		
		c.setCpf(cpf);
		return c;
	}

	/** 
	 * TODO Descri��o do M�todo
	 * @param request
	 * @param response
	 * @return
	 * @see les22018.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, 
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public void setView(Resultado resultado, ServletConfig config) {
		// TODO Auto-generated method stub
		
	}

}
