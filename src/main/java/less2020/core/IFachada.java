package less2020.core;

import less2020.core.aplicacao.Resultado;
import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;

public interface IFachada {

	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(IEntidade entidade);
	public Resultado visualizar(EntidadeDominio entidade);
	
	
}
