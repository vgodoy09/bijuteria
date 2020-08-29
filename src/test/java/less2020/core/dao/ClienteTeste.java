package less2020.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import less2020.command.ICommand;
import less2020.command.impl.SalvarCommand;
import less2020.core.aplicacao.Resultado;
import less2020.dominio.IEntidade;
import less2020.vh.IViewHelper;
import less2020.vh.impl.ClienteViewHelper;

@SpringBootTest
public class ClienteTeste {
	
	private static IViewHelper vh;

	@Test
	public void castrarcliente() 
	{
		post();
	}
	
	public void post() {
		Map<String, IViewHelper> vhs = new HashMap<String, IViewHelper>();
		
		vhs.put("/les22018-web/SalvarCliente", new ClienteViewHelper());
		
		Map<String, ICommand> commands = new HashMap<String, ICommand>();

		commands.put("SALVAR", new SalvarCommand());
		
		String uri = "/les22018-web/SalvarCliente";
		
		String operacao = "SALVAR";
		
		vh = vhs.get(uri);
		
		ICommand command = commands.get(operacao);
		
		IEntidade entidade = vh.getEntidade(null);
		
		Resultado resultado = command.execute(entidade);
		
		System.out.println(resultado);
	}
}
