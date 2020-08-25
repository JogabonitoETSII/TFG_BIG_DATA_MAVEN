package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;

import java.io.File;

import EnviromentClass.AnalizerObject;
import EnviromentClass.DatabaseObject;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseAnalizerLinkClass.
 */
public class DatabaseAnalizerLinkClass {

	
	private final int FILEPATHPOSITION= 0;
	private final int FILENAMEPOSITION = 1;
	private final String SUCCESPROCESS ="_SUCCESS";
	/** The data base. */
	private DatabaseObject dataBase;
	
	/** The analizer. */
	private AnalizerObject analizer;
	
	
	/**
	 * Instantiates a new database analizer link class.
	 */
	public DatabaseAnalizerLinkClass() {}
	
	/**
	 * Inits the linker.
	 *
	 * @param dataBase the data base
	 * @param analizer the analizer
	 */
	public void initLinker(DatabaseObject dataBase,AnalizerObject analizer ) {
		setDataBase(dataBase);
		setAnalizer(analizer);
	}

	/**
	 * Execute file to analice.
	 */
	public void executeFileToAnalice() {
		String[]  auxFilePathToAnalize;
		auxFilePathToAnalize  = getDataBase().filePathToAnalize();
		setPathTOExecute(auxFilePathToAnalize[FILEPATHPOSITION], auxFilePathToAnalize[FILENAMEPOSITION]);
		getAnalizer().uploadData(getAnalizer().getOutputPath());
		getAnalizer().execute();
		getAnalizer().downloadResult();
		
	}
	
	public void setPathTOExecute(String pathFile, String fileName) {
		getAnalizer().setFilePathToAnalize(pathFile); // get the file path
		getAnalizer().setFileName(fileName);
	}
	/**
	 * Gets the data base.
	 *
	 * @return the data base
	 */
	public DatabaseObject  getDataBase() {
		return dataBase;
	}


	/**
	 * Sets the data base.
	 *
	 * @param dataBase the new data base
	 */
	public void setDataBase(DatabaseObject dataBase) {
		this.dataBase = dataBase;
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
	 * Succes build solution.
	 *
	 * @return the boolean
	 */
	public Boolean succesBuildSolution() {	
		
		//System.out.println("el fichero existe? " + getAnalizer().getFilePathToAnalize()+"_SUCCESS");
		
		File auxFile = new File(getAnalizer().getFilePathToAnalize()+SUCCESPROCESS);
		return auxFile.exists();
	}
}
