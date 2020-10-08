package EnviromentTest;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.TFG_BIG_DATA.maven.TFG_MAVEN_2.ModelViewController;

public class BigDataWorkFlowTest {

	@Test
	public void test() {
		
		String[]  args = {"-u","test","-p","test","--MONGO","/home/alberto/Escritorio/inputFileMongo","--HADOOP","/home/alberto/Escritorio/inputFileHadoop","--PENTAHO","/home/alberto/Escritorio/inputFilePentaho"};
		
		System.out.println("Valid arguments to Enviroment is");
		System.out.println("-u USER , is a user to database mongo");
		System.out.println("-p PASSWORD, is a password to user mongo");
		System.out.println("--Mongo PATH config mongo file ");
		System.out.println("--Hadoop Path config hadoop file");
		System.out.println("--Pentaho Path config pentaho file");
		System.out.println("--MongoConfig configuration file example");
		System.out.println("--HadoopConfig configuration file example");
		System.out.println("--PentahoConfig configuration file example");
		ModelViewController testWorkFlow;
		testWorkFlow = new ModelViewController(args);
	//	System.out.println("JAJAJA "  + testWorkFlow.getLogics().getMongoArgsInserted());
	//	Assert.assertTrue(testWorkFlow.getLogics().getMongoArgsInserted());
	//	Assert.assertTrue(testWorkFlow.getLogics().getHadoopArgsInserted());
		
		
		
		
		
		
		
		
		
	}

}
