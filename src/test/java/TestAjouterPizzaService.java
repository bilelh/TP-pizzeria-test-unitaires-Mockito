import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.factory.MenuServiceFactory;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.PizzaMemoDao;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class TestAjouterPizzaService {
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testExecuteUC() {
		
		PizzaMemoDao dao = new PizzaMemoDao();
		dao.initialisation ();
		AjouterPizzaService ajout = new AjouterPizzaService();
		systemInMock.provideLines("REG" , "Regina" , "10" , "Viande");
		
		try {
			ajout.executeUC(dao);
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(9, dao.findAllPizzas().size());
		Assert.assertEquals("REG", dao.findAllPizzas().get(8).code);
		Assert.assertEquals("Regina", dao.findAllPizzas().get(8).libelle);
		Assert.assertEquals(10, dao.findAllPizzas().get(8).prix,0);
		Assert.assertEquals(CategoriePizza.VIANDE, dao.findAllPizzas().get(8).cat);
	}
	

}
