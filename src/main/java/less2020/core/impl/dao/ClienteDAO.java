
package less2020.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
		openConnection();
		PreparedStatement pst=null;
		Cliente cliente = (Cliente)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_cliente SET nome=?, cpf=? ");
			sql.append("WHERE id_cli=?");				
			
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setInt(3, cliente.getId());
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
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(IEntidade entidade) {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;
		
		Cliente filtroCliente = (Cliente)entidade;
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append(" FROM tb_cliente WHERE id=? or name like ?");
	
	
	try {
		openConnection();
		pst = connection.prepareStatement(sql.toString());
		
			pst.setInt(1, filtroCliente.getId());
			pst.setString(2, "%"+filtroCliente.getNome()+"%");			
		
		ResultSet rs = pst.executeQuery();
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		while (rs.next()) {
			Cliente c = new Cliente();
			c.setId(rs.getInt("id"));
			c.setNome(rs.getString("nome"));
			c.setCpf(rs.getString("cpf"));
			
			java.sql.Date dtCadastroEmLong = rs.getDate("dt_cadastro");
			Date dtCadastro = new Date(dtCadastroEmLong.getTime());				
			c.setDtCadastro(dtCadastro);
			clientes.add(c);
		}
		return clientes;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
}
