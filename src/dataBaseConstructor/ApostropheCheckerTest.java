package dataBaseConstructor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ApostropheCheckerTest {

	ApostropheChecker findapos;
	
	@Before
	public void setup() {
		findapos = new ApostropheChecker();
	}

	@Test
	public void teststring() throws FileNotFoundException {
		assertEquals("x''s", findapos.remodify("x's"));
		//assertEquals(true, findapos.remodify("name"));
	}

}
