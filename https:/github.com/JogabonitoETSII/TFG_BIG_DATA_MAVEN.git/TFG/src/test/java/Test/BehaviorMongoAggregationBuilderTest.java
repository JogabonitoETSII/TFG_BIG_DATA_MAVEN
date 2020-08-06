package Test;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.assertions.Assertions;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Test;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

import Mongo.MatchFilterObject;
import Mongo.MongoLogic;
import Mongo.AggregationQueryBuilders;
import Mongo.FieldValueFilter;



// TODO: Auto-generated Javadoc
/**
 * The Class BehaviorMongoAggregationBuilderTest.
 */
public class BehaviorMongoAggregationBuilderTest {
	
	/** The collectioname. */
	final String COLLECTIONAME ="products";
	
	/** The databasename. */
	final String DATABASENAME ="test";
	
	/** The user. */
	final String USER ="test";
	
	/** The pass. */
	final String  PASS="test";
	
	
	/** The aux field name. */
	private ArrayList<String> auxFieldName;
	
	/** The aux field value. */
	private ArrayList<Object> auxFieldValue;
 	
	 /** The field name. */
	 private ArrayList<ArrayList<String>> fieldName;
	
	/** The field value. */
	private ArrayList<ArrayList<Object>> fieldValue;
	
	/** The filters. */
	private  ArrayList<String> filters;
	
	
	
	
	/**
	 * Test.
	 */
	@Test
	public void test() {
		/*String connectionString ="mongodb://"+USER+":"+PASS+"@127.0.0.1:27017/"+DATABASENAME ;
		MongoLogic connectionDatabase = null ;
		connectionDatabase = new MongoLogic(connectionString);
		AggregationQueryBuilders aggregates = new AggregationQueryBuilders();
	    BehaviorMatchStagebuilder(connectionDatabase, aggregates);
		aggregates.clearPipeline();
	    BehaviorGroupStagebuilder(connectionDatabase,aggregates);
	    aggregates.clearPipeline();
		BehaviorProjectStagebuilder(connectionDatabase,aggregates);
		*/
	}
	
	/**
	 * Behavior match stagebuilder.
	 *
	 * @param connectionDatabase the connection database
	 * @param aggregates the aggregates
	 */
	public void BehaviorMatchStagebuilder(MongoLogic connectionDatabase , AggregationQueryBuilders aggregates) {

		insertFielValuePairTest(); // se intertan los datos de test en los vectores auxiliares para crear el objeto MatfilterObject
		MatchFilterObject auxTest  = new MatchFilterObject(fieldName,fieldValue,filters);
		aggregates.addMatchStage(auxTest.matchStageBuilder()); // se coge el Bson con el stage Match.
		// NOTA PENSAR UNA FORMA CLARA DE DIFERENCIA ENTRE NUEROS Y STRINGS.
		AggregateIterable<Document> iterable =  connectionDatabase.getAnyCollection(DATABASENAME,COLLECTIONAME).aggregate(aggregates.getPipeline());
		List<Document> builderMatchStageResult = new ArrayList<Document>();
		iterable.into(builderMatchStageResult);
		System.out.println(builderMatchStageResult.size());
		Assert.assertEquals(261456, builderMatchStageResult.size());
	}
	
	/**
	 * Behavior group stagebuilder.
	 *
	 * @param connectionDatabase the connection database
	 * @param aggregates the aggregates
	 */
	public void BehaviorGroupStagebuilder(MongoLogic connectionDatabase , AggregationQueryBuilders aggregates) {
	
		aggregates.makeGroupStage("$type", "sum" , 1);
		
		AggregateIterable<Document> iterable =  connectionDatabase.getAnyCollection(DATABASENAME,COLLECTIONAME).aggregate(aggregates.getPipeline());
		List<Document> builderMatchStageResult = new ArrayList<Document>();
		iterable.into(builderMatchStageResult);
		System.out.println(builderMatchStageResult.size());
		
		for(int i = 0 ; i < builderMatchStageResult.size(); i++ ) {
			System.out.println(builderMatchStageResult.get(i));
		}
		Assert.assertEquals(4, builderMatchStageResult.size());
		
		
	}
	
	/**
	 * Behavior project stagebuilder.
	 *
	 * @param connectionDatabase the connection database
	 * @param aggregates the aggregates
	 */
	public void BehaviorProjectStagebuilder(MongoLogic connectionDatabase , AggregationQueryBuilders aggregates) {
		
		
		
		
		ArrayList<String> fields = new ArrayList<String>();
		fields.add("type");
		
		aggregates.makeProjectStage(fields, true);
		aggregates.makeLimitStage(2);
		AggregateIterable<Document> iterable =  connectionDatabase.getAnyCollection(DATABASENAME,COLLECTIONAME).aggregate(aggregates.getPipeline());
		List<Document> builderMatchStageResult = new ArrayList<Document>();
		iterable.into(builderMatchStageResult);
		System.out.println(builderMatchStageResult.size());
		
		for(int i = 0 ; i < builderMatchStageResult.size(); i++ ) {
			
			System.out.println(builderMatchStageResult.get(i));
			
			
		}
		
		Assert.assertEquals(2, builderMatchStageResult.size());
		
		
	}
	
	/**
	 * Insert fiel value pair test.
	 */
	public void insertFielValuePairTest() {
		initAtributes();
		makeTestArgumentsFields("type","Music");
		makeTestArguments("eq");
		resetAuxiliars();
		Double value = 14.99;
		makeTestArgumentsFields("regularPrice",value);
		makeTestArguments("gte");
		/*
		makeTestArgumentsFields("asdd","asdd");
		makeTestArguments("nad");
		MatchFilterObject auxTest  = new MatchFilterObject(fieldName,fieldValue,filters);
		Assert.assertEquals(3, auxTest.getFieldValueFilters().size());	
		*/
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
	 * @param fieldName the field name
	 * @param fieldValue the field value
	 */
	public  void makeTestArgumentsFields( String fieldName, Object fieldValue) {
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
	
	
}
