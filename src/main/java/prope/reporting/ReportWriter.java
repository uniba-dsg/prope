package prope.reporting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public final class ReportWriter {

	private final Report report;

	private final String excelDelimiter = ";";

	private final String rDelimiter = ",";

	public ReportWriter(Report report) {
		this.report = report;
	}

	public void printToConsole() {
		for (ReportEntry entry : report) {
			System.out.println(entry);
		}
	}

	public void writeToExcelFile(String outputFile) {
		if (report.getEntries().isEmpty()) {
			System.out
					.println("There is nothing to write... Did you use the correct analysis type?");
			return;
		}

		writeToFile(outputFile, excelDelimiter);
	}

	private void writeToFile(String outputFile, String delimiter) {
		try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(outputFile), "UTF-8"))) {
			printHeader(writer, delimiter);
			printBody(writer, delimiter);
		} catch (IOException e) {
			System.err.println("Could no write to file " + outputFile + ": "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	private void printHeader(PrintWriter writer, String delimiter) {
		writer.print("filename" + delimiter);
		for (String varName : report.getEntries().get(0).getVariableNames()) {
			writer.print(varName + delimiter);
		}
		writer.println();
	}

	private void printBody(PrintWriter writer, String delimiter) {
		for (ReportEntry entry : report) {
			writer.println(entry.toStringWithSeparator(delimiter));
		}
	}

	public void writeToRFile(String outputFile) {
		writeToFile(outputFile, rDelimiter);
	}
}
