package less2020.core.impl.negocio;

import less2020.core.IStrategy;
import less2020.dominio.Fornecedor;
import less2020.dominio.IEntidade;

public class ValidadorDadosObrigatoriosFornecedor implements IStrategy{

	@Override
	public String processar(IEntidade entidade) {
		
		if(entidade instanceof Fornecedor){
			Fornecedor fornecedor = (Fornecedor)entidade;
			
			String nome = fornecedor.getNome();
			String logradouro = fornecedor.getEndereco().getLogradouro();
			String cnpj = fornecedor.getCnpj();
			String cidade = fornecedor.getEndereco().getCidade().getNome();
			
			if(nome == null || logradouro == null || cnpj==null || cidade == null){
				return "Nome, logradouro, CNPJ e cidade s�o de preenchimento obrigat�rio!";
			}
			
			if(nome.trim().equals("") || logradouro.trim().equals("") || 
					cnpj.trim().equals("")|| cidade.trim().equals("")){
				return "Nome, logradouro, CNPJ e cidade s�o de preenchimento obrigat�rio!";
			}
			
		}else{
			return "Deve ser registrado um fornecedor!";
		}
		
		
		return null;
	}

}
