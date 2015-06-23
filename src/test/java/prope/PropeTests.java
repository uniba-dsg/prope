package prope;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import prope.executables.AnalysisException;
import prope.executables.AnalysisType;

public class PropeTests {

	@Test(expected = AnalysisException.class)
	public void validateArgLength() {
		String[] args = new String[1];
		Prope.validateArgs(args);
	}

	@Test(expected = AnalysisException.class)
	public void validateWrongAnalysisType() {
		String[] args = { "-z", "b" };
		Prope.validateArgs(args);
	}

	@Test
	public void getInstallabilityAnalysisType() {
		AnalysisType type = Prope.getAnalysisType("-i");
		assertEquals(AnalysisType.INSTALLABILITY, type);
	}

	@Test
	public void getDeployabilityAnalysisType() {
		AnalysisType type = Prope.getAnalysisType("-d");
		assertEquals(AnalysisType.DEPLOYABILITY, type);
	}

	@Test
	public void getPortabilityAnalysisType() {
		AnalysisType type = Prope.getAnalysisType("-p");
		assertEquals(AnalysisType.PORTABILITY, type);
	}

	@Test
	public void getAdaptabilityAnalysisType() {
		AnalysisType type = Prope.getAnalysisType("-a");
		assertEquals(AnalysisType.ADAPTABILITY, type);
	}

	@Test
	public void getInstallabilityUnknownType() {
		AnalysisType type = Prope.getAnalysisType("-z");
		assertEquals(AnalysisType.UNKNOWN, type);
	}

}
