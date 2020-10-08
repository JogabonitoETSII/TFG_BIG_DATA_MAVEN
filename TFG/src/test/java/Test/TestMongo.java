package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.Assert;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

import Mongo.MongoLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class TestMongo.
 */
public class TestMongo {

	/** The collectioname. */
	final String COLLECTIONAME = "products";

	/** The collectionamecruds. */
	final String COLLECTIONAMECRUDS = "testInsertUpdateDelete";

	/** The databasename. */
	final String DATABASENAME = "test";

	/** The user. */
	final String USER = "test";

	/** The pass. */
	final String PASS = "test";

	/** The insert test. */
	private HashMap<String, Object> insertUpdateTest;

	/** The insert many test. */
	private ArrayList<HashMap<String, Object>> insertUpdateManyTest;

	/**
	 * Test.
	 */
	@org.junit.Test
	public void test() {

		String connectionString = "mongodb://" + USER + ":" + PASS + "@127.0.0.1:27017/" + DATABASENAME;
		MongoLogic connectionDatabase = null;
		 //connectionDatabase = new MongoLogic(connectionString);
		connectionDatabase = new MongoLogic();
		
		//connectionDatabase.parseInputsFile("/home/alberto/Escritorio");
		
		
		/*
		 * MongoLogicConnecitonTest(connectionDatabase);
		 * MongoGetDatabasesTest(connectionDatabase);
		 * MongoExisteAnyDatabaseTest(connectionDatabase);
		 * getDatabaseTest(connectionDatabase);
		 * getDatabasesReadPreferenceTest(connectionDatabase);
		 * getCollection(connectionDatabase);
		 * 
		 * singlesStageAggregation(connectionDatabase);
		 * connectionDatabase.mongoCLoseDBConnection();
		 */
		// initTestVariables();
		// testInsertOne(connectionDatabase);
		// testInsertMany(connectionDatabase);
		// testUpdateOne(connectionDatabase);
		// testUpdateMany(connectionDatabase);
		// deleteOneTest(connectionDatabase);

		// deleteManyTest(connectionDatabase);
		/*try {
			workflowParseInputFileExporFile(connectionDatabase,"/home/alberto/Escritorio/inputFileMongo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	/*
	 * @org.junit.Test public void MongoConnectionTest() {
	 * 
	 * String connectionString ="mongodb+srv://admin:admin@127.0.0.1:27017/admin";
	 * ConnectionString Uri = new ConnectionString(connectionString);
	 * MongoClientSettings settings = MongoClientSettings.builder()
	 * .applyConnectionString(Uri) .applicationName("")
	 * .applyToConnectionPoolSettings( builder -> builder.maxWaitTime(1000,
	 * TimeUnit.MILLISECONDS) ).build(); MongoClient connection =
	 * MongoClients.create(settings);
	 * 
	 * }
	 */

	public void workflowParseInputFileExporFile(MongoLogic connectionDatabase ,String Path) throws IOException {
		
		Assert.assertTrue(connectionDatabase.parseInputsFile(Path));
		connectionDatabase.makeConnectionString("test", "test", connectionDatabase.getIpServer(), connectionDatabase.getDatabaseConnect());
		connectionDatabase.createConecctionOnDatabase();
		connectionDatabase.exportToDataToFile();
	}
	
	/**
	 * Parses the input file test.
	 *
	 * @param connectionDatabase the connection database
	 * @param Path the path
	 */
	public void parseInputFileTest(MongoLogic connectionDatabase ,String Path) {
		Assert.assertTrue(connectionDatabase.parseInputsFile(Path));
	}
	
	
	/**
	 * Mongo logic conneciton test.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void MongoLogicConnecitonTest(MongoLogic connectionDatabase) {
		Assert.assertNotNull(connectionDatabase);
	}

	/**
	 * Mongo get databases test.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void MongoGetDatabasesTest(MongoLogic connectionDatabase) {
		List<String> databasesNames = new ArrayList<>();
		databasesNames = connectionDatabase.listDatabasesNames();
		Assert.assertTrue(databasesNames.contains(DATABASENAME));
	}

	/**
	 * Mongo existe any database test.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void MongoExisteAnyDatabaseTest(MongoLogic connectionDatabase) {
		Assert.assertTrue(connectionDatabase.ExisteAnyDatabase(DATABASENAME));
	}

	/**
	 * Gets the database test.
	 *
	 * @param connectionDatabase the connection database
	 * @return the database test
	 */
	public void getDatabaseTest(MongoLogic connectionDatabase) {
		connectionDatabase.getDatabases(DATABASENAME);
	}

	/**
	 * Gets the databases read preference test.
	 *
	 * @param connectionDatabase the connection database
	 * @return the databases read preference test
	 */
	public void getDatabasesReadPreferenceTest(MongoLogic connectionDatabase) {
		Assert.assertEquals("primary", connectionDatabase.getReadPreference(DATABASENAME).getName());
	}

	/**
	 * Gets the collection.
	 *
	 * @param connectionDatabase the connection database
	 * @return the collection
	 */
	public void getCollection(MongoLogic connectionDatabase) {
		MongoCollection<Document> actuallyCollection = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAME);
		MongoIterable<Document> cursor = actuallyCollection.find().skip(10).limit(20);
		List<Document> documents = new ArrayList<Document>();
		Assert.assertEquals(20, cursor.into(documents).size());
	}

	/**
	 * Singles stage aggregation. Test realizado con la finalidad de comprobar si
	 * conectamos y somos capaces de leer de la BBDD
	 * 
	 * @param connectionDatabase the connection database
	 */
	public void singlesStageAggregation(MongoLogic connectionDatabase) {
		String fieldName = "type";
		String fieldValue = "Music";
		Bson query = Filters.eq(fieldName, fieldValue);
		List<Bson> pipeline = new ArrayList<Bson>();
		Bson matchStage = Aggregates.match(query);
		pipeline.add(matchStage);
		AggregateIterable<Document> iterable = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAME)
				.aggregate(pipeline);

		List<Document> builderMatchStageResult = new ArrayList<Document>();
		iterable.into(builderMatchStageResult);
		Assert.assertEquals(424117, builderMatchStageResult.size());
	}

	/**
	 * Test insert many.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void testInsertMany(MongoLogic connectionDatabase) {
		createDocumentsToManyInsertUpdateTest();
		List<Document> insertTestDOcument = new ArrayList<Document>();
		List<ObjectId> objectIdTest = new ArrayList<>();
		insertTestDOcument = connectionDatabase.insertManyDocuments(
				connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS), getInsertManyTest());
		for (Document obtainObjecId : insertTestDOcument) {
			objectIdTest.add(obtainObjecId.getObjectId("_id"));
		}
		Long expected = (long) 3;
		Long inserted = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.countDocuments(Filters.in("_id", objectIdTest));
		Assert.assertEquals(expected, inserted);
	}

	/**
	 * Test one many.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void testInsertOne(MongoLogic connectionDatabase) {

		createDocumentsToInsertUpdateTest();
		Document insertTestDOcument = new Document();
		insertTestDOcument = connectionDatabase.insertOneDocument(
				connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS), getInsertTest());
		Document inserted = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.find(Filters.eq("_id", insertTestDOcument.getObjectId("_id"))).first();
		Assert.assertEquals(inserted, insertTestDOcument);

	}

	/**
	 * Test update one.
	 *
	 * @param connectionDatabase the connection database
	 */
	void testUpdateOne(MongoLogic connectionDatabase) {

		Document updatdeDocument = new Document();
		createDocumentsToInsertUpdateTest();
		ObjectId documentToUbdate = new ObjectId("5f2922eb000de42e76ce8696");

		connectionDatabase.updateOne(connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS),
				documentToUbdate, getInsertTest());
		// crear un objeto idOBject y pasarlo por parametro como se penso originalmente.

		updatdeDocument = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.find(Filters.eq("_id", documentToUbdate)).iterator().tryNext();

		Assert.assertEquals(updatdeDocument.get("cuidad"), "Puerto de la cruz");
		Assert.assertEquals(updatdeDocument.get("codigo postal"), 31025);
	}

	/**
	 * Test update many.
	 *
	 * @param connectionDatabase the connection database
	 */
	void testUpdateMany(MongoLogic connectionDatabase) {

		Document updatdeDocument = new Document(); // documento para filtrar los que se tienen que actualizar.
		FindIterable<Document> iteratorFInd; // iterador del filtor de mongo

		ArrayList<ObjectId> listObjectUpdate = new ArrayList<ObjectId>();
		createDocumentsToInsertUpdateTest(); // se crea los campos actualizados
		HashMap<String, Object> documentToUpdate = new HashMap<String, Object>(); // campos a actualizar;

		iteratorFInd = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS).find(updatdeDocument); // guardamos
																													// el
																													// objectId
																													// Para
																													// confirmar
																													// el
																													// update
		listObjectUpdate.add(iteratorFInd.iterator().tryNext().getObjectId("_id")); // guardamos el object ID
		connectionDatabase.updateMany(connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS),
				documentToUpdate, getInsertTest()); // realizamos el update
		iteratorFInd = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.find(Filters.eq(listObjectUpdate.get(0))); // volvemos a coger el documento actualizado
		updatdeDocument = iteratorFInd.iterator().tryNext(); // lo guardamos

		Assert.assertNotEquals(updatdeDocument.get("cuidad"), documentToUpdate.get("cuidad"));

	}

	/**
	 * Delete one test.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void deleteOneTest(MongoLogic connectionDatabase) {

		ObjectId documentToUbdate = new ObjectId("5f2922eb000de42e76ce8696");
		connectionDatabase.deleteOne(connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS),
				documentToUbdate);
		Assert.assertNull(connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.find(Filters.eq(documentToUbdate)).iterator().tryNext());
	}

	/**
	 * Delete many test.
	 *
	 * @param connectionDatabase the connection database
	 */
	public void deleteManyTest(MongoLogic connectionDatabase) {
		Document updatdeDocument = new Document();
		HashMap<String, Object> documentToUpdate = new HashMap<String, Object>();
		documentToUpdate.put("cuidad", "la laguna"); // se meten los campos en un documento para utilziar de fomra
														// nativa la api de Mongo
		updatdeDocument.putAll(documentToUpdate);// se insertan los campos a modificar en la variable del filtro
		DeleteResult resultToDelete;
		Long documentsToDelete = connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS)
				.countDocuments(updatdeDocument);

		resultToDelete = connectionDatabase
				.deleteMany(connectionDatabase.getAnyCollection(DATABASENAME, COLLECTIONAMECRUDS), documentToUpdate);
		Assert.assertEquals(documentsToDelete, (Long) resultToDelete.getDeletedCount());
	}

	/**
	 * Creates the documents to insert test.
	 */
	public void createDocumentsToInsertUpdateTest() {

		// getInsertTest().put("cuidad", "Puerto de la cruz");
		// getInsertTest().put("codigo postal", 31025);

		getInsertTest().put("cuidad", "la laguna");
		getInsertTest().put("codigo postal", 38025);

		/*
		 * getInsertTest().put("Direccion", "calle barcelona nº6" );
		 * getInsertTest().put("Piso", 3 ); getInsertTest().put("Puerta",21);
		 */

	}

	/**
	 * Creates the documents to many insert test.
	 */
	public void createDocumentsToManyInsertUpdateTest() {

		getInsertTest().clear();
		getInsertManyTest().clear();
		setInsertTest(new HashMap<String, Object>());
		// System.out.println("contenido del arraylist al crearlo 0 " +
		// getInsertManyTest() );
		getInsertTest().put("cuidad", "la laguna");
		getInsertTest().put("codigo postal", 38025);
		getInsertTest().put("Direccion", "calle barcelona nº6");
		getInsertTest().put("Piso", 3);
		getInsertTest().put("Puerta", 21);
		getInsertManyTest().add(getInsertTest());

		setInsertTest(new HashMap<String, Object>());

		// System.out.println("contenido del arraylist al crearlo 1 " +
		// getInsertManyTest() );
		getInsertTest().put("cuidad", "GUimar");
		getInsertTest().put("codigo postal", 36025);
		getInsertTest().put("Direccion", "calle camberi nº12");
		getInsertTest().put("Piso", 5);
		getInsertTest().put("Puerta", 51);
		getInsertManyTest().add(getInsertTest());

		setInsertTest(new HashMap<String, Object>());
		getInsertTest().clear();
		// System.out.println("contenido del arraylist al crearlo 2 " +
		// getInsertManyTest() );
		getInsertTest().put("cuidad", "Santa cruz");
		getInsertTest().put("codigo postal", 35002);
		getInsertTest().put("Direccion", "calle La Salle nº 34");
		getInsertTest().put("Piso", 8);
		getInsertTest().put("Puerta", 81);
		getInsertManyTest().add(getInsertTest());
		// getInsertTest().clear();
		// System.out.println("contenido del arraylist al crearlo 3 " +
		// getInsertManyTest() );

	}

	/**
	 * Inits the test variables.
	 */
	public void initTestVariables() {
		setInsertTest(new HashMap<String, Object>());
		setInsertManyTest(new ArrayList<HashMap<String, Object>>());
	}

	/**
	 * Gets the insert test.
	 *
	 * @return the insert test
	 */
	public HashMap<String, Object> getInsertTest() {
		return insertUpdateTest;
	}

	/**
	 * Sets the insert test.
	 *
	 * @param insertTest the insert test
	 */
	public void setInsertTest(HashMap<String, Object> insertTest) {
		this.insertUpdateTest = insertTest;
	}

	/**
	 * Gets the insert many test.
	 *
	 * @return the insert many test
	 */
	public ArrayList<HashMap<String, Object>> getInsertManyTest() {
		return insertUpdateManyTest;
	}

	/**
	 * Sets the insert many test.
	 *
	 * @param insertManyTest the insert many test
	 */
	public void setInsertManyTest(ArrayList<HashMap<String, Object>> insertManyTest) {
		this.insertUpdateManyTest = insertManyTest;
	}

}
