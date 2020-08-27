package less2020.core;

import java.sql.SQLException;
import java.util.List;

import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;

public interface IDAO {

	public void salvar(EntidadeDominio entidade) throws SQLException;
	public void alterar(EntidadeDominio entidade)throws SQLException;
	public void excluir(EntidadeDominio entidade)throws SQLException;
	public List<EntidadeDominio> consultar(IEntidade entidade)throws SQLException;
	
	
}
