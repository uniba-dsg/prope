package prope.executables;

import java.nio.file.Paths;

import org.junit.Test;

public class AnalysisWorkflowTests {

	@Test(expected = IllegalArgumentException.class)
	public void testNonExistingDirectory() {
		@SuppressWarnings("unused")
		AnalysisWorkflow sut = new AnalysisWorkflow(Paths.get("foobar"),
				AnalysisType.DEPLOYABILITY);
	}

}
