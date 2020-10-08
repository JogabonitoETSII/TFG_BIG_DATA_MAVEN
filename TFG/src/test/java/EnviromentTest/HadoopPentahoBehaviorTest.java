package EnviromentTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

import EnviromentClass.AnalizerReportLinkClass;
import Hadoop.HadoopLogic;
import Pentaho.PentahoLogic;

public class HadoopPentahoBehaviorTest {

	@Test
	public void test() throws ReportProcessingException, IOException {
		
		/*
		HadoopLogic hadoopYarn = new HadoopLogic();
		PentahoLogic pentaho =  new PentahoLogic();
		AnalizerReportLinkClass hadoopPentaho = new AnalizerReportLinkClass();
		String[] columNames = {"Palabras","Cantidad"};
		hadoopPentaho.setAnalizer(hadoopYarn);
		hadoopPentaho.setReport(pentaho);
		hadoopYarn.setFilePathToAnalize("/home/alberto/Escritorio/");
		hadoopYarn.getYarn().setYarnHome("/home/hadoop/hadoop-2.8.5/bin/yarn"); // añadimos el home del yarn en local
		hadoopYarn.setRemotePath("/albertoHome/"); // añadimos el path remoto donde se va a subir el fichero
		hadoopYarn.setOutputPath("/albertoHome/outputTEST1"); // añadimos el folder para el fichero de salida
		hadoopYarn.setYarnJarFileToExecute("/home/hadoop/hadoop-2.8.5/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.5.jar"); 
		//pentaho.setOutputFileReportName("/2lBueno");
		System.out.println("fichero a analizar devuelto por la fuincion " + hadoopPentaho.getAnalizer().getResultFile());
		/*pentaho.createReportWordCounts(hadoopPentaho.getAnalizer().getFilePathToAnalize()+hadoopPentaho.getAnalizer().outputLocalFolder()
				,hadoopPentaho.getAnalizer().getResultFile() ,"\t", columNames, "Palabras", "Cantidad", "Palabras", new String[] {""});
		pentaho.setOutputFileReportName("/ouputTest1");
		pentaho.setImportDelimiter("\t");
		pentaho.setColumsNames(columNames);
		pentaho.setSelectDataColum("Palabras");
		pentaho.setDataEJeY("Cantidad");
		pentaho.setDataEjex("Palabras");
		pentaho.setSeriesColorReport( new String[] {""});
		pentaho.setReportType("TABLE");
		Assert.assertTrue(hadoopPentaho.makeReport());*/
	}

}
