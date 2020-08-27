
package less2020.command.impl;

import less2020.command.ICommand;
import less2020.core.IFachada;
import less2020.core.impl.controle.Fachada;



public abstract class AbstractCommand implements ICommand {

	protected IFachada fachada = new Fachada();
	

}
