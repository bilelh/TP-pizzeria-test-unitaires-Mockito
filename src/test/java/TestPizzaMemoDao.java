import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.service.PizzaMemoDao;

public class TestPizzaMemoDao {
	PizzaMemoDao dao = new PizzaMemoDao() ;
	/*
	@Test
	public void testInitialisation () {
		
	}*/
	@Test
	public void testFindAllPizzas() {
		dao.initialisation();
		List <Pizza> liste = dao.findAllPizzas();
		Assert.assertEquals(8 , liste.size()) ;
	}
	@Test
	public void testSaveNewPizza() {
		dao.initialisation();
		List <Pizza> liste = dao.findAllPizzas();
		Pizza pizza = new Pizza("TTT" , "Test" , 12 , CategoriePizza.VIANDE ) ;
		dao.saveNewPizza(pizza);
		Assert.assertEquals(9 , liste.size()) ;
	}
	@Test
	public void testSaveNewPizza2() {
		dao.initialisation();
		List <Pizza> liste = dao.findAllPizzas();
		Pizza pizza = new Pizza("TTT" , "Test" , 12 , CategoriePizza.VIANDE ) ;
		dao.saveNewPizza(pizza);
		Assert.assertEquals(8, liste.indexOf(pizza));
	}
	@Test
	public void testUpdatePizza() {
		dao.initialisation();
		List <Pizza> liste = dao.findAllPizzas();
		Pizza pizza = new Pizza("TTT" , "Test" , 12 , CategoriePizza.VIANDE ) ;
		dao.updatePizza("ORI", pizza);
		Assert.assertEquals(8 , liste.size()) ;
	}
	@Test
	public void testUpdatePizza2() {
		dao.initialisation();
		Pizza pizza = new Pizza("TTT" , "Test" , 12 , CategoriePizza.VIANDE ) ;
		dao.updatePizza("ORI", pizza);
		boolean exist = dao.pizzaExists("ORI") ;
		Assert.assertFalse(exist);
	}
	@Test
	public void testDeletePizza() {
		dao.initialisation();
		dao.deletePizza("IND");
		boolean exist = dao.pizzaExists("IND") ;
		Assert.assertFalse(exist);
	}
	@Test
	public void testDeletePizza2() {
		dao.initialisation();
		List <Pizza> liste = dao.findAllPizzas();
		dao.deletePizza("IND");
		Assert.assertEquals(7 , liste.size()) ;
	}
	@Test
	public void testFindPizzaByCode() {
		dao.initialisation();
		Pizza pizza =dao.findPizzaByCode("SAV");
		Assert.assertEquals("SAV",pizza.code);
	}
	@Test
	public void testPizzaExists() {
		dao.initialisation();
		boolean exist = dao.pizzaExists("MAR");
		Assert.assertTrue(exist);
	}
	

}
