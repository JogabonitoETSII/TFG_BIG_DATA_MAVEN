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
	 * @param localPath the local path
	 * @param remotePath the remote path
	 */
	public abstract void uploadData(String remotePath);
	public abstract void downloadResult();	
	
	
}
