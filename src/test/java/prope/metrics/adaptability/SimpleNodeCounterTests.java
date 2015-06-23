package prope.metrics.adaptability;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import prope.metrics.adaptability.nodecounters.NodeCounter;
import prope.metrics.adaptability.nodecounters.SimpleNodeCounter;

public class SimpleNodeCounterTests {

	private NodeCounter simpleCounter;

	private AdaptabilityAnalyzer util = new AdaptabilityAnalyzer();

	@Before
	public void setUp() {
		simpleCounter = new SimpleNodeCounter(false);
	}
	

	@Test
	public void testNodeCount() {
		Document testFile = util
				.getDom("src/test/resources/adaptability/ElementNumbers.bpmn");
		simpleCounter.addToCounts(testFile);
		int numberOfElements = simpleCounter.getAbsoluteElementNumbers().keySet()
				.size();
		assertEquals(2, numberOfElements);
	}
	
	@Test
	public void csvWrite() throws IOException{
		Path fileLocation = Paths.get("tmp.csv");
		Files.deleteIfExists(fileLocation);
		
		simpleCounter.writeToCsv(fileLocation);
		
		assertTrue(Files.exists(fileLocation));
		Files.deleteIfExists(fileLocation);
	}


}
