package com.TFG_BIG_DATA.maven.TFG;



import Mongo.MongoLogic;



public class ModelViewController {

	
	private MongoLogic mongoBackEndProccess;
	/*private HadoopLogic hadoopBackEndProccess;
	private PentahoLogic pentajoBackEndProccess;
	*/
	
	public ModelViewController () {
		mongoBackEndProccess = new MongoLogic();
	}
	
	
	
	
	
}