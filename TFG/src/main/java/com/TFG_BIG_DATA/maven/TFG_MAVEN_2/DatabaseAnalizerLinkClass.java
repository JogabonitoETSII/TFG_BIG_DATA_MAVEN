package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;

import EnviromentClass.AnalizerObject;
import EnviromentClass.DatabaseObject;

public class DatabaseAnalizerLinkClass {

	
	private DatabaseObject dataBase;
	
	private AnalizerObject analizer;
	
	
	public DatabaseAnalizerLinkClass() {}
	
	public void initLinker(DatabaseObject dataBase,AnalizerObject analizer ) {
		setDataBase(dataBase);
		setAnalizer(analizer);
	}

	public void executeFileToAnalice() {
		String[]  auxFilePathToAnalize;
		auxFilePathToAnalize  = getDataBase().filePathToAnalize();
		getAnalizer().setFilePathToAnalize(auxFilePathToAnalize[0]); // get the file path
		getAnalizer().setFileName(auxFilePathToAnalize[1]); // set the file path
		getAnalizer().execute();
		
	}
	
	
	public DatabaseObject  getDataBase() {
		return dataBase;
	}


	public void setDataBase(DatabaseObject dataBase) {
		this.dataBase = dataBase;
	}


	public AnalizerObject getAnalizer() {
		return analizer;
	}


	public void setAnalizer(AnalizerObject analizer) {
		this.analizer = analizer;
	}
	
	
	
	
}
