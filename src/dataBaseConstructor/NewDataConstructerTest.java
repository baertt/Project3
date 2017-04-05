package dataBaseConstructor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class NewDataConstructerTest {

	NewDataConstructer data;
	List<?> newkid = Arrays.asList("");
	List<?> newkid2 = Arrays.asList("HP");
	
	@Before
	public void setup() {
		data = new NewDataConstructer();
	}

	@Test
	public void teststring() throws FileNotFoundException {
		
		assertEquals("NA", data.collegiateCode(newkid));
		assertEquals("HP", data.collegiateCode(newkid2));
	}

}
