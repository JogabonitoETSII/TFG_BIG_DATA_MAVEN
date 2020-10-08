package EnviromentClass;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalizerReportLinkClass.
 */
public class AnalizerReportLinkClass {
	
	/** The analizer. */
	private AnalizerObject analizer;
	
	/** The report. */
	private ReportObject report;
	
	/**
	 * Instantiates a new analizer report link class.
	 */
	public AnalizerReportLinkClass() {
		
	}
	
	/**
	 * Instantiates a new analizer report link class.
	 *
	 * @param analizer the analizer
	 * @param report the report
	 */
	public AnalizerReportLinkClass(AnalizerObject analizer, ReportObject report) {
		setAnalizer(analizer);
		setReport(report);
	}
	
	public Boolean makeReportWorkFlow() {
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!path de las alida de hadoop  " + getAnalizer().getFilePathToAnalize()+getAnalizer().outputLocalFolder() + "   nombre del fichero " + getAnalizer().getResultFile()  );
		return getReport().buildReport(getReport().getOutputFileReportName(), getAnalizer().getResultFile());
	}
	
	public Boolean makeReportAnalizerWorkFlow() {
		//System.out.println("path del report de salida  makeReportAnalizerWorkFlow()  " + getAnalizer().getFilePathToAnalize()+getAnalizer().outputLocalFolder() );
		System.out.println("path de las alida de hadoop  " + getAnalizer().getFilePathToAnalize()+getAnalizer().outputLocalFolder() + "   nombre del fichero " + getAnalizer().getResultFile()  );
		
		return getReport().buildReport(getAnalizer().getFilePathToAnalize()+getAnalizer().outputLocalFolder(), getAnalizer().getResultFile());
	}
	
	/**
	 * Gets the analizer.
	 *
	 * @return the analizer
	 */
	public AnalizerObject getAnalizer() {
		return analizer;
	}

	/**
	 * Sets the analizer.
	 *
	 * @param analizer the new analizer
	 */
	public void setAnalizer(AnalizerObject analizer) {
		this.analizer = analizer;
	}

	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public ReportObject getReport() {
		return report;
	}

	/**
	 * Sets the report.
	 *
	 * @param report the new report
	 */
	public void setReport(ReportObject report) {
		this.report = report;
	}

	
	
}
