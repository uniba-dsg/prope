package prope.metrics.portability;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import prope.executables.FileAnalyzer;
import prope.reporting.ReportEntry;

public final class PortabilityAnalyzer implements FileAnalyzer {

	private final String fileEnding = ".bpel";

	@Override
	public List<ReportEntry> analyzeFile(Path filePath) {
		List<ReportEntry> report = new ArrayList<ReportEntry>();

		if (filePath.toString().endsWith(fileEnding)) {

			System.out.println("Analyzing " + filePath + " for portability");

			ReportEntry entry = populateEntry(filePath);
			report.add(entry);

		} else {

			System.out.println("Ignoring " + filePath);

		}
		return report;
	}

	private ReportEntry populateEntry(Path filePath) {
		bpp.executables.FileAnalyzer bppAnalyzer = new bpp.executables.FileAnalyzer(
				filePath.toString(), false);
		bpp.domain.AnalysisResult analysisResult = bppAnalyzer.analyze();
		ReportEntry entry = new ReportEntry(analysisResult.getBpelFile()
				.toString());
		entry.addVariable("class", analysisResult.getClassification()
				.toString());
		entry.addVariable("N", analysisResult.getNumberOfElements() + "");
		entry.addVariable("Mb", analysisResult.getBasicPortabilityMetric() + "");
		entry.addVariable("Me", analysisResult.getWeightedElementsMetric() + "");
		entry.addVariable("Ma", analysisResult.getActivityPortabilityMetric()
				+ "");
		entry.addVariable("Ms",
				analysisResult.getServiceCommunicationPortabilityMetric() + "");
		return entry;
	}

}
