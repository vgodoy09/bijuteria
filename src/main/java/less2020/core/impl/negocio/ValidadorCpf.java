package less2020.core.impl.negocio;

import less2020.core.IStrategy;
import less2020.dominio.Cliente;
import less2020.dominio.IEntidade;

public class ValidadorCpf implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		
		if(entidade instanceof Cliente){
			Cliente c = (Cliente)entidade;
			
			if(c.getCpf().length() < 9){
				return "CPF deve conter 14 digitos!";
			}
			
		}else{
			return "CPF n�o pode ser v�lidado, pois entidade n�o � um cliente!";
		}
		
		
		return null;
	}

}
