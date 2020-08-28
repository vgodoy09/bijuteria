package less2020.core.impl.naofuncionais;

import java.util.Date;

import less2020.core.IStrategy;
import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;

public class ComplementarDtCadastro implements IStrategy {

	@SuppressWarnings("null")
	@Override
	public String processar(IEntidade entidade) {		
		
		
		if(entidade !=null){
			Date data = new Date();		
			((EntidadeDominio)entidade).setDtCadastro(data);
		}else{
			return "Entidade: "+entidade.getClass().getCanonicalName()+" nula!";
		}
		
		
		
		return null;
	}

}
