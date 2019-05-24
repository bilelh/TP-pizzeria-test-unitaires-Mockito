import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.ModifierPizzaService;
import fr.pizzeria.service.PizzaMemoDao;

public class TestModifierPizzaService {
	
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testExecuteUC() {
		
		PizzaMemoDao dao = new PizzaMemoDao();
		dao.initialisation ();
		ModifierPizzaService modif = new ModifierPizzaService();
		systemInMock.provideLines("ORI" , "REG" , "Regina" , "10" , "Viande");
		
		try {
			modif.executeUC(dao);
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(8, dao.findAllPizzas().size());
		Assert.assertEquals("REG", dao.findAllPizzas().get(7).code);
		Assert.assertEquals("Regina", dao.findAllPizzas().get(7).libelle);
		Assert.assertEquals(10, dao.findAllPizzas().get(7).prix,0);
		Assert.assertEquals(CategoriePizza.VIANDE, dao.findAllPizzas().get(7).cat);
		Assert.assertEquals("IND", dao.findAllPizzas().get(6).code);
		boolean exist = dao.pizzaExists("ORI") ;
		Assert.assertFalse(exist);
		
	}

}
