package dataBaseConstructor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ConstructDatabaseTest {

	ConstructDatabase constructer;
	
	@Before
	public void setup() {
		constructer = new ConstructDatabase();
	}

	@Test
	public void teststring() throws FileNotFoundException, ClassNotFoundException {
		constructer.Construct("sample");
		//assertEquals(true, findapos.remodify("name"));
	}

}
