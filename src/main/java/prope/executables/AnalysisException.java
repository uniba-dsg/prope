package prope.executables;

public class AnalysisException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AnalysisException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public AnalysisException(String arg0) {
		super(arg0);
	}

	public AnalysisException(Throwable arg0) {
		super(arg0);
	}

}
