package dataBaseConstructor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class JsonAnalyzerTest {

	JsonAnalyzer jsonAnalyzer;
	
	@Before
	public void setup() {
		jsonAnalyzer = new JsonAnalyzer();
	}

	@Test
	public void teststring() throws FileNotFoundException {
		assertEquals(false, jsonAnalyzer.checkNexttoken("name", "nome"));
		assertEquals(true, jsonAnalyzer.checkNexttoken("name", "name"));
	}

}
