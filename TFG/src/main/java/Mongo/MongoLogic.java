/*
 * 
 */
package Mongo;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.client.AggregateIterable;
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

import EnviromentClass.DatabaseObject;
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
public class MongoLogic  extends DatabaseObject{
	
	
	/** The max export value. */
	private Integer maxExportValue ;
	/** The Is connected. */
	private boolean connected ;
	
	/** The uri string. */
	
	private String ipServer;
	
	/** The collection to use. */
	private String collectionToUse;
	
	/** The uri string. */
	private ConnectionString uriString;
	
	/** The settings. */
	private MongoClientSettings settings;
	
	
	/** The database connect. */
	private String databaseConnect;
	
	/** The all documents. */
	private Boolean allDocuments;
	
	/** The database connected. */
	private MongoClient mongoClient;
	
	/** The aux document to use. */
	private org.bson.Document auxDocumentToUse; // its a Document for  used by all CRUD operations (create,read,update,delete)
	
	/** The connection string. */
	private   String connectionString ="";
	//private   String connectionString = "mongodb://admin:admin@127.0.0.1:27017/admin"

	
	/** The agrregates. */
	private AggregationQueryBuilders agrregates;
	
	/** The file path to export. */
	private String[] filePathToExport;
	
	/** The exclude id find. */
	private Boolean excludeIdFind;
	/**
	 * Instantiates a new mongo logic.
	 *
	 * @throws MongoException the mongo exception
	 */
	public MongoLogic() throws MongoException {
		setExcludeIdFind(false);
		setAgrregates(new AggregationQueryBuilders());
		if(!getConnectionString().isEmpty()) {
			createConecctionOnDatabase( getConnectionString());
		}
	}
	
	/**
	 * Instantiates a new mongo logic.
	 *
	 * @param connectionString the connection string
	 * @throws MongoException the mongo exception
	 */
	public MongoLogic(String connectionString) throws MongoException {
		//setQueryCreator(new PojosClass());
		setExcludeIdFind(false);
		setAgrregates(new AggregationQueryBuilders());
		setConnectionString(connectionString);
		createConecctionOnDatabase(connectionString);
	}
	
	/**
	 * Creates the conecction on database.
	 *
	 * @param connectionString1 the connection string 1
	 * @throws MongoException the mongo exception
	 */
	public void createConecctionOnDatabase(String connectionString1) throws MongoException {
		try {
			setConnected(true);
			createUriString(connectionString1);
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
	 * Creates the conecction on database.
	 *
	 * @throws MongoException the mongo exception
	 */
	public void createConecctionOnDatabase() throws MongoException {
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
	 * Find documents.
	 *
	 * @param database the database
	 * @param collection the collection
	 * @return the aggregate iterable
	 */
	public AggregateIterable<Document> findDocuments(String database, String collection){
		excludeIdFromQuerysResult();
		return getAnyCollection(database, collection).aggregate(getAgrregates().getPipeline());
	}
	
	/**
	 * Find documents.
	 *
	 * @param database the database
	 * @param collection the collection
	 * @param pipeline the pipeline
	 * @return the aggregate iterable
	 */
	public AggregateIterable<Document> findDocuments(String database, String collection, ArrayList<Bson> pipeline){
		excludeIdFromQuerysResult();
		return getAnyCollection(database, database).aggregate(pipeline);
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
	
	/**
	 * Aggregate match.
	 *
	 * @param selects the selects
	 * @return the aggregate iterable
	 */
	public void AggregateMatch(MatchFilterObject selects){
		getAgrregates().addMatchStage(selects.matchStageBuilder());
	}
	
	/**
	 * Aggregate projection.
	 *
	 * @param fields the fields
	 * @param exclude the exclude
	 */
	public void AggregateProjection(ArrayList<String> fields , Boolean exclude) {
		getAgrregates().makeProjectStage(fields, exclude);
		
	}
	
	/**
	 * Aggregate projection.
	 *
	 * @param field the field
	 * @param exclude the exclude
	 */
	public void AggregateProjection(String field , Boolean exclude) {
		getAgrregates().makeProjectStage(field, exclude);
	}
	
	/**
	 * Insert one document.
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
	 * @return the update result
	 */
	public UpdateResult updateMany(MongoCollection collection, HashMap<String, Object> listActuallyValue, HashMap<String, Object> listToUpdate ){
		
		 Document testUpdateMany =  new Document(listActuallyValue);
		 //testUpdateMany =  (Document) collection.find(testUpdateMany).iterator().tryNext();
		return collection.updateMany(testUpdateMany, setOperator(listToUpdate));
		
	}
	
	/**
	 * Delete one.
	 *
	 * @param collection the collection
	 * @param documentToUpdate the document to update
	 * @return the delete result
	 */
	public DeleteResult deleteOne(MongoCollection collection , ObjectId documentToUpdate) {
		return collection.deleteOne(Filters.eq(documentToUpdate));
	}
	
	/**
	 * Delete many.
	 *
	 * @param collection the collection
	 * @param listToDelete the list to delete
	 * @return the delete result
	 */
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

	/**
	 * File path to analize.
	 *
	 * @return the string[]
	 */
	@Override
	public String[] filePathToAnalize() {
		// TODO Auto-generated method stub
		return getFilePathToExport();
	}

	/**
	 * Gets the file path to export.
	 *
	 * @return the file path to export
	 */
	public String[] getFilePathToExport() {
		return filePathToExport;
	}

	/**
	 * Sets the file path to export.
	 *
	 * @param filePathToExport the new file path to export
	 */
	public void setFilePathToExport(String[] filePathToExport) {
		this.filePathToExport = filePathToExport;
	}

	/**
	 * Gets the agrregates.
	 *
	 * @return the agrregates
	 */
	public AggregationQueryBuilders getAgrregates() {
		return agrregates;
	}

	/**
	 * Sets the agrregates.
	 *
	 * @param agrregates the new agrregates
	 */
	public void setAgrregates(AggregationQueryBuilders agrregates) {
		this.agrregates = agrregates;
	}
	
	/**
	 * Export to data to file.
	 *
	 * @param folderPath the folder path
	 * @param fileName the file name
	 * @param aggregateMatch the aggregate match
	 * @throws IOException ***************
	 */

	public void exportToDataToFile(String folderPath, String fileName, AggregateIterable<Document> aggregateMatch) throws IOException {
		// TODO Auto-generated method stub
		setFilePathToExport(new String[] {folderPath,fileName}); // SET FOLDER AND FILE NAME TO LINKED  A HADOOP
		
		//System.out.println("cotenido de la query " + aggregateMatch.first() );
		//System.out.println("folder y filne name  " + folderPath+fileName);
		
		@SuppressWarnings("resource")
		FileWriter auxOutputStream = new FileWriter(folderPath+fileName);
		int exportedDocuments = 0;
		for(Document outputDocs : aggregateMatch) {
			exportedDocuments++;
			auxOutputStream.write(  outputDocs.toJson() + "\n");
			if((exportedDocuments > getMaxExportValue()) && (!getAllDocuments())) {
				auxOutputStream.close();
				break;
			}
			
		}
	}
	
	/**
	 * Export to data to file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void exportToDataToFile() throws IOException {
		exportToDataToFile(getFilePath(), getFileName(), findDocuments(getDatabaseConnect(), getCollectionToUse()));
	}
	

	/**
	 * Exclude id from querys result.
	 */
	public void excludeIdFromQuerysResult() {
		String auxExcludeId = "_id";
		if(!getExcludeIdFind()) {
			getAgrregates().makeProjectStage(auxExcludeId,getExcludeIdFind());
			//System.out.println(" pipeplineeee  " + getAgrregates().getPipeline());
		}
	}
	
	/**
	 * Gets the exclude id find.
	 *
	 * @return the exclude id find
	 */
	public Boolean getExcludeIdFind() {
		return excludeIdFind;
	}

	/**
	 * Sets the exclude id find.
	 *
	 * @param excludeIdFind the new exclude id find
	 */
	public void setExcludeIdFind(Boolean excludeIdFind) {
		this.excludeIdFind = excludeIdFind;
	}

	/**
	 * Parses the inputs file.
	 *
	 * @param path the path
	 * @return true, if successful
	 */
	@Override
	public boolean parseInputsFile(String path  )  {
			// auxiliares de el MATH
			MatchFilterObject auxInsertQuerys = new MatchFilterObject();
			ArrayList<ArrayList<Object>> auxInsertValues = new ArrayList<ArrayList<Object>>();
			ArrayList<ArrayList<String>> auxInsertFields = new ArrayList<ArrayList<String>>();
			ArrayList<String> auxInsertFilters = new ArrayList<String>();
			ArrayList<Object> auxValues = new ArrayList<Object>();
			ArrayList<String> auxFields =  new ArrayList<String>();
			
			// auxliares de Projection
			
			String auxProjectionField;
			Boolean auxExclude;
			System.out.println("fichero a ejecuta . "  );
		try {
			File auxInput = new File(path);
			Scanner input = new Scanner(new File(path));
			if(auxInput.exists()) {
				setIpServer(input.nextLine()); /// insertamos la ip del servidor 
				//System.out.println("Ip leida " +  getIpServer());
				setDatabaseConnect(input.nextLine());// insertamos la BBDD a conectar
				//System.out.println("database a conectar  " +  getDatabaseConnect());
				setCollectionToUse(input.nextLine()); // insertmaos la coleccion a ejecuta.
				//System.out.println("coleccion a usar  " +  getCollectionToUse());
				setFilePath(input.nextLine()); // insertamos el directorio donde queremos dejar el fichero
				//System.out.println("path del fichero a usar exportar" +  getFilePath());
			
				setFileName(input.nextLine()); // insertamos el nombre del fichero de salida.
				
				if(input.hasNext("[-+]?\\d*\\.?\\d+")) {
					setAllDocuments(false);
					setMaxExportValue(Integer.parseInt(input.nextLine()));
				}
				else {
					setMaxExportValue(1000);
					setAllDocuments(true);
				}
				//System.out.println("nombre del fichero a usar exportar" +  getFileName());
				String [] inputLine;
				String testinput;
				if(input.hasNext(getAgrregates().getMATCH())){ // si encontramos la etiqueta <MATHC> empezamos a parsear
					input.nextLine();
						while(!input.hasNext(getAgrregates().getENDMATCH())) { // hasta que encontremos la etiqueta <ENDMATCH>

							testinput = input.nextLine(); // nos saltamos la etiqueta
							inputLine = testinput.split(",");// tokenizamos por , para obtener los filtros , campos y valores
							if(inputLine.length > 2) { // verificamos que se han introducide los 3 valores por linea
								//System.out.println("input line " +  testinput + " length del  input line " + testinput.split(",").length); tester
								auxInsertFilters.add(inputLine[getAgrregates().FILTERPOSITION]);// insertamos el filtro
								auxFields.add(inputLine[getAgrregates().FIELDPOSITION ]);//insertamos el campo
								 if(inputLine[getAgrregates().VALUEPOSITION].matches("[-+]?\\d*\\.?\\d+")) { // comprobamos que se inserta un numero
									 auxValues.add(Double.parseDouble(inputLine[getAgrregates().VALUEPOSITION]));
								}
								else {	
									auxValues.add(inputLine[getAgrregates().VALUEPOSITION]); // si no se inserta el valor del texto
								}
								 auxInsertValues.add(auxValues);
								 auxInsertFields.add(auxFields);
								 auxValues = new ArrayList<Object>(); // seteamos los auxliares de entrada para volver a insertar mas sentencias
								 auxFields =  new ArrayList<String>();// seteamos los auxliares de entrada para volver a insertar mas sentencias	 
							}
							else {
								System.out.println("los valores insertados son menores al numero requerido. !!!" +  inputLine[0]);
							}
							
						}
						input.nextLine();
						auxInsertQuerys.insertFieldValuePairs(auxInsertFields , auxInsertValues , auxInsertFilters);
						getAgrregates().setMachtStage(auxInsertQuerys);
					}
					if(input.hasNext(getAgrregates().getPROJECTION())) {
						input.nextLine();
						while(!input.hasNext(getAgrregates().getENDPROJECTION())) {
							testinput = input.nextLine(); // nos saltamos la etiqueta;
							inputLine = testinput .split(",");
							
							if((inputLine.length > 1 ) && (inputLine.length < 3)) { //para la projeccion nos aseguramos de que solo insertan dos valores.
								 // System.out.println("input line  !!!!!!!!!!!!!!!!!!!!!!!!!!!" +  testinput + " length del  input line " + testinput.split(",").length); tester
								getAgrregates().makeProjectStage(inputLine[getAgrregates().FILTERPOSITION], Boolean.parseBoolean(inputLine[getAgrregates().FILTERPOSITION]));
							}
							else {
								System.out.println("ha insertado el numero equivocado de valores. " +  inputLine[0] + " tamaño del vector " + inputLine.length );
							}
						}
						excludeIdFromQuerysResult();
					}	
				}
			else {
				System.out.println(" el ficheor no existe " + path  );
				return false;
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Make connection string.
	 *
	 * @param user the user
	 * @param password the password
	 * @param ip the ip
	 * @param database the database
	 */
	public void makeConnectionString (String user, String password, String ip , String database) {
		if((!user.isEmpty()) && (!password.isEmpty())) {
			setConnectionString("mongodb://" + user + ":" + password + "@" + ip +"/" + database );
		}
		else {
			System.out.println("Error el valor del usuario es Nulo" + user );
			if(password.isEmpty()) {
				System.out.println("Error el valor del usuario es Nulo" + user );
			}
		}
	}
	
	/**
	 * Gets the max export value.
	 *
	 * @return the max export value
	 */
	public Integer getMaxExportValue() {
		return maxExportValue;
	}

	/**
	 * Sets the max export value.
	 *
	 * @param maxExportValue the new max export value
	 */
	public void setMaxExportValue(Integer maxExportValue) {
		this.maxExportValue = maxExportValue;
	}

	/**
	 * Gets the ip server.
	 *
	 * @return the ip server
	 */
	public String getIpServer() {
		return ipServer;
	}

	/**
	 * Sets the ip server.
	 *
	 * @param ipServer the new ip server
	 */
	public void setIpServer(String ipServer) {
		this.ipServer = ipServer;
	}

	/**
	 * Gets the collection to use.
	 *
	 * @return the collection to use
	 */
	public String getCollectionToUse() {
		return collectionToUse;
	}

	/**
	 * Sets the collection to use.
	 *
	 * @param collectionToUse the new collection to use
	 */
	public void setCollectionToUse(String collectionToUse) {
		this.collectionToUse = collectionToUse;
	}

	/**
	 * Gets the all documents.
	 *
	 * @return the all documents
	 */
	public Boolean getAllDocuments() {
		return allDocuments;
	}

	/**
	 * Sets the all documents.
	 *
	 * @param allDocuments the new all documents
	 */
	public void setAllDocuments(Boolean allDocuments) {
		this.allDocuments = allDocuments;
	}

	@Override
	public boolean exportToDataToFile(String folderPath, String fileName) {
		try {
			exportToDataToFile( folderPath,fileName,findDocuments(getDatabaseConnect(), getCollectionToUse()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	
}
