package prope.metrics.adaptability;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.saxon.lib.NamespaceConstant;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import prope.metrics.adaptability.nodecounters.XPathNodeCounter;

public class XPathNodeCounterTests {
	
	private XPathNodeCounter sut;
	
	@BeforeClass
	public static void beforeClass() {
		System.setProperty("javax.xml.xpath.XPathFactory:"
				+ NamespaceConstant.OBJECT_MODEL_SAXON,
				"net.sf.saxon.xpath.XPathFactoryImpl");
	}
	
	@Before
	public void setUp(){
		sut = new XPathNodeCounter();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void absoluteElementNumbersShouldBeUnmodifiable(){
		sut.getAbsoluteElementNumbers().put("abc", new AtomicInteger());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void processOccurrencesShouldBeUnmodifiable(){
		sut.getProcessOccurences().put("abc", new AtomicInteger());
	}
	
	@Test
	public void csvWrite() throws IOException{
		Path fileLocation = Paths.get("tmp.csv");
		Files.deleteIfExists(fileLocation);
		
		sut.writeToCsv(fileLocation);
		
		assertTrue(Files.exists(fileLocation));
		Files.deleteIfExists(fileLocation);
	}

}
