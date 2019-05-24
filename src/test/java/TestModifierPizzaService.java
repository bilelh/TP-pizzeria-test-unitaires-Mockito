import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
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
	
	@Test(expected=UpdatePizzaException.class)
	public void testMockExecuteUC() throws UpdatePizzaException {
		
		PizzaMemoDao mockedDao = Mockito.mock(PizzaMemoDao.class);
		
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(null) ;
		
		ModifierPizzaService modif = new ModifierPizzaService();
		systemInMock.provideLines("ORI" , "REG" , "Regina" , "10" , "Viande");
		
		modif.executeUC(mockedDao);
	}
	
	@Test(expected=UpdatePizzaException.class)
	public void test2MockExecuteUC() throws UpdatePizzaException {
		
		PizzaMemoDao mockedDao = Mockito.mock(PizzaMemoDao.class);
		Pizza pizza1 = null;
		Pizza pizza2 = new Pizza(null , "Orientale" , 15 , CategoriePizza.VIANDE);
		List <Pizza> listPizza = new ArrayList<Pizza>();
		listPizza.add(pizza1);
		listPizza.add(pizza2);
		
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(listPizza) ;
		
		ModifierPizzaService modif = new ModifierPizzaService();
		systemInMock.provideLines("ORI" , "REG" , "Regina" , "10" , "Viande");
		
		modif.executeUC(mockedDao); 
	}
}
