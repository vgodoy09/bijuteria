
package less2020.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import less2020.dominio.Cliente;
import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;

public class ClienteDAO extends AbstractJdbcDAO {
	
	public ClienteDAO() {
		super("tb_cliente", "id_cli");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;
		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_cliente(nome, cpf, ");
			sql.append("dt_cadastro) VALUES (?,?,?)");		
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			Timestamp time = new Timestamp(cliente.getDtCadastro().getTime());
			pst.setTimestamp(3, time);
			pst.executeUpdate();			
			connection.commit();		
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}finally{
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		

	}
	/** 
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
	}
	/** 
	 * TODO Descri��o do M�todo
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(IEntidade entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	

}
