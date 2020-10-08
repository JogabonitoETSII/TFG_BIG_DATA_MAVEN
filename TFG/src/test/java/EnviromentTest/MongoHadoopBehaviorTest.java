package EnviromentTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import EnviromentClass.DatabaseAnalizerLinkClass;
import Hadoop.HadoopLogic;
import Mongo.MatchFilterObject;
import Mongo.MongoLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class MongoHadoopBehaviorTest.
 */
public class MongoHadoopBehaviorTest {

	/** The databasename. */
	final String DATABASENAME = "test";

	/** The user. */
	final String USER = "test";

	/** The pass. */
	final String PASS = "test";
	
	/** The collection. */
	final String COLLECTION = "products";
	
	final String connecitonStirngToDFS = "hdfs://localhost:9000";
	
	/** The aux field name. */
	private ArrayList<String> auxFieldName;

	/** The aux field value. */
	private ArrayList<Object> auxFieldValue;

	/** The field name. */
	private ArrayList<ArrayList<String>> fieldName;

	/** The field value. */
	private ArrayList<ArrayList<Object>> fieldValue;

	/** The filters. */
	private ArrayList<String> filters;
	
	
	/**
	 * Test.
	 * @throws IOException 
	 */
	@Test
	public void test() throws IOException {
		/*DatabaseAnalizerLinkClass mongoHadoopLinked = new DatabaseAnalizerLinkClass();
		HadoopLogic hadoopYarn = new HadoopLogic(connecitonStirngToDFS);
		initAtributes();
		makeTestArgumentsFields("type", "Music");
		makeTestArguments("eq");
		MatchFilterObject match = new MatchFilterObject(fieldName, fieldValue, filters);
		String connectionString = "mongodb://" + USER + ":" + PASS + "@127.0.0.1:27017/" + DATABASENAME;
		MongoLogic connectionDatabase = null;
		connectionDatabase = new MongoLogic(connectionString);
		connectionDatabase.AggregateProjection("sku", false); // kitamos los campos que puedan ralentizar el proceso
		connectionDatabase.AggregateMatch(match); // setemaos la query
		connectionDatabase.exportToDataToFile("/home/alberto/Escritorio/","exportTEST1" ,connectionDatabase.findDocuments(DATABASENAME, COLLECTION) ); // exportmaos los datos
		//connectionDatabase.setFilePathToExport(new String[] {"/home/alberto/Escritorio/","exportTEST1"}); 
		hadoopYarn.getYarn().setYarnHome("/home/hadoop/hadoop-2.8.5/bin/yarn"); // añadimos el home del yarn en local
		hadoopYarn.setRemotePath("/albertoHome/"); // añadimos el path remoto donde se va a subir el fichero
		hadoopYarn.setOutputPath("/albertoHome/outputTEST1"); // añadimos el folder para el fichero de salida
		hadoopYarn.setYarnJarFileToExecute("/home/hadoop/hadoop-2.8.5/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.5.jar"); // añadimos el fichero jar a ejecuta
		hadoopYarn.setYarnJarOption("wordcount"); // añadimos la opcion para el fichero jar
		mongoHadoopLinked.setDataBase(connectionDatabase); // seteamos el objeto de BBDD
		mongoHadoopLinked.setAnalizer(hadoopYarn); // seteamos el obejto analizardor
		mongoHadoopLinked.setPathTOExecute("/home/alberto/Escritorio/outputTEST1/", "exportTEST1"); // 
		String line = hadoopYarn.getResultFile();
		mongoHadoopLinked.executeFileToAnalice();
		//hadoopYarn.downloadResult();
		//System.out.println( "devuelve ture si existe  el fichero _SUCCES  " +  mongoHadoopLinked.succesBuildSolution()); // comprobamos que el fichero __SUCCES se encuentra en local
		
		System.out.println("devuelve correctamente el nombre del fichero? " + line);
		Assert.assertTrue(mongoHadoopLinked.succesBuildSolution());*/
	}

	/**
	 * Make test arguments.
	 *
	 * @param filter the filter
	 */
	public void makeTestArguments(String filter) {
		getFilters().add(filter);
		getFieldName().add(getAuxFieldName());
		getFieldValue().add(getAuxFieldValue());
	}

	/**
	 * Make test arguments fields.
	 *
	 * @param fieldName  the field name
	 * @param fieldValue the field value
	 */
	public void makeTestArgumentsFields(String fieldName, String fieldValue) {
		getAuxFieldName().add(fieldName);
		getAuxFieldValue().add(fieldValue);
	}

	/**
	 * Inits the atributes.
	 */
	public void initAtributes() {
		setFieldName(new ArrayList<ArrayList<String>>());
		setFieldValue((new ArrayList<ArrayList<Object>>()));
		setFilters(new ArrayList<String>());
		setAuxFieldName(new ArrayList<String>());
		setAuxFieldValue(new ArrayList<Object>());
	}

	/**
	 * Reset auxiliars.
	 */
	public void resetAuxiliars() {
		setAuxFieldName(new ArrayList<String>());
		setAuxFieldValue(new ArrayList<Object>());
	}

	/**
	 * Gets the aux field name.
	 *
	 * @return the aux field name
	 */
	public ArrayList<String> getAuxFieldName() {
		return auxFieldName;
	}

	/**
	 * Sets the aux field name.
	 *
	 * @param auxFieldName the new aux field name
	 */
	public void setAuxFieldName(ArrayList<String> auxFieldName) {
		this.auxFieldName = auxFieldName;
	}

	/**
	 * Gets the aux field value.
	 *
	 * @return the aux field value
	 */
	public ArrayList<Object> getAuxFieldValue() {
		return auxFieldValue;
	}

	/**
	 * Sets the aux field value.
	 *
	 * @param auxFieldValue the new aux field value
	 */
	public void setAuxFieldValue(ArrayList<Object> auxFieldValue) {
		this.auxFieldValue = auxFieldValue;
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public ArrayList<ArrayList<String>> getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(ArrayList<ArrayList<String>> fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the field value.
	 *
	 * @return the field value
	 */
	public ArrayList<ArrayList<Object>> getFieldValue() {
		return fieldValue;
	}

	/**
	 * Sets the field value.
	 *
	 * @param fieldValue the new field value
	 */
	public void setFieldValue(ArrayList<ArrayList<Object>> fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * Gets the filters.
	 *
	 * @return the filters
	 */
	public ArrayList<String> getFilters() {
		return filters;
	}

	/**
	 * Sets the filters.
	 *
	 * @param filters the new filters
	 */
	public void setFilters(ArrayList<String> filters) {
		this.filters = filters;
	}
	
	
}
