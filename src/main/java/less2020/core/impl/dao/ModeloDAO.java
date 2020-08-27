
package less2020.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;
import less2020.dominio.Modelo;

public class ModeloDAO extends AbstractJdbcDAO {
	
	public ModeloDAO() {
		super("tb_modelo", "id_mod");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Modelo modelo = (Modelo)entidade;
				
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_modelo(descricao) VALUES (?)");		
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, modelo.getDescricao());
			pst.executeUpdate();	
			
			ResultSet rs = pst.getGeneratedKeys();
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			modelo.setId(id);
			
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
		Modelo modelo = (Modelo)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_modelo SET descricao=? WHERE id_mod=?");				
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, modelo.getDescricao());
			pst.setInt(2, modelo.getId());
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
			
			Modelo modelo = (Modelo)entidade;
			String sql=null;
			
			if(modelo.getDescricao() == null){
				modelo.setDescricao("");
			}
			
			if(modelo.getId() == null && modelo.getDescricao().equals("")){
				sql = "SELECT * FROM tb_modelo";
			}else if(modelo.getId() != null && modelo.getDescricao().equals("")){
				sql = "SELECT * FROM tb_modelo WHERE id_mod=?";
			}else if(modelo.getId() == null && !modelo.getDescricao().equals("")){
				sql = "SELECT * FROM tb_marca WHERE descricao like ?";
			
			}	
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if(modelo.getId() != null && modelo.getDescricao().equals("")){
				pst.setInt(1, modelo.getId());
			}else if(modelo.getId() == null && !modelo.getDescricao().equals("")){
				pst.setString(1, "%"+modelo.getDescricao()+"%");			
			}
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> modelos = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Modelo m = new Modelo();
				m.setId(rs.getInt("id_mod"));
				m.setDescricao(rs.getString("descricao"));
								
				modelos.add(m);
			}
			return modelos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
