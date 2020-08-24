package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;

public class BigDataEnviroment {
	private static BigDataEnviroment enviroment;
	private static ModelViewController modelViewControllerEnviroment;
	
	public static void main(String[] args) {
		
		if (enviroment == null) {
			enviroment = new BigDataEnviroment();
		}
	}
	
	private BigDataEnviroment() {
		modelViewControllerEnviroment = new ModelViewController();
	}
	
}
