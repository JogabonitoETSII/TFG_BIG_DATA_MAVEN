package EnviromentClass;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseObject.
 */
public abstract class DatabaseObject {

	/** The file path. */
	private String filePath;
	
	/** The file name. */
	private String fileName;
	
	/**
	 * filePathToAnalize.
	 *
	 * @return the string // the first position of a vector is the folder path the second position is the name of file;
	 */
	public abstract String[] filePathToAnalize();

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the file path.
	 *
	 * @param filePath the new file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public abstract boolean parseInputsFile(String path ) ;
	public abstract boolean exportToDataToFile(String folderPath, String fileName);
}
