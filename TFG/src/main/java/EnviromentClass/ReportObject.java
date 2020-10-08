package EnviromentClass;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportObject.
 */
public abstract class ReportObject {

	
	/** The Folder input ouput report. */
	public String FolderInputOuputReport;
	
	/** The output file report. */
	public String outputFileReportName;
	

	/**
	 * Builds the report.
	 *
	 * @param filePath the file path
	 * @param fileName the file name
	 * @return the boolean
	 */
	public abstract Boolean buildReport(String filePath , String fileName);


	/**
	 * Gets the folder input ouput report.
	 *
	 * @return the folder input ouput report
	 */
	public String getFolderInputOuputReport() {
		return FolderInputOuputReport;
	}


	/**
	 * Sets the folder input ouput report.
	 *
	 * @param folderInputOuputReport the new folder input ouput report
	 */
	public void setFolderInputOuputReport(String folderInputOuputReport) {
		FolderInputOuputReport = folderInputOuputReport;
	}


	/**
	 * Gets the output file report name.
	 *
	 * @return the output file report name
	 */
	public String getOutputFileReportName() {
		return outputFileReportName;
	}


	/**
	 * Sets the output file report name.
	 *
	 * @param outputFileReportName the new output file report name
	 */
	public void setOutputFileReportName(String outputFileReportName) {
		this.outputFileReportName = outputFileReportName;
	}


	/**
	 * Parses the inputs file.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @return true, if successful
	 */
	public  abstract boolean parseInputsFile(String path) ;
	
}
