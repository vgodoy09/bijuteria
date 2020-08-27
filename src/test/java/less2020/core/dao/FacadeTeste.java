package less2020.core.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import less2020.command.impl.ConsultarCommand;
import less2020.core.aplicacao.Resultado;
import less2020.core.impl.controle.Fachada;
import less2020.dominio.IEntidade;
import less2020.dominio.Produto;

@SpringBootTest
public class FacadeTeste {
	
	@Test
	void consultaDeProduto() {
		
		Fachada fachada = new Fachada();
		
		
		ConsultarCommand consultar = new ConsultarCommand();
		
		Produto prod = new Produto();
				prod.setDescricao("familia prado");
				prod.setId(1);
		IEntidade p = prod;
		Resultado execute = consultar.execute(p);
		
		System.out.println(execute.getMsg());
		System.out.println(execute.getEntidades());
		
		assertNotNull(execute);

	}

}
