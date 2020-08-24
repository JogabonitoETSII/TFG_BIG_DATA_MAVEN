package EnviromentClass;

// TODO: Auto-generated Javadoc
/**
 * The Class AnalizerObject.
 */
public abstract class AnalizerObject {

	/** The file path to analize. */
	public String filePathToAnalize;
	
	/** The file name. */
	public String fileName;
	
	/**
	 * Sets the file path to analize.
	 *
	 * @param filePath the new file path to analize
	 */
	public void setFilePathToAnalize(String filePath) {
		this.filePathToAnalize = filePath;
	}
	
	/**
	 * Gets the file path to analize.
	 *
	 * @return the file path to analize
	 */
	public String getFilePathToAnalize() {
		return filePathToAnalize;
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
	
	public abstract void execute();
	
	
}
