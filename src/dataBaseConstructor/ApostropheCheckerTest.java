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
		assertEquals("Students learn about history by playing the roles of historical characters, based on primary sources and rich historical texts, in elaborate and competitive games that allow for multiple outcomes. Possible game topics include Ancient Athens'' democracy, the trial of Galileo, the French Revolution, the Haitian Revolution, women''s suffrage, and the negotiations that led to the end of Apartheid.", findapos.remodify("Students learn about history by playing the roles of historical characters, based on primary sources and rich historical texts, in elaborate and competitive games that allow for multiple outcomes. Possible game topics include Ancient Athens' democracy, the trial of Galileo, the French Revolution, the Haitian Revolution, women's suffrage, and the negotiations that led to the end of Apartheid."));
		//assertEquals(true, findapos.remodify("name"));
	}

}
