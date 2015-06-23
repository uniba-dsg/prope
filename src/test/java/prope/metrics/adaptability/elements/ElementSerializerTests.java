package prope.metrics.adaptability.elements;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class ElementSerializerTests {

	@Test
	public void testSerialization() throws IOException {
		String fileName = "tmp.tex";
		ElementSerializer serializer = new ElementSerializer(true, fileName);
		Files.deleteIfExists(Paths.get(fileName));

		serializer.serializeToLatexTable();

		assertEquals(
				Files.readAllLines(Paths
						.get("src/test/java/prope/metrics/adaptability/elements/tmp.tex")),
				Files.readAllLines(Paths.get(fileName)));
		
		Files.deleteIfExists(Paths.get(fileName));
	}

}
