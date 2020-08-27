package less2020.core.impl.negocio;

import less2020.core.IStrategy;
import less2020.dominio.Fornecedor;
import less2020.dominio.IEntidade;

public class ValidadorCnpj implements IStrategy {

	@Override
	public String processar(IEntidade entidade) {
		
		if(entidade instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor)entidade;
			
			if(fornecedor.getCnpj().length() < 14){
				return "CNPJ deve conter 14 digitos!";
			}
			
		}else{
			return "CNPJ n�o pode ser v�lidado, pois entidade n�o � um fornecedor!";
		}
		
		
		return null;
	}

}
