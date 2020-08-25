package Test;

import java.io.FileNotFoundException;
import java.io.IOException;



import Hadoop.HDFSLogic;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.fs.permission.FsPermission;
import org.junit.Assert;


// TODO: Auto-generated Javadoc
/**
 * The Class HDFSLogicTest.
 */
public class HDFSLogicTest {

	/**
	 * Test.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@org.junit.Test
	public void test() throws IOException {

		// hdfs://localhost:54310/user/hadoop/
		String localFolder = "/home/alberto/Escritorio/";
		String folderToCreateOrDelete = "";
		String file = "querysToTestMOngo";
		String remoteFolder = "/albertoHome/" ;
		String connecitonStirng = "hdfs://localhost:9000";
		//HDFSLogic hdfs = new HDFSLogic(connecitonStirng);
		//connectToHadoopTest(hdfs, connecitonStirng);
		//addFileTest(hdfs,  remoteFolder ,file  ); // pasmaos el folder y la ruta compelta con el fichero para realizar los test
		//listFoldersTest(hdfs,remotePath);
		//listActuallyFoldersTest(hdfs,remotePath);
		//removeFileAnyFolderTest(hdfs, remoteFolder,file);
		//removeFileActuallyFolderTest(hdfs, remoteFolder,file);
		//addFolderTest(hdfs, remoteFolder, folderToCreateOrDelete);
		//addFolderInActuallyFolderTest(hdfs, remoteFolder, folderToCreateOrDelete + "inRemote");
		//changePermissionsTest(hdfs, remoteFolder, file+"1344423231");
		//removeFolderTest(hdfs, remoteFolder, folderToCreateOrDelete);
		//removeFolderInActuallyFolderTest(hdfs, remoteFolder, folderToCreateOrDelete + "inRemote");
		//copyToLocalTest(hdfs, remoteFolder, file, localFolder);
		//copyToLocalInActuallyFolderTest(hdfs, remoteFolder, file, localFolder);
		//copyFromLocal(hdfs, localFolder , file, remoteFolder);
	}

	/**
	 * Connect to hadoop test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param connectionString the connection string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void connectToHadoopTest(HDFSLogic hdfsTest, String connectionString) throws IOException {
		hdfsTest.connectToHdfs(connectionString);
		Assert.assertTrue(hdfsTest.getConnectionTrue());

	}
	
	/**
	 * List folders test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolderPath the remote folder path
	 * @throws FileNotFoundException the file not found exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void listFoldersTest(HDFSLogic hdfsTest , String remoteFolderPath) throws FileNotFoundException, IllegalArgumentException, IOException {
		RemoteIterator<LocatedFileStatus> testTrue = hdfsTest.getHdfsFileSystem().listFiles( new Path(remoteFolderPath) , false);
		Assert.assertTrue(remoteFolderPath,testTrue.hasNext());
	}

	/**
	 * List actually folders test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolderPath the remote folder path
	 * @throws FileNotFoundException the file not found exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void listActuallyFoldersTest(HDFSLogic hdfsTest ,  String remoteFolderPath) throws FileNotFoundException, IllegalArgumentException, IOException {
		
		hdfsTest.setRemotePath(new Path(remoteFolderPath));
		RemoteIterator<LocatedFileStatus> testTrue = hdfsTest.listActuallyFolder();
		Assert.assertTrue(remoteFolderPath,testTrue.hasNext());
	}
	
	/**
	 * Adds the file test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param file the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addFileTest(HDFSLogic hdfsTest ,  String remoteFolder , String file ) throws IOException {
		hdfsTest.setRemotePath(new Path(remoteFolder));
		hdfsTest.addFile(file + "22111");
		hdfsTest.addFile(file + "1344423231", remoteFolder);
		RemoteIterator<LocatedFileStatus> testTrue = hdfsTest.getHdfsFileSystem().listFiles( new Path(remoteFolder) , false);
		Assert.assertTrue(file,testTrue.hasNext());
		
		
	}

	/**
	 * Removes the file test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param file the file
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void removeFileAnyFolderTest(HDFSLogic hdfsTest ,  String remoteFolder ,String file ) throws IllegalArgumentException, IOException {
		hdfsTest.removeFile(remoteFolder, file, false);
		Assert.assertFalse( hdfsTest.existFile(remoteFolder,file));
	}
	
	/**
	 * Removes the file actually folder test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param file the file
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void removeFileActuallyFolderTest(HDFSLogic hdfsTest ,  String remoteFolder ,String file) throws IllegalArgumentException, IOException {
		hdfsTest.setRemotePath(new Path(remoteFolder));
		
		System.out.println(hdfsTest.getRemotePath());
		hdfsTest.removeFileInActuallyDirectory(file + "22111", false);
		Assert.assertFalse( hdfsTest.existFileInActuallyFolder(file));
	}

	/**
	 * Adds the folder test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param nameNewFolder the name new folder
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addFolderTest(HDFSLogic hdfsTest , String remoteFolder , String nameNewFolder ) throws IllegalArgumentException, IOException {
		Assert.assertTrue(hdfsTest.addFolder(remoteFolder, nameNewFolder ));
	}

	/**
	 * Adds the folder in actually folder test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param nameNewFolder the name new folder
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addFolderInActuallyFolderTest(HDFSLogic hdfsTest , String remoteFolder , String nameNewFolder) throws IllegalArgumentException, IOException {
		hdfsTest.setRemotePath(new Path(remoteFolder));
		Assert.assertTrue(hdfsTest.addFolderInActuallyFolder(nameNewFolder));
	}
	
	/**
	 * Change permissions test.
	 *
	 * @param hdfsTest the hdfs test
	 * @param remoteFolder the remote folder
	 * @param nameOfObjectoTochangePermissions the name of objecto tochange permissions
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void changePermissionsTest(HDFSLogic hdfsTest , String remoteFolder , String nameOfObjectoTochangePermissions) throws IllegalArgumentException, IOException {
		String permissions = "777";
		hdfsTest.changePermission(remoteFolder, nameOfObjectoTochangePermissions, permissions);
		FileStatus[] CheckPermissions = hdfsTest.getHdfsFileSystem().listStatus(new Path(remoteFolder + nameOfObjectoTochangePermissions));
		System.out.println( "cantidad de objetos devueltos FIleStatus " + CheckPermissions.length );
		Assert.assertEquals(CheckPermissions[0].getPermission(), new FsPermission (permissions));
	}
	
	/**
	 * Removes the folder test.
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public void removeFolderTest(HDFSLogic hdfsTest , String remoteFolder ,String folderToDelete) throws IllegalArgumentException, IOException {
		Assert.assertTrue(hdfsTest.removeFolder(remoteFolder,folderToDelete,false));		
	}
	
	public void removeFolderInActuallyFolderTest(HDFSLogic hdfsTest , String remoteFolder ,String folderToDelete) throws IllegalArgumentException, IOException {
		hdfsTest.setRemotePath(new Path (remoteFolder));
		Assert.assertTrue(hdfsTest.removeFolderInActuallyFolder(folderToDelete,false));	
	}
	
	
	/**
	 * Copy to local test.
	 * @throws IOException 
	 */
	public void copyToLocalTest(HDFSLogic hdfsTest , String remoteFolder ,String filename,String localFolder) throws IOException {
		
		try {
			hdfsTest.copyToLocal(remoteFolder, filename, localFolder);
			Assert.assertTrue( true );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void copyToLocalInActuallyFolderTest(HDFSLogic hdfsTest , String remoteFolder ,String filename,String localFolder) throws IllegalArgumentException, IOException {
		hdfsTest.setRemotePath(new Path(remoteFolder));
		hdfsTest.copyToLocalActuallyFolder( filename, localFolder);
		Assert.assertTrue( true );
	}
	
	/**
	 * Put file test.
	 * @throws IOException 
	 * @throws IllegalArgumentException 
	 */
	public void copyFromLocal(HDFSLogic hdfsTest , String localFolder ,String filename,String remoteFolder) throws IllegalArgumentException, IOException {	
		
		 //hdfsTest.copyFromLocal( localFolder, filename, remoteFolder);
		
		Path localfile =  new Path(localFolder+filename);
		Path  remoteFIle =  new Path(remoteFolder+filename);
		System.out.println("folder local : "  + localfile + "   folder remoto : + " + remoteFIle  );
		hdfsTest.getHdfsFileSystem().copyFromLocalFile( localfile, remoteFIle );
		System.out.println("folder local : "  + localfile + "   folder remoto : + " + remoteFIle  );
		//Runtime.getRuntime().exec("hdfs dfs -copyFromLocal " + localFolder  + filename + " " + remoteFolder);
		
		hdfsTest.closeConnection();
		Assert.assertTrue(  true );
	}
	

}
