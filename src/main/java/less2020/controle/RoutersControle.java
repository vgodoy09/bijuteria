package less2020.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoutersControle {
	
	@GetMapping("cliente")
	public ModelAndView formcliente() {
		return new ModelAndView("FormCliente");
	}
	

}
