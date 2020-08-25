package EnviromentClass;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportObject.
 */
public abstract class ReportObject {

	
	/** The Folder input ouput report. */
	public String FolderInputOuputReport;
	

	/**
	 * Builds the report.
	 */
	public abstract void buildReport();


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
	
	
}
