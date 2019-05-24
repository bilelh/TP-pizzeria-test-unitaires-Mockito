import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.factory.MenuServiceFactory;
import fr.pizzeria.service.ListerPizzaService;
import fr.pizzeria.service.PizzaMemoDao;

public class TestListerPizzaService {
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test
	public void executeUC() {
		
		
		
	}

}
