package less2020.dominio;

public class Modelo extends EntidadeDominio {

	
	private String descricao;
	
	public Modelo() {}
	public Modelo(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Modelo(int id) {
		this.id = id;
	}
	
	public Modelo(String descricao) {	
		this.descricao = descricao;
	}
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
