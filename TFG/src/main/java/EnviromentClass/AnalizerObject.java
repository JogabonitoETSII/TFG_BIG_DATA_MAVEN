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
	
	/** The output path. */
	public String outputPath;
	
	/** The remote path. */
	public String remotePath;
	
	/** The result file name. */
	public String resultFileName;
	
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
	
	
	
	/**
	 * Gets the output path.
	 *
	 * @return the output path
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * Sets the output path.
	 *
	 * @param outputPath the new output path
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	
	
	/**
	 * Gets the remote path.
	 *
	 * @return the remote path
	 */
	public String getRemotePath() {
		return remotePath;
	}

	/**
	 * Sets the remote path.
	 *
	 * @param remotePath the new remote path
	 */
	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * Execute.
	 */
	public abstract void execute();
	
	/**
	 * Upload data to.
	 *
	 * @param remotePath the remote path
	 */
	
	
	public abstract void uploadData(String remotePath);
	
	/**
	 * Gets the result file name.
	 *
	 * @return the result file name
	 */
	public String getResultFileName() {
		return resultFileName;
	}

	/**
	 * Sets the result file name.
	 *
	 * @param resultFileName the new result file name
	 */
	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}

	/**
	 * Download result.
	 */
	public abstract void downloadResult();	
	
	/**
	 * Gets the result file.
	 *
	 * @return the result file
	 */
	public abstract String getResultFile();
	
	/**
	 * OutputLocal folder.
	 *
	 * @return the string
	 */
	public abstract String outputLocalFolder();
	
	public abstract boolean parseInputsFile(String path ) ;

}
