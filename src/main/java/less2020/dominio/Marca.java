package less2020.dominio;

public class Marca extends EntidadeDominio {


	private String descricao;
	
	public Marca() {}
	public Marca(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Marca(int id) {
		this.id = id;	
	}
	
	public Marca(String descricao) {	
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
