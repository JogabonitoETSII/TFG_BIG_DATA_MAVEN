package Hadoop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class HDFSLogic.
 */
public class HDFSLogic {

	/** The config HDFS. */
	private Configuration configHDFS;
	
	/** The hdfs file system. */
	private FileSystem hdfsFileSystem;
	
	/** The hdfs file system status. */
	private FileStatus hdfsFileSystemStatus;
	
	/** The connection true. */
	private Boolean connectionTrue;
	
	/** The defaultconfig. */
	final String DEFAULTCONFIG = "fs.defaultFS";
	
	
	/** The remote path. */
	private Path remotePath;
	
	/** The local path. */
	private Path localPath;
	
	/** The connection string. */
	private String connectionString; 
	
	/**
	 * Instantiates a new HDFS logic.
	 */
	public HDFSLogic() {
		setConnectionTrue(false);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Instantiates a new HDFS logic.
	 *
	 * @param connectionString the connection string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HDFSLogic (String connectionString) throws IOException {
		 // 
		setConnectionTrue(false);
		connectToHdfs(connectionString);
	}
	
	/**
	 * Connect to hdfs.
	 * Funcion para conectarnos a un HDFS por defecto.
	 * @param connectionString the connection string
	 * @return the boolean
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean connectToHdfs(String connectionString) throws IOException {
		// creamos la configuracion
		
		
		
		setConfigHDFS( new Configuration());
        getConfigHDFS().set(DEFAULTCONFIG, connectionString); 
        setHdfsFileSystem(FileSystem.get(getConfigHDFS()));

        if(getHdfsFileSystem() != null) {
        	setConnectionTrue(true);
        }
		return getConnectionTrue();
	}
	
	/**
	 * Adds the F ile.
	 *
	 * @param filePath the file path
	 * @return true, if successful
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public boolean addFile (String filePath) throws IOException {
		
		System.out.println("remote path varibale de la clase  " + getRemotePath() + " existe = ?" + existFolder());
		if(existFolder()) {
			Path remoteCreateFilePath ;
			remoteCreateFilePath = new Path(getRemotePath() + filePath);
			return getHdfsFileSystem().createNewFile(remoteCreateFilePath);
		}
		return false;
		
	}
	
	/**
	 * Adds the file.
	 *
	 * @param folderPath the folder path
	 * @param filePath the file path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addFile (String folderPath , String filePath) throws IOException {
		if(existFolder(folderPath)) {
			Path remoteCreateFilePath ;
			remoteCreateFilePath = new Path( folderPath + filePath);
			getHdfsFileSystem().createNewFile(remoteCreateFilePath);
		}
	}
	
	/**
	 * Removes the file.
	 *
	 * @param folderPath the folder path
	 * @param file the file
	 * @param recursive the recursive
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void removeFile(String folderPath, String file , Boolean recursive) throws IllegalArgumentException, IOException {
		if(existFile(folderPath, file)) {	
			getHdfsFileSystem().delete( new Path(folderPath + file), recursive);
		}
		
	}
	
	
	/**
	 * Removes the file in actually directory.
	 *
	 * @param file the file
	 * @param recursive the recursive
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void removeFileInActuallyDirectory(String file , Boolean recursive) throws IllegalArgumentException, IOException {
		if(existFileInActuallyFolder(file)){	
			getHdfsFileSystem().delete( new Path(getRemotePath() + file), recursive);
		}
	}
	
	
	/**
	 * Change permission.
	 *
	 * @param remoteFolder the remote folder
	 * @param nameOfObjectoTochangePermissions the name of objecto tochange permissions
	 * @param permissions the permissions
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void changePermission(String remoteFolder, String nameOfObjectoTochangePermissions,String permissions) throws IllegalArgumentException, IOException {
		getHdfsFileSystem().setPermission(new Path(remoteFolder + nameOfObjectoTochangePermissions), new FsPermission (permissions));
		
	}
	
	/**
	 * Gets the status.
	 *
	 * @param path the path
	 * @return the status
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FileStatus[] getStatus(String path ) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().listStatus(new Path(path));
	}
	
	/**
	 * Gets the status.
	 *
	 * @param path the path
	 * @return the status
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public FileStatus[] getStatus(Path path ) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().listStatus(path);
	}
 	
	/**
	 * Adds the folder.
	 *
	 * @param remoteFolder the remote folder
	 * @param folderName the folder name
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean addFolder(String remoteFolder, String folderName) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().mkdirs(new Path(remoteFolder + folderName ));
	}
	
	/**
	 * Adds the folder in actually folder.
	 *
	 * @param folderName the folder name
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean addFolderInActuallyFolder(String folderName) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().mkdirs(new Path(getRemotePath() + folderName ));
	}
	
	/**
	 * List folders.
	 *
	 * @param remoteFolder the remote folder
	 * @param folderNameToRemove the folder name to remove
	 * @param recursive the recursive
	 * @return the remote iterator
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	
	public Boolean removeFolder(String remoteFolder, Boolean recursive) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().delete(new Path(remoteFolder), recursive);
	}
	
	/**
	 * Removes the folder in actually folder.
	 *
	 * @param folderNameToRemove the folder name to remove
	 * @param recursive the recursive
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean removeFolderInActuallyFolder(String folderNameToRemove, Boolean recursive) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().delete(new Path(getRemotePath() + folderNameToRemove), recursive);
	}
	
	
	/**
	 * List folders.
	 *
	 * @param remoteFolderPath the remote folder path
	 * @return the remote iterator
	 * @throws FileNotFoundException the file not found exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public RemoteIterator<LocatedFileStatus> listFolders(String remoteFolderPath) throws FileNotFoundException, IllegalArgumentException, IOException {
		RemoteIterator<LocatedFileStatus> remoteFolder = getHdfsFileSystem().listFiles( new Path(remoteFolderPath) , false);
		return remoteFolder;
	}
	
	/**
	 * List actually folder.
	 *
	 * @return the remote iterator
	 * @throws FileNotFoundException the file not found exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public RemoteIterator<LocatedFileStatus> listActuallyFolder() throws FileNotFoundException, IllegalArgumentException, IOException {
		RemoteIterator<LocatedFileStatus> remoteFolder = getHdfsFileSystem().listFiles( getRemotePath() , false);
		return remoteFolder;
	}
	
	
	/**
	 * Exist folder.
	 *
	 * @param filePath the file path
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean existFolder(String filePath) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().exists(new Path (filePath));
	}

	/**
	 * Exist folder.
	 *
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean existFolder() throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().exists(getRemotePath());
	}

	/**
	 * Exist file.
	 *
	 * @param folderPath the folder path
	 * @param filePath the file path
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean existFile(String folderPath ,String filePath) throws IllegalArgumentException, IOException {
		
		//System.out.println("entrada del exists + "   +  folderPath + filePath);
		return getHdfsFileSystem().exists(new Path (folderPath + filePath));
	}
	
	/**
	 * Exist file in actually folder.
	 *
	 * @param filePath the file path
	 * @return the boolean
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean existFileInActuallyFolder(String filePath) throws IllegalArgumentException, IOException {
		return getHdfsFileSystem().exists(new Path (getRemotePath() + filePath));
	}
	
	
	/**
	 * Copy to local.
	 *
	 * @param remoteFolder the remote folder
	 * @param filePath the file path
	 * @param localFolder the local folder
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void copyToLocal(String remoteFolder,String filePath , String localFolder) throws IllegalArgumentException, IOException {
		getHdfsFileSystem().copyToLocalFile( new Path(remoteFolder+filePath), new Path(localFolder));
	}
	
	/**
	 * Copy to local.
	 *
	 * @param remoteFolder the remote folder
	 * @param localFolder the local folder
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void copyToLocal(String remoteFolder, String localFolder) throws IllegalArgumentException, IOException {
		getHdfsFileSystem().copyToLocalFile( new Path(remoteFolder), new Path(localFolder));
	}
	
	/**
	 * Copy to local actually folder.
	 *
	 * @param filePath the file path
	 * @param localFolder the local folder
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void copyToLocalActuallyFolder(String filePath , String localFolder) throws IllegalArgumentException, IOException {
		
		
		
		getHdfsFileSystem().copyToLocalFile( new Path(getRemotePath() + filePath), new Path(localFolder));
	}
	
	/**
	 * Copy from local.
	 *
	 * @param localFolder the local folder
	 * @param filePath the file path
	 * @param remoteFolder the remote folder
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void copyFromLocal(String localFolder,String filePath , String remoteFolder) throws IOException  {
		
		Path localfile =  new Path(localFolder+filePath);
		Path  remoteFIle =  new Path(remoteFolder+filePath);
		getHdfsFileSystem().copyFromLocalFile( localfile, remoteFIle );
		
		//Runtime.getRuntime().exec("hdfs dfs -copyFromLocal " + localFolder  + filePath + " " + remoteFolder + filePath );
		
		/*File existfileToUpload = new File(localFolder);
		
		Scanner ultimaVIDA = new Scanner(new File(localFolder+filePath));
		
		if(existfileToUpload.exists()) {
			try {
			if(!existFile(remoteFolder, filePath)) {
				InputStream in = new BufferedInputStream(new FileInputStream(new File(localFolder+filePath)));
				FSDataOutputStream out = getHdfsFileSystem().create(remoteFIle);
			    byte[] b = new byte[1024];
			    int numBytes = 0;
				    while ((numBytes = in.read(b)) > 0) {
				      out.write(b, 0, numBytes);
				    }
				    
					//OutputStream out = getHdfsFileSystem().create(remoteFIle);
					//IOUtils.copyBytes(in, out, 1024);
				    in.close();
				    out.close();
				}
			} catch (IllegalArgumentException | IOException e) {
			    
				System.out.println("mensaje de error " +  e.getMessage() );
				e.printStackTrace();
			}
			*/
		//}
		//getHdfsFileSystem().copyFromLocalFile(false,true, localfile, remoteFIle);
		
	}
	
	/**
	 * Creates the path.
	 *
	 * @param pathDirectorie the path directorie
	 * @param fileName the file name
	 * @return the path
	 */
	public Path createPath( String pathDirectorie, String fileName) {
		return new Path(pathDirectorie, fileName);
	}
	
	/**
	 * Reconnect to HDFS.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void reconnectToHDFS() throws IOException {
		connectToHdfs(getConnectionString());
	}
	
	/**
	 * Close connection.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void closeConnection() throws IOException {
		getHdfsFileSystem().close();
	}
	
	/**
	 * Gets the connection true.
	 *
	 * @return the connection true
	 */
	public Boolean getConnectionTrue() {
		return connectionTrue;
	}

	/**
	 * Sets the connection true.
	 *
	 * @param connectionTrue the new connection true
	 */
	public void setConnectionTrue(Boolean connectionTrue) {
		this.connectionTrue = connectionTrue;
	}

	/**
	 * Gets the config HDFS.
	 *
	 * @return the config HDFS
	 */
	public Configuration getConfigHDFS() {
		return configHDFS;
	}

	/**
	 * Sets the config HDFS.
	 *
	 * @param configHDFS the new config HDFS
	 */
	public void setConfigHDFS(Configuration configHDFS) {
		this.configHDFS = configHDFS;
	}

	/**
	 * Gets the hdfs file system.
	 *
	 * @return the hdfs file system
	 */
	public FileSystem getHdfsFileSystem() {
		return hdfsFileSystem;
	}

	/**
	 * Sets the hdfs file system.
	 *
	 * @param hdfsFileSystem the new hdfs file system
	 */
	public void setHdfsFileSystem(FileSystem hdfsFileSystem) {
		this.hdfsFileSystem = hdfsFileSystem;
	}

	/**
	 * Gets the hdfs file system status.
	 *
	 * @return the hdfs file system status
	 */
	public FileStatus getHdfsFileSystemStatus() {
		return hdfsFileSystemStatus;
	}

	/**
	 * Sets the hdfs file system status.
	 *
	 * @param hdfsFileSystemStatus the new hdfs file system status
	 */
	public void setHdfsFileSystemStatus(FileStatus hdfsFileSystemStatus) {
		this.hdfsFileSystemStatus = hdfsFileSystemStatus;
	}

	/**
	 * Gets the remote path.
	 *
	 * @return the remote path
	 */
	public Path getRemotePath() {
		return remotePath;
	}

	/**
	 * Sets the remote path.
	 *
	 * @param remotePath the new remote path
	 */
	public void setRemotePath(Path remotePath) {
		this.remotePath = remotePath;
	}

	/**
	 * Gets the local path.
	 *
	 * @return the local path
	 */
	public Path getLocalPath() {
		return localPath;
	}

	/**
	 * Sets the local path.
	 *
	 * @param localPath the new local path
	 */
	public void setLocalPath(Path localPath) {
		this.localPath = localPath;
	}

	/**
	 * Gets the defaultconfig.
	 *
	 * @return the defaultconfig
	 */
	public String getDEFAULTCONFIG() {
		return DEFAULTCONFIG;
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
	
	
	
	
	
	
	
	
	
	
	
}
