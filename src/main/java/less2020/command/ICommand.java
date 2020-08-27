
package less2020.command;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.IEntidade;


public interface ICommand {

	public Resultado execute(IEntidade entidade);
	
}
