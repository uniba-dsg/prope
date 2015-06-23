package prope.executables;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import prope.reporting.ReportEntry;

public class AnalysisWorkflowTests {
	
	private AnalysisWorkflow sut;
	
	private final String deployabilityResourcesPath = "src/test/resources/installability/deployment/";
	
	private final String installabilityResourcePath = "src/test/resources/installability/server/";

	@Test(expected = IllegalArgumentException.class)
	public void testNonExistingDirectory() {
		sut = new AnalysisWorkflow(Paths.get("foobar"),
				AnalysisType.DEPLOYABILITY);
	}
	
	@Test
	public void deployabilityAnalysis(){
		sut = new AnalysisWorkflow(Paths.get(deployabilityResourcesPath + "xmlDescriptorWithText.zip"),
				AnalysisType.DEPLOYABILITY);
		
		List<ReportEntry> results = sut.start().getEntries();
		assertEquals(results.size(),1);
		
		ReportEntry entry = results.get(0);
		assertEquals(14, getDeploymentEffort(entry));
		assertEquals(2, getEffortOfPackageConstruction(entry));
		assertEquals(12, getDescriptorComplexity(entry));
	}

	private int getDeploymentEffort(ReportEntry entry) {
		if (entry != null) {
			return Integer.parseInt(entry.getVariableValue("DE"));
		} else {
			return 0;
		}
	}

	private int getEffortOfPackageConstruction(ReportEntry entry) {
		if (entry != null) {
			return Integer.parseInt(entry.getVariableValue("EPC"));
		} else {
			return 0;
		}
	}

	private int getDescriptorComplexity(ReportEntry entry) {
		if (entry != null) {
			return Integer.parseInt(entry.getVariableValue("DDS"));
		} else {
			return 0;
		}
	}
	
	@Test
	public void installabilityAnalysis() throws ParseException {
		sut = new AnalysisWorkflow(Paths.get(installabilityResourcePath + "simple/"),
				AnalysisType.INSTALLABILITY);
		
		List<ReportEntry> results = sut.start().getEntries();
		assertEquals(results.size(),1);

		String ait = results.get(0).getVariableValue("AIT");
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		Number number = format.parse(ait);
		double d = number.doubleValue();
		assertEquals("AIT should be 22,00, but was " + d, 22, d, 0.1);
	}
}
