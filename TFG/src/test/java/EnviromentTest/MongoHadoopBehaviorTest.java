package EnviromentTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
	 */
	@Test
	public void test() {
		
		initAtributes();
		makeTestArgumentsFields("type", "Music");
		makeTestArguments("eq");
		
		MatchFilterObject match = new MatchFilterObject(fieldName, fieldValue, filters);
		
		String connectionString = "mongodb://" + USER + ":" + PASS + "@127.0.0.1:27017/" + DATABASENAME;
		MongoLogic connectionDatabase = null;
		connectionDatabase = new MongoLogic(connectionString);
		connectionDatabase.exportToDataToFile("folderPath","filename",connectionDatabase.AggregateMatch(match, DATABASENAME, "test"));
		
		
		
		
		
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
