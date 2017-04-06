package guiController;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class WaitingExampleTest {

	WaitingExample constructer;
	
	@Before
	public void setup() {
		constructer = new WaitingExample();
	}

	@Test
	public void teststring() throws FileNotFoundException, ClassNotFoundException {
		System.out.println(constructer.testConnection("www.google.com"));
		//assertEquals(true, findapos.remodify("name"));
	}

}
