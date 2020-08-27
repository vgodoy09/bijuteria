
package less2020.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import less2020.dominio.EntidadeDominio;
import less2020.dominio.IEntidade;
import less2020.dominio.Marca;
import less2020.dominio.Modelo;
import less2020.dominio.Produto;

public class ProdutoDAO extends AbstractJdbcDAO {
	
	public ProdutoDAO() {
		super("tb_produto", "id_pro");		
	}
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst=null;
		Produto produto = (Produto)entidade;
		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO produto(descricao, quantidade, ");
			sql.append("dt_cadastro, marca_id, modelo_id) VALUES (?,?,?,?,?)");		
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, produto.getDescricao());
			pst.setInt(2, produto.getQuantidade());
			Timestamp time = new Timestamp(produto.getDtCadastro().getTime());
			pst.setTimestamp(3, time);
			pst.setInt(4, produto.getMarca().getId());
			pst.setInt(5, produto.getModelo().getId());
			pst.executeUpdate();	
			
			ResultSet rs = pst.getGeneratedKeys();
			int id=0;
			if(rs.next())
				id = rs.getInt(1);
			produto.setId(id);
			
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
		Produto produto = (Produto)entidade;		
		
		try {
			connection.setAutoCommit(false);			
					
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_produto SET descricao=?, quantidade=?, mar_id=?, mod_id=? ");
			sql.append("WHERE id_pro=?");				
			
					
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, produto.getDescricao());
			pst.setInt(2, produto.getQuantidade());
			pst.setInt(3, produto.getMarca().getId());
			pst.setInt(4, produto.getModelo().getId());
			pst.setInt(5, produto.getId());
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
			
			Produto filtroProduto = (Produto)entidade;
			
			
			if(filtroProduto.getDescricao() == null){
				filtroProduto.setDescricao("");
			}
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * ");
			sql.append(" FROM produto as prd WHERE id=? and descricao like ?");
			
				
//			if(filtroProduto.getId() != null && filtroProduto.getDescricao().equals("")){
//				sql.append(" id=?");
//			}else if(filtroProduto.getId() == null && !filtroProduto.getDescricao().equals("")){
//				sql.append(" and descricao like ?");		
//			}
			
//			if(filtroProduto.getMarca() != null) {
//				sql.append(" and id_mar=");
//				sql.append(filtroProduto.getMarca().getId());
//			}
//			
//			if(filtroProduto.getModelo() != null) {
//				sql.append(" and id_mod=");
//				sql.append(filtroProduto.getModelo().getId());
//			}
		
		
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
//			if(filtroProduto.getId() != null && filtroProduto.getDescricao().equals("")){
				pst.setInt(1, filtroProduto.getId());
//			}else if(filtroProduto.getId() == null && !filtroProduto.getDescricao().equals("")){
				pst.setString(2, "%"+filtroProduto.getDescricao()+"%");			
//			}
			

			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> produtos = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id"));
				p.setDescricao(rs.getString("descricao"));
				p.setQuantidade(rs.getInt("quantidade"));
				
//				int idMarca = rs.getInt("marca_id");
//				String descricaoMarca = rs.getString("dmarca");				
//				Marca marca = new Marca(idMarca, descricaoMarca);
//				p.setMarca(marca);
				
//				int idModelo = rs.getInt("id_mod");
//				String descricaoModelo = rs.getString("dmodelo");				
//				Modelo modelo = new Modelo(idModelo, descricaoModelo);
//				p.setModelo(modelo);
				
				java.sql.Date dtCadastroEmLong = rs.getDate("dt_cadastro");
				Date dtCadastro = new Date(dtCadastroEmLong.getTime());				
				p.setDtCadastro(dtCadastro);
				produtos.add(p);
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	

	

}
