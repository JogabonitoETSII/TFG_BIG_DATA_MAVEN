package Mongo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoClient;
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoException;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
// TODO: Auto-generated Javadoc
/*import java.sql.DatabaseMetaData;
import javax.swing.text.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

*/
// TODO: Auto-generated Javadoc
/**
 * The Class MongoLogic.
 */
/**
 * @author alberto
 *
 */
public class MongoLogic  {
	
	/** The Is connected. */
	private boolean connected ;
	
	/** The uri string. */
	
	private ConnectionString uriString;
	
	/** The settings. */
	private MongoClientSettings settings;
	
	
	/** The database connect. */
	private String databaseConnect;
	

	/** The database connected. */
	private MongoClient mongoClient;
	
	/** The aux document to use. */
	private org.bson.Document auxDocumentToUse; // its a Document for  used by all CRUD operations (create,read,update,delete)
	
	/** The connection string. */
	private   String connectionString ="";
	//private   String connectionString = "mongodb://admin:admin@127.0.0.1:27017/admin"
	
	/** The query creator. */
	private PojosClass queryCreator;
	
	
	/**
	 * Instantiates a new mongo logic.
	 *
	 * @throws MongoException the mongo exception
	 */
	public MongoLogic() throws MongoException {
		setQueryCreator(new PojosClass());
		createConecctionOnDatabase( getConnectionString());
	}
	
	/**
	 * Instantiates a new mongo logic.
	 *
	 * @param connectionString the connection string
	 * @throws MongoException the mongo exception
	 */
	public MongoLogic(String connectionString) throws MongoException {
		setQueryCreator(new PojosClass());
		setConnectionString(connectionString);
		createConecctionOnDatabase(connectionString);
	}
	
	/**
	 * Creates the conecction on database.
	 *
	 * @param connectionString1 the connection string 1
	 * @throws MongoException the mongo exception
	 */
	private void createConecctionOnDatabase(String connectionString1) throws MongoException {
		try {
			setConnected(true);
			createUriString(getConnectionString());
			setSettings(clientSettings());
			setMongoClient(MongoClients.create(getSettings()));
		}catch ( MongoException e) {
			System.out.println( " Error connection MongoDatabase " +  e);
			setConnected(false);
		}
		finally {
			if (getMongoClient() == null) {
				mongoCLoseDBConnection();
			}
		}
	}
	
	/**
	 * Client settings.
	 *
	 * @return the mongo client settings
	 */
	private MongoClientSettings clientSettings() {
		// añadir constantes y variable de la BBDD seleccionada.
		MongoClientSettings settings = MongoClientSettings.builder()
				.applyConnectionString(getUriString())
				.applicationName("admin")
				.applyToConnectionPoolSettings(
						builder -> builder.maxWaitTime(1000, TimeUnit.MILLISECONDS)
				).build();
		return settings;
	}
	
	
	/**
	 * Creates the uri string.
	 *
	 * @param connectionString the connection string
	 * @return the mongo client URI
	 * @throws MongoException the mongo exception
	 */
	public ConnectionString createUriString(String connectionString )throws MongoException  {
		
		try {
			setUriString(new ConnectionString(connectionString));
		}catch(MongoException  e) {
			throw  new MongoException(e.getCode(),e.getMessage());
		}
		return getUriString();
	}
	
	/**
	 * Creates the uri string.
	 *
	 * @return the mongo client URI
	 */
	public ConnectionString createUriString() {
		try {
			setUriString(new ConnectionString(getConnectionString()));
		}catch(MongoException e) {
			throw  new MongoException(e.getCode(),e.getMessage());
		}
		return getUriString();
	}
	
	/**
	 * List databases names.
	 *
	 * @return the list
	 */
	public List<String> listDatabasesNames(){
		MongoIterable<String> databaseIterableDatabaseName = getMongoClient().listDatabaseNames();
		List<String> databasesNames = new ArrayList<>();
		for (String name : databaseIterableDatabaseName) {
			databasesNames.add(name);
		}
		return databasesNames;
	
	}
	
	/**
	 * Existe any database.
	 *
	 * @param databaseNameToSearch the database name to search
	 * @return true, if successful
	 */
	public boolean ExisteAnyDatabase(String databaseNameToSearch) {
		
		MongoIterable<String> databaseIterableDatabaseName = getMongoClient().listDatabaseNames();
		List<String> databasesNames = new ArrayList<>();
		for (String name : databaseIterableDatabaseName) {
			databasesNames.add(name);
		}
		return databasesNames.contains(databaseNameToSearch);
	}
	
	/**
	 * Gets the databases.
	 *
	 * @param databaseName the database name
	 * @return the databases
	 */
	public MongoDatabase getDatabases(String databaseName) {
		return getMongoClient().getDatabase(databaseName);
	}
	
	/**
	 * Gets the read preference.
	 *
	 * @param databaseName the database name
	 * @return the read preference
	 */
	public ReadPreference getReadPreference(String databaseName) {
		MongoDatabase database = getMongoClient().getDatabase( databaseName );
		return database.getReadPreference();
	}
	/**
	 * Count numbers documents in any collection.
	 *
	 * @param collection the collection
	 * @param database   the database
	 * @return the long
	/* */
	
	public long countNumbersDocumentsInAnyCollection(String collection , String database) {
		MongoDatabase auxDatabaseSelect = getMongoClient().getDatabase(database);
		return  auxDatabaseSelect.getCollection(collection).countDocuments();

	}
	
	/**
	 * Select any collection.
	 *
	 * @param database   the database
	 * @param collection the collection
	 * @return the mongo collection
	 */
	public MongoCollection<Document> getAnyCollection( String database, String collection ) {
		MongoDatabase auxDatabaseSelect = getMongoClient().getDatabase(database);
		return auxDatabaseSelect.getCollection(collection);
	}
	
	/* Insert one document.
	 *
	 * @param collection the collection
	 * @param document the document
	 * @return the document
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Document insertOneDocument(MongoCollection collection ,HashMap<String,Object> document) {
			Document auxDocumentInserOne = new Document();
			auxDocumentInserOne.putAll(document);
			collection.insertOne(auxDocumentInserOne);
			return auxDocumentInserOne;
	}
	
	/**
	 * Insert documents.
	 *
	 * @param collection the collection
	 * @param documents the documents
	 * @return the list
	 * 
	 * se devuelve los objects ID de los documentos insertados para comprobar posteriormente que se insertaron correctamente.
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Document>insertManyDocuments(MongoCollection collection , ArrayList<HashMap<String,Object>> documents) {
		List<Document> auxInsertManyDocuments = new ArrayList<>();
		Document auxInsertOneDocument = new Document(); 
		for(int i = 0 ; i < documents.size() ;i++) {
			auxInsertOneDocument.putAll(documents.get(i));
			auxInsertManyDocuments.add(auxInsertOneDocument);
			auxInsertOneDocument = new Document(); 
		}
		collection.insertMany(auxInsertManyDocuments);
		return auxInsertManyDocuments;
	}
	
	
	/**
	 * Update one.
	 *
	 * @param collection the collection
	 * @param documentToUpdate the document to update
	 * @param listToUpdate the list to update
	 */
	public void updateOne(MongoCollection collection , ObjectId documentToUpdate, HashMap<String, Object> listToUpdate){
		collection.updateOne(Filters.eq(documentToUpdate), setOperator(listToUpdate)  );
	}
	
	/**
	 * Update many.
	 *
	 * @param collection the collection
	 * @param listActuallyValue the list actually value
	 * @param listToUpdate the list to update
	 * @return 
	 */
	public UpdateResult updateMany(MongoCollection collection, HashMap<String, Object> listActuallyValue, HashMap<String, Object> listToUpdate ){
		
		 Document testUpdateMany =  new Document(listActuallyValue);
		 //testUpdateMany =  (Document) collection.find(testUpdateMany).iterator().tryNext();
		return collection.updateMany(testUpdateMany, setOperator(listToUpdate));
		
	}
	
	public DeleteResult deleteOne(MongoCollection collection , ObjectId documentToUpdate) {
		return collection.deleteOne(Filters.eq(documentToUpdate));
	}
	
	public DeleteResult  deleteMany(MongoCollection collection, HashMap<String, Object> listToDelete) {
		Document testUpdateMany =  new Document(listToDelete);
		 //testUpdateMany =  (Document) collection.find(testUpdateMany).iterator().tryNext();
		return collection.deleteMany(testUpdateMany);
	}
	
	
	/**
	 * Sets the operator.
	 *
	 * @param listFieldValue the list field value
	 * @return the document
	 */
	
	public Document setOperator(HashMap<String, Object> listFieldValue) {
		Document parseToBson = new Document("$set" , new Document(listFieldValue) );
		return parseToBson ;
	}
	
	
	/**

	/**
	 * Checks if is connection true.
	 *
	 * @return true, if successful
	 */
	public boolean IsConnectionTrue() {
		return isConnected();	
	}
	

	/**
	 * Gets the uri string.
	 *
	 * @return the uri string
	 */
	public ConnectionString getUriString() {
		return uriString;
	}

	/**
	 * Sets the uri string.
	 *
	 * @param uriString the new uri string
	 */
	public void setUriString(ConnectionString uriString) {

		this.uriString = uriString;
	}


	/**
	 * Gets the database connected.
	 *
	 * @return the database connected
	 */
	public MongoClient getMongoClient() {
		return mongoClient;
	}


	/**
	 * Sets the database connected.
	 *
	 * @param mongoClient the new mongo client
	 */
	public void setMongoClient(MongoClient mongoClient) {
			this.mongoClient = mongoClient;
	}

    /**
	 * Mongo C lose DB connection.
	 */
    public void mongoCLoseDBConnection () {
    	
    	getMongoClient().close();
    	
    }
	/**
	 * Gets the connection string.
	 *
	 * @return the connection string
	 */
	public String getConnectionString() {
		return connectionString;
	}


	/**
	 * Sets the connection string.
	 *
	 * @param connectionString the new connection string
	 */
	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}
	
	
	/**
	 * Checks if is connected.
	 *
	 * @return true, if is connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Sets the connected.
	 *
	 * @param connected the new connected
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	/**
	 * Gets the aux document to use.
	 *
	 * @return the aux document to use
	 */
	public org.bson.Document getAuxDocumentToUse() {
		return auxDocumentToUse;
	}

	/**
	 * Sets the aux document to use.
	 *
	 * @param auxDocumentToUse the new aux document to use
	 */
	public void setAuxDocumentToUse(org.bson.Document auxDocumentToUse) {
		this.auxDocumentToUse = auxDocumentToUse;
	}

	/**
	 * Gets the query creator.
	 *
	 * @return the query creator
	 */
	public PojosClass getQueryCreator() {
		return queryCreator;
	}

	/**
	 * Sets the query creator.
	 *
	 * @param queryCreator the new query creator
	 */
	public void setQueryCreator(PojosClass queryCreator) {
		this.queryCreator = queryCreator;
	}
	
	
	/**
	 * Gets the settings.
	 *
	 * @return the settings
	 */
	public MongoClientSettings getSettings() {
		return settings;
	}

	/**
	 * Sets the settings.
	 *
	 * @param settings the new settings
	 */
	public void setSettings(MongoClientSettings settings) {
		this.settings = settings;
	}
	
	/**
	 * Gets the database connect.
	 *
	 * @return the database connect
	 */
	public String getDatabaseConnect() {
		return databaseConnect;
	}

	/**
	 * Sets the database connect.
	 *
	 * @param databaseConnect the new database connect
	 */
	public void setDatabaseConnect(String databaseConnect) {
		this.databaseConnect = databaseConnect;
	}
	
	/** The print block. 
	Block<? super org.bson.Document> printBlock = new Block<org.bson.Document>() {
	       @Override
	       public void apply(final org.bson.Document document) {
	           System.out.println(((org.bson.Document) document).toJson());
	       }
	};*/
}
