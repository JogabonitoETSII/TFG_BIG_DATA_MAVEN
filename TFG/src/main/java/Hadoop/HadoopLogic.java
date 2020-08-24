package Hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;


import EnviromentClass.AnalizerObject;

// TODO: Auto-generated Javadoc
/**
 * The Class HadoopLogic.
 */
public class HadoopLogic extends AnalizerObject  {

	
	/** The connection string. */
	private String connectionString ;
	
	/** The hdfscomands. */
	private HDFSLogic hdfscomands;
	
	/** The yarn. */
	private Yarn yarn;
	
	/** The folder input path. */
	private String folderInputPath;
	
	/** The folder outpath. */
	private String folderOutpath;
	
	/**
	 * Instantiates a new hadoop logic.
	 */
	public HadoopLogic() {
		setHdfscomands(new HDFSLogic());
		setYarn(new Yarn());
	}
	
	/**
	 * Instantiates a new hadoop logic.
	 *
	 * @param hdfsConfig the hdfs config
	 * @throws IOException 
	 */
	public HadoopLogic(String hdfsConnectionString ) throws IOException {
		setHdfscomands(new HDFSLogic(hdfsConnectionString));
		setYarn(new Yarn());

	}
	
	/**
	 * Execute.
	 *
	 * @param jarFilePathToExecute the jar file path to execute
	 * @param jarOption the jar option
	 */
	public void execute(String jarFilePathToExecute,String jarOption) {
		try {
			executeToYarn(jarFilePathToExecute, jarOption, getFolderInputPath(), getFolderOutpath());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Execute to yarn.
	 *
	 * @param jarFilePathToExecute the jar file path to execute
	 * @param jarOption the jar option
	 * @param inputFolder the input folder
	 * @param outputFolder the output folder
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public void executeToYarn( String jarFilePathToExecute, String jarOption, String inputFolder,String outputFolder) throws IOException, InterruptedException {	
		
		getYarn().executeAlgorit(jarFilePathToExecute, jarOption, inputFolder, outputFolder);
	}
	
	/**
	 * Uplaod data.
	 *
	 * @param filePathToCluster the file path to cluster
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void uplaodData(String filePathToCluster) throws IOException {
		setFolderInputPath(filePathToCluster);
		getHdfscomands().copyFromLocal(getFilePathToAnalize(), getFileName(), getFolderInputPath());
	}
	
	/**
	 * Execute.
	 */
	@Override
	public void execute() {
		execute(getYarn().getJarFileToExecute(), getYarn().getJarOption());
	}
	
	/**
	 * Gets the connection string.
	 *
	 * @return the connection string
	 */
	public String getConnectionString() {
		return connectionString;
	}

	/**
	 * Sets the connection string.
	 *
	 * @param connectionString the new connection string
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	/**
	 * Gets the hdfscomands.
	 *
	 * @return the hdfscomands
	 */
	public HDFSLogic getHdfscomands() {
		return hdfscomands;
	}

	/**
	 * Sets the hdfscomands.
	 *
	 * @param hdfscomands the new hdfscomands
	 */
	public void setHdfscomands(HDFSLogic hdfscomands) {
		this.hdfscomands = hdfscomands;
	}

	/**
	 * Gets the yarn.
	 *
	 * @return the yarn
	 */
	public Yarn getYarn() {
		return yarn;
	}

	/**
	 * Sets the yarn.
	 *
	 * @param yarn the new yarn
	 */
	public void setYarn(Yarn yarn) {
		this.yarn = yarn;
	}

	/**
	 * Gets the folder input path.
	 *
	 * @return the folder input path
	 */
	public String getFolderInputPath() {
		return folderInputPath;
	}

	/**
	 * Sets the folder input path.
	 *
	 * @param folderInputPath the new folder input path
	 */
	public void setFolderInputPath(String folderInputPath) {
		this.folderInputPath = folderInputPath;
	}

	/**
	 * Gets the folder outpath.
	 *
	 * @return the folder outpath
	 */
	public String getFolderOutpath() {
		return folderOutpath;
	}

	/**
	 * Sets the folder outpath.
	 *
	 * @param folderOutpath the new folder outpath
	 */
	public void setFolderOutpath(String folderOutpath) {
		this.folderOutpath = folderOutpath;
	}
	
	/**
	 * Sets the yarn jar file to execute.
	 *
	 * @param Path the new yarn jar file to execute
	 */
	public void setYarnJarFileToExecute(String Path) {
		getYarn().setJarFileToExecute(Path);
	}
	
	/**
	 * Sets the yarn jar option.
	 *
	 * @param option the new yarn jar option
	 */
	public void setYarnJarOption(String option) {
		getYarn().setJarOption(option);
	}
	
}
