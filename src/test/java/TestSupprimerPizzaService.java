import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.service.AjouterPizzaService;
import fr.pizzeria.service.PizzaMemoDao;
import fr.pizzeria.service.SupprimerPizzaService;

public class TestSupprimerPizzaService {
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void testExecuteUC() {
		
		PizzaMemoDao dao = new PizzaMemoDao();
		dao.initialisation ();
		SupprimerPizzaService suppr = new SupprimerPizzaService();
		systemInMock.provideLines("ORI");
		
		try {
			suppr.executeUC(dao);
		} catch (PizzaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(7, dao.findAllPizzas().size());
		Assert.assertEquals("IND", dao.findAllPizzas().get(6).code);
		boolean exist = dao.pizzaExists("ORI") ;
		Assert.assertFalse(exist);
		
	}

}
