package dataBaseConstructor;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ConstructJsonTest {

	ConstructJson file;
	
	@Before
	public void setup() {
		file = new ConstructJson();
	}

	@Test
	public void teststring() throws FileNotFoundException {
		file.loadJson("http://hoike.hendrix.edu/api/CourseModel?$filter=YearCode%20eq%202016%20and%20TermCode%20eq%20%271S%27&$orderby=CourseId%20asc", "sample");
	}

}
