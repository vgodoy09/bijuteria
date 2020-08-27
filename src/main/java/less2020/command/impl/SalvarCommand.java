
package less2020.command.impl;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;


public class SalvarCommand extends AbstractCommand{

	
	public Resultado execute(IEntidade entidade) {
		
		return fachada.salvar((EntidadeDominio)entidade);
	}

}
