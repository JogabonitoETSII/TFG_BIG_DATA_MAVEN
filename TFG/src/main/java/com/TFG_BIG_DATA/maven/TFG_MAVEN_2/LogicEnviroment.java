package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;

import java.io.IOException;

import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

import EnviromentClass.AnalizerReportLinkClass;
import EnviromentClass.DatabaseAnalizerLinkClass;
import Hadoop.HadoopLogic;
import Mongo.MongoLogic;
import Pentaho.PentahoLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class LogicEnviroment.
 */
public class LogicEnviroment {
	
	/** The one. */
	private final Integer ONE = 1;
	
	/** The ussername. */
	private final String USSERNAME ="-u";
	
	/** The password. */
	private final String PASSWORD ="-p";
	
	/** The mongo. */
	private final String MONGO = "--MONGO";
	
	/** The hadoop. */
	private final String HADOOP = "--HADOOP";
	
	/** The pentaho. */
	private final String PENTAHO = "--PENTAHO";
	
	/** The mongoconfig. */
	private final String MONGOCONFIG = "--MONGOCONFIG";
	
	/** The hadoopconfig. */
	private final String HADOOPCONFIG = "--HADOOPCONFIG";
	
	/** The pentahoconfig. */
	private final String PENTAHOCONFIG = "--PENTAHOCONFIG";
	
	/** The mongo args inserted. */
	private  Boolean mongoArgsInserted;
	
	/** The hadoop args inserted. */
	private  Boolean hadoopArgsInserted;
	
	/** The pentaho args inserted. */
	private  Boolean pentahoArgsInserted;
	
	/** The analizer report. */
	private AnalizerReportLinkClass analizerReport;
	
	/** The database analizer. */
	private DatabaseAnalizerLinkClass databaseAnalizer;
	
	
	/**
	 * Instantiates a new model view controller.
	 */
	public LogicEnviroment () {
		setMongoArgsInserted(false);
		setHadoopArgsInserted(false);
		setPentahoArgsInserted(false);
	}

	/**
	 * Instantiates a new model view controller.
	 *
	 * @param args the args
	 */
	public LogicEnviroment(String [] args) {
		setMongoArgsInserted(false);
		setHadoopArgsInserted(false);
		setPentahoArgsInserted(false);
		setDatabaseAnalizer(new DatabaseAnalizerLinkClass());
		setAnalizerReport(new AnalizerReportLinkClass());
		MongoLogic mongoBackEndProccess = null;
		HadoopLogic hadoopBackEndProccess = null ; 
		PentahoLogic pentahoBackEndProccess = null; 
		String auxUserName = null;
		String auxPassword = null;
		for(int i = 0 ; i < args.length ; i++) {
			//System.out.println("args valor  i " + args[i]);
			switch(args[i]) {
				case  USSERNAME : {
					if( i + ONE < args.length) {
						auxUserName = args[i + ONE];
					}
					i++;
					break;
				}
				case PASSWORD : {
					if( i + ONE < args.length) {
						auxPassword = args[i + ONE];
					}
					i++;
					break;
				}
				case MONGO :{
					if( i + ONE < args.length) {
						if(!getMongoArgsInserted()){
							mongoBackEndProccess = new MongoLogic();
							mongoBackEndProccess.parseInputsFile(args[i + ONE]);
							mongoBackEndProccess.makeConnectionString(auxUserName, auxPassword, mongoBackEndProccess.getIpServer()
									,mongoBackEndProccess.getDatabaseConnect());
							mongoBackEndProccess.createConecctionOnDatabase();
							setMongoArgsInserted(true);
						}
					}
					else {
						System.out.println("Error  falta el path del fichero de configuracion de Mongo");
					}
					i++;
					break;
				}
				case HADOOP : {
					
					if( i + ONE < args.length) {
						if(!getHadoopArgsInserted()) {
							hadoopBackEndProccess = new HadoopLogic();
							hadoopBackEndProccess.parseInputsFile(args[i + ONE]);
							setHadoopArgsInserted(true);
						}
					}
					else {
						System.out.println("Error  falta el path del fichero de configuracion de Hadoop");
					}
					i++;
					break;
				}
				
				case PENTAHO : {
					
					if( i + ONE < args.length) {
						if(!getPentahoArgsInserted()) {
							try {
								pentahoBackEndProccess = new PentahoLogic();
								pentahoBackEndProccess.parseInputsFile(args[i + ONE]);
								setPentahoArgsInserted(true);
							} catch (ReportProcessingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else {
						System.out.println("Error  falta el path del fichero de configuracion de Pentaho");
					}
					i++;
					break;
				}
			}
		}
		setObjectEnviroment(mongoBackEndProccess,hadoopBackEndProccess,  pentahoBackEndProccess );
		executeWOrkFlow();
	}

	
	/**
	 * Execute Work flow.
	 */
	private void executeWOrkFlow() {
		
		System.out.println("pentaho " +  getPentahoArgsInserted() + "    +  " + getHadoopArgsInserted());
		if(getMongoArgsInserted() && getHadoopArgsInserted()) { // mongo con hadoop
			getDatabaseAnalizer().executeWorkFlowDatabaseAnalizer();
			if(getPentahoArgsInserted() ) {
				getAnalizerReport().makeReportAnalizerWorkFlow();
			}
		}
		else if(getPentahoArgsInserted() && getHadoopArgsInserted()) { // pentaho con hadoop
			getAnalizerReport().makeReportAnalizerWorkFlow();
		}
		else if (getMongoArgsInserted()  && getPentahoArgsInserted() ) { // pentaho con mongo 
			getDatabaseAnalizer().executeWOrkFlowDatabase();
			getAnalizerReport().makeReportWorkFlow();
		}
		else if(getMongoArgsInserted() ) {
			getDatabaseAnalizer().executeWOrkFlowDatabase(); // solo mongo
		}
		else if(getPentahoArgsInserted()) { // solo pentaho
			
		}
		else if(getHadoopArgsInserted()) { // solo hadoop
			getDatabaseAnalizer().executeAnalizerWorkFlow();
		}
		
	}

	/**
	 * Sets the object enviroment.
	 *
	 * @param mongoBackEndProccess2 the mongo back end proccess 2
	 * @param hadoopBackEndProccess2 the hadoop back end proccess 2
	 * @param pentajoBackEndProccess2 the pentajo back end proccess 2
	 */
	public void setObjectEnviroment(MongoLogic mongoBackEndProccess, HadoopLogic hadoopBackEndProccess, PentahoLogic pentahoBackEndProccess2) {
		
		if(getMongoArgsInserted()) {
			getDatabaseAnalizer().setDataBase(mongoBackEndProccess);
		}
		if(getHadoopArgsInserted()) {
			getDatabaseAnalizer().setAnalizer(hadoopBackEndProccess);
			getAnalizerReport().setAnalizer(hadoopBackEndProccess);
		}
		if(getPentahoArgsInserted()) {
			getAnalizerReport().setReport(pentahoBackEndProccess2);
		}
		
	}
	

	/**
	 * Gets the mongo args inserted.
	 *
	 * @return the mongo args inserted
	 */
	public Boolean getMongoArgsInserted() {
		return mongoArgsInserted;
	}

	/**
	 * Gets the hadoop args inserted.
	 *
	 * @return the hadoop args inserted
	 */
	public Boolean getHadoopArgsInserted() {
		return hadoopArgsInserted;
	}

	/**
	 * Gets the pentaho args inserted.
	 *
	 * @return the pentaho args inserted
	 */
	public Boolean getPentahoArgsInserted() {
		return pentahoArgsInserted;
	}

	/**
	 * Sets the mongo args inserted.
	 *
	 * @param mongoArgsInserted the new mongo args inserted
	 */
	public void setMongoArgsInserted(Boolean mongoArgsInserted) {
		this.mongoArgsInserted = mongoArgsInserted;
	}

	/**
	 * Sets the hadoop args inserted.
	 *
	 * @param hadoopArgsInserted the new hadoop args inserted
	 */
	public void setHadoopArgsInserted(Boolean hadoopArgsInserted) {
		this.hadoopArgsInserted = hadoopArgsInserted;
	}

	/**
	 * Sets the pentaho args inserted.
	 *
	 * @param pentahoArgsInserted the new pentaho args inserted
	 */
	public void setPentahoArgsInserted(Boolean pentahoArgsInserted) {
		this.pentahoArgsInserted = pentahoArgsInserted;
	}

	/**
	 * Gets the analizer report.
	 *
	 * @return the analizer report
	 */
	public AnalizerReportLinkClass getAnalizerReport() {
		return analizerReport;
	}

	/**
	 * Sets the analizer report.
	 *
	 * @param analizerReport the new analizer report
	 */
	public void setAnalizerReport(AnalizerReportLinkClass analizerReport) {
		this.analizerReport = analizerReport;
	}

	/**
	 * Gets the database analizer.
	 *
	 * @return the database analizer
	 */
	public DatabaseAnalizerLinkClass getDatabaseAnalizer() {
		return databaseAnalizer;
	}

	/**
	 * Sets the database analizer.
	 *
	 * @param databaseAnalizer the new database analizer
	 */
	public void setDatabaseAnalizer(DatabaseAnalizerLinkClass databaseAnalizer) {
		this.databaseAnalizer = databaseAnalizer;
	}
	

}
