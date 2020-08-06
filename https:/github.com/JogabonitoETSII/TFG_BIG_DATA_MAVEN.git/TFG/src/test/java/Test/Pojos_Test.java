package Test;



import java.util.ArrayList;

import org.bson.assertions.Assertions;
import org.junit.Assert;
import org.junit.Test;

import Mongo.PojosClass;



public class Pojos_Test {

	
	@Test
	 public void test() throws InterruptedException {
		//createPojosClass_Test();
		//loadClassRunTime();
	}
	
	public void createPojosClass_Test() throws InterruptedException {
		
		PojosClass pojoTest = new PojosClass();
		    ArrayList<String> type;
			ArrayList<String> atributeName;
			String className = "Personas";
			type = new ArrayList<String>();
			atributeName = new ArrayList<String>();
			
			type.add("int");
			atributeName.add("numeroPersonas");
	    	pojoTest.createClassTextRunTime(type, atributeName, className);
	    	Assertions.isTrue("createPojosClassRuntime", pojoTest.isClassCreated() == true);
	}
	
	public void loadClassRunTime() {
		
		String className = "Personas";
		PojosClass pojoTest = new PojosClass();
		pojoTest.moveClassFileToClassPathJava(pojoTest.getPathPojoClassStorage()+className+pojoTest.getCLASSEXTENTION());
		pojoTest.loadClassInRunTime("Mongo.Personas");
		Assertions.isTrue("TEST LOAD RUNTIME CLASS", pojoTest.isClassLoad() == true);
	}
}
