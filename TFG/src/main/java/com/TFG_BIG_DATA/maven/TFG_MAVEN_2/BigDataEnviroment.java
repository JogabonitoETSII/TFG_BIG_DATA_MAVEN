package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;



// TODO: Auto-generated Javadoc
/**
 * The Class BigDataEnviroment.
 */
public class BigDataEnviroment {
	
	
	/** The enviroment. */
	private static BigDataEnviroment enviroment;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		if (enviroment == null) {
			enviroment = new BigDataEnviroment(args);
		}
	}
	
	/**
	 * Instantiates a new big data enviroment.
	 *
	 * @param args the args
	 */
	private BigDataEnviroment(String [] args) {

		
		if(args.length > 1) {
			new ModelViewController(args);
		}
		else {
			System.out.println("Valid arguments to Enviroment is");
			System.out.println("-u USER , is a user to database mongo");
			System.out.println("-p PASSWORD, is a password to user mongo");
			System.out.println("--Mongo PATH config mongo file ");
			System.out.println("--Hadoop Path config hadoop file");
			System.out.println("--Pentaho Path config pentaho file");
			System.out.println("--MongoConfig configuration file example");
			System.out.println("--HadoopConfig configuration file example");
			System.out.println("--PentahoConfig configuration file example");
		}
		
	}
}
