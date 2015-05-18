package prope.metrics.adaptability.elements;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ElementSerializer {

	private final String fileName;

	private final String ROW_COLOR = "\\myrowcolour";

	private final String TABLE_NEWLINE = "\\\\";

	private final boolean useRowColor;

	private PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException {
		new ElementSerializer(true, args[0]).serializeToLatexTable();
	}

	ElementSerializer(boolean useColor, String fileName) {
		this.useRowColor = useColor;
		this.fileName = fileName;
	}

	public void serializeToLatexTable() throws FileNotFoundException {
		try {
			writer = new PrintWriter(fileName);

			writeLongTableHeader();

			writeLongTableBody();

			writeLongTableFooter();

		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private void writeLongTableHeader() {
		println("\\begin{center}");
		println("\\begin{scriptsize}");
		println("\\begin{longtable}{p{0.13\\textwidth}|p{0.82\\textwidth}}");
		println("\\caption{Adaptability Scores}\\\\");
		println("\\lanel{tab:adaptableElements}\\\\");
		println("\\textbf{Property} & \\textbf{Element}");
		println("\\vspace{2pt}\\\\");
		println("\\toprule");
		println("\\endfirsthead");
		println("\\multicolumn{2}{c}");
		println("{\\tablename\\ \\thetable\\ -- \\emph{Continued from previous page}}\\\\");
		println("\\endhead");
		println("\\multicolumn{2}{r}{\\emph{Continued on next page}}");
		println("\\endfoot");
		println("\\bottomrule");
		println("\\endlastfoot");
	}

	private void writeLongTableBody() {
		List<AdaptableElement> elements = new AdaptableElements().getElements();
		for (int i = 0; i < elements.size(); i++) {
			AdaptableElement element = elements.get(i);

			println("Element name & " + element.getName() + TABLE_NEWLINE);
			printColor();
			println("Documentation &" + element.getDocumentation()
					+ TABLE_NEWLINE);
			println("Expression & " + element.getLocatorExpression()
					+ TABLE_NEWLINE);
			printColor();
			println("Adaptions & " + element.getAdaptions() + TABLE_NEWLINE);
			println("Score & " + element.getNumberOfAdaptions() + TABLE_NEWLINE);

			// print a midrule expect for last assertion
			if (i != elements.size() - 1) {
				println("\\midrule");
			}
		}
	}

	private void writeLongTableFooter() {
		println("\\end{longtable}");
		println("\\end{scriptsize}");
		println("\\end{center}");
	}

	private void printColor() {
		if (useRowColor) {
			println(ROW_COLOR);
		}
	}

	private void println(String line) {
		writer.println(line);
	}
}
