package Hadoop;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;



// TODO: Auto-generated Javadoc
/**
 * The Class Yarn.
 */
public class Yarn {
	
	
	/** The bashjaroption. */
	private  final String BASHJAROPTION = "jar";
	
	/** The bashsrc. */
	private final String BASHSRC = "/bin/bash";
	
	/** The bashloption. */
	private final String BASHLOPTION = "-l";
	
	/** The bashcoption. */
	private final String BASHCOPTION = "-c";
	
	/** The yarn home. */
	private String yarnHome;
	
	/** The yarn comandt. */
	private String yarnComandt;
	
	/** The jar file to execute. */
	private String jarFileToExecute;
	
	/** The in folder. */
	private String inFolder;
	
	/** The out folder. */
	private String outFolder;
	
	/** The Execute is true. */
	private Boolean ExecuteIsTrue;
	
	/** The jar option. */
	private String jarOption;
	
	/**
	 * Instantiates a new yarn.
	 */
	public Yarn() {}
	
	/**
	 * Instantiates a new yarn.
	 *
	 * @param jarFilePath the jar file path
	 * @param jarOption the jar option
	 * @param inputFolder the input folder
	 * @param outputFolder the output folder
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws YarnException the yarn exception
	 * @throws InterruptedException the interrupted exception
	 */
	public Yarn(String jarFilePath, String jarOption , String inputFolder, String outputFolder) throws IOException, YarnException, InterruptedException  {
		initControlBooleansFLow();
		executeAlgorit(jarFilePath, jarOption, inputFolder, outputFolder);
		/*Process auxProces = Runtime.getRuntime().exec(new 
				String[]{"/bin/bash","-l", "-c","/home/hadoop/hadoop-2.8.5/bin/yarn jar /home/hadoop/hadoop-2.8.5/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.5.jar wordcount /albertoHome/match_stage.js /albertoHome/outHadoopjava","/home/hadoop/hadoop-2.8.5"}
				) ;
		
		
		
		System.out.println(" devuelve 0 si acaba normalmente. " + auxProces.waitFor());
		//System.out.println(" variables de entorno e. " + System.getenv("HOME"));
		StringBuffer output = new StringBuffer();
		BufferedReader reader = 
                new BufferedReader(new InputStreamReader(auxProces.getInputStream()));
		
		 String line = "";           
	        while ((line = reader.readLine())!= null) {
	            output.append(line + "\n");
	        }
	        System.out.println("-------------------------- log del comando ejecutado --------");
	        System.out.println(output.toString());
	        System.out.println("-------------------------- log del comando ejecutado --------");*/
		//System.out.println(Runtime.getRuntime().exec(new String[]{"bash", "-l", "-c","ls /home/alberto/"}, null, new File("/home/alberto")));
		//Runtime.getRuntime().exec(new String[]{"/bin/bash","-l", "-c","yarn jar /home/hadoop/hadoop-2.8.5/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.8.5.jar wordcount /albertoHome/match_stage.js /albertoHome/outHadoopjava"}, null, new File("/home/alberto"));
	}
	
	/**
	 * Execute algorit.
	 *
	 * @param jarFilePath the jar file path
	 * @param jarOption the jar option
	 * @param inputFolder the input folder
	 * @param outputFolder the output folder
	 * @return the boolean
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public Boolean executeAlgorit(String jarFilePath, String jarOption , String inputFolder, String outputFolder) throws IOException, InterruptedException {
		
		Process auxProces = Runtime.getRuntime().exec(new  String[]{BASHSRC,BASHLOPTION, BASHCOPTION, getYarnHome()+ " " + BASHJAROPTION + " "+  jarFilePath+ " " + jarOption+ " " +inputFolder + " " + outputFolder}) ;
		int outputOfProcess  = auxProces.waitFor();
		
		System.out.println(" devuelve 0 si acaba normalmente. " + auxProces.waitFor());
		//System.out.println(" variables de entorno e. " + System.getenv("HOME"));
		/*StringBuffer output = new StringBuffer();
		BufferedReader reader =  new BufferedReader(new InputStreamReader(auxProces.getInputStream()));
		
		 String line = "";           
	        while ((line = reader.readLine())!= null) {
	            output.append(line + "\n");
	        }
	        System.out.println("-------------------------- log del comando ejecutado --------");
	        System.out.println(output.toString());
	        System.out.println("-------------------------- log del comando ejecutado --------");
		*/
		if(outputOfProcess == 0) {
			
			setExecuteIsTrue(true);
			return getExecuteIsTrue();
		}
		return getExecuteIsTrue() ;
	}

	
	/**
	 * Inits the control booleans F low.
	 */
	public void initControlBooleansFLow() {
		setExecuteIsTrue(false);
	}

	/**
	 * Gets the yarn home.
	 *
	 * @return the yarn home
	 */
	public String getYarnHome() {
		return yarnHome;
	}

	/**
	 * Sets the yarn home.
	 *
	 * @param yarnHome the new yarn home
	 */
	public void setYarnHome(String yarnHome) {
		this.yarnHome = yarnHome;
	}

	/**
	 * Gets the yarn comandt.
	 *
	 * @return the yarn comandt
	 */
	public String getYarnComandt() {
		return yarnComandt;
	}

	/**
	 * Sets the yarn comandt.
	 *
	 * @param yarnComandt the new yarn comandt
	 */
	public void setYarnComandt(String yarnComandt) {
		this.yarnComandt = yarnComandt;
	}

	/**
	 * Gets the jar file to execute.
	 *
	 * @return the jar file to execute
	 */
	public String getJarFileToExecute() {
		return jarFileToExecute;
	}

	/**
	 * Sets the jar file to execute.
	 *
	 * @param jarFileToExecute the new jar file to execute
	 */
	public void setJarFileToExecute(String jarFileToExecute) {
		this.jarFileToExecute = jarFileToExecute;
	}

	/**
	 * Gets the in folder.
	 *
	 * @return the in folder
	 */
	public String getInFolder() {
		return inFolder;
	}

	/**
	 * Sets the in folder.
	 *
	 * @param inFolder the new in folder
	 */
	public void setInFolder(String inFolder) {
		this.inFolder = inFolder;
	}

	/**
	 * Gets the out folder.
	 *
	 * @return the out folder
	 */
	public String getOutFolder() {
		return outFolder;
	}

	/**
	 * Sets the out folder.
	 *
	 * @param outFolder the new out folder
	 */
	public void setOutFolder(String outFolder) {
		this.outFolder = outFolder;
	}

	/**
	 * Gets the bashsrc.
	 *
	 * @return the bashsrc
	 */
	public String getBASHSRC() {
		return BASHSRC;
	}

	/**
	 * Gets the bashloption.
	 *
	 * @return the bashloption
	 */
	public String getBASHLOPTION() {
		return BASHLOPTION;
	}

	/**
	 * Gets the bashcoption.
	 *
	 * @return the bashcoption
	 */
	public String getBASHCOPTION() {
		return BASHCOPTION;
	}

	/**
	 * Gets the execute is true.
	 *
	 * @return the execute is true
	 */
	public Boolean getExecuteIsTrue() {
		return ExecuteIsTrue;
	}

	/**
	 * Sets the execute is true.
	 *
	 * @param executeIsTrue the new execute is true
	 */
	public void setExecuteIsTrue(Boolean executeIsTrue) {
		ExecuteIsTrue = executeIsTrue;
	}

	/**
	 * Gets the bashjaroption.
	 *
	 * @return the bashjaroption
	 */
	public String getBASHJAROPTION() {
		return BASHJAROPTION;
	}

	/**
	 * Gets the jar option.
	 *
	 * @return the jar option
	 */
	public String getJarOption() {
		return jarOption;
	}

	/**
	 * Sets the jar option.
	 *
	 * @param jarOption the new jar option
	 */
	public void setJarOption(String jarOption) {
		this.jarOption = jarOption;
	}
	

	
	
	
}
