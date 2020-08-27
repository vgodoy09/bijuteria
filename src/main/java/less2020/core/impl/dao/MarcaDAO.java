
package less2020.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;
import less2020.dominio.Marca;

public class MarcaDAO extends AbstractJdbcDAO {
	
	public MarcaDAO() {
		super("tb_marca", "id_mar");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Marca marca = (Marca)entidade;
				
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_marca(descricao) VALUES (?)");		
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, marca.getDescricao());
			pst.executeUpdate();	
			
			ResultSet rs = pst.getGeneratedKeys();
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			marca.setId(id);
			
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
		openConnection();
		PreparedStatement pst=null;
		Marca marca = (Marca)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_marca SET descricao=? WHERE id_mar=?");				
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, marca.getDescricao());
			pst.setInt(2, marca.getId());
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
			PreparedStatement pst = null;
			
			Marca marca = (Marca)entidade;
			String sql=null;
			
			if(marca.getDescricao() == null){
				marca.setDescricao("");
			}
			
			if(marca.getId() == null && marca.getDescricao().equals("")){
				sql = "SELECT * FROM tb_marca";
			}else if(marca.getId() != null && marca.getDescricao().equals("")){
				sql = "SELECT * FROM tb_marca WHERE id_mar=?";
			}else if(marca.getId() == null && !marca.getDescricao().equals("")){
				sql = "SELECT * FROM tb_marca WHERE descricao like ?";
			
			}	
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(marca.getId() != null && marca.getDescricao().equals("")){
				pst.setInt(1, marca.getId());
			}else if(marca.getId() == null && !marca.getDescricao().equals("")){
				pst.setString(1, "%"+marca.getDescricao()+"%");			
			}
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> marcas = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Marca m = new Marca();
				m.setId(rs.getInt("id_mar"));
				m.setDescricao(rs.getString("descricao"));
								
				marcas.add(m);
			}
			return marcas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
