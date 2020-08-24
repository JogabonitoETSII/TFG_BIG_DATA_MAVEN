package Mongo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



// TODO: Auto-generated Javadoc
/**
 * The Class PojosClass.
 */
public class PojosClass extends ClassLoader{
	
	/** The classdefinition. */
	private final String CLASSDEFINITION = "public class ";  	
	
	/** The tabular string. */
	private final String tabularString = "\t";
	
	/** The double tabular string. */
	private final String doubleTabularString = "\t\t";
	/** The return string. */
	private final String returnString ="return";
	/** The set string. */
	private final String setString = "set";
	
	/** The get string. */
	private final String getString =" get";
	/** The obrace. */
	private final String OBRACE = "{";
	
	/** The cbrace. */
	private final String CBRACE = "}";
	
	/** The oparenthesis. */
	private final String OPARENTHESIS = "(";
	
	/** The cparenthesis. */
	private final String CPARENTHESIS = ")";
	
	/** The publcistring. */
	private final String PUBLCISTRING = "public ";
	
	/** The privatestring. */
	private final String PRIVATESTRING = "private ";
	
	/** The jumpline. */
	private final String JUMPLINE = "\n";
	
	/** The semicolon. */
	private final String SEMICOLON = ";";
	
	/** The Projectpackage. */
	private final String Projectpackage = "package Mongo";
	
	/** The extenstion. */
	private final String EXTENSTION = ".java";
	
	/** The classextention. */
	private final String CLASSEXTENTION = ".class";
	/** The out put. */
	

	private final String voidString = "void";
	
	/** The this point string. */
	private final String thisPointString ="this.";
	
	/** The equal string. */
	private final String equalString = "=";
	
	/** The final string. */
	private final String finalString = "final";
	
	/** The space string. */
	private final String spaceString =" ";
	
	/** The out put. */
	private FileWriter outPut;
	
	/** The class compile. */
	private boolean classCompile;
	
	/** The class created. */
	private boolean classCreated;
	
	
	/** The class load. */
	private boolean classLoad;
	
	/** The path pojo class storage. */
	private String pathPojoClassStorage = "/home/alberto/eclipse-workspace/TFG/src/main/java/Mongo/";
	
	/** The lass path pojo storage. */
	private String classPathPojoStorage =  "/home/alberto/git/TFG_BIG_DATA/TFG_BIG_DATA_GIT/bin/Mongo";
	/**
	 * Instantiates a new pojos class.
	 */
	public PojosClass() {
		setClassCreated(false);
		setClassLoad(false);
		setClassCompile(false);
	}
	
	
	
	/**
	 * Creates the class text run time.
	 *
	 * @param type         the type
	 * @param atributeName the atribute name
	 * @param className    the class name
	 * @throws InterruptedException the interrupted exception
	 */
	public void createClassTextRunTime(ArrayList<String> type , ArrayList<String> atributeName , String className ) throws InterruptedException {
		
		try {
			setClassCreated(true);
			setOutPut( new FileWriter(getPathPojoClassStorage() + className + getEXTENSTION()) );
			getOutPut().write(getProjectpackage() + getSEMICOLON() + getJUMPLINE());
			getOutPut().write(getCLASSDEFINITION() + getSpaceString()  + className + getSpaceString()  +  getOBRACE() + getJUMPLINE() + getTabularString());
			for(int i = 0 ; i < atributeName.size() ; i++) {
				getOutPut().write(getPRIVATESTRING() + type.get(i) + getSpaceString()  + atributeName.get(i) + getSEMICOLON() + getJUMPLINE() + getTabularString());
			}
			getOutPut().write(getJUMPLINE() + getTabularString() + getPUBLCISTRING() + className + getOPARENTHESIS() + getCPARENTHESIS() + getOBRACE() + getCBRACE() + getJUMPLINE()  );
			
			for(int i = 0 ; i < atributeName.size() ; i++) {
				getOutPut().write( getTabularString()+ getPUBLCISTRING() + getVoidString() + getSpaceString() + getSetString()  + atributeName.get(i) + getOPARENTHESIS()  
								   + getFinalString() + getSpaceString() +type.get(i) + getSpaceString() + atributeName.get(i) + getCPARENTHESIS() + getOBRACE() + getJUMPLINE() 
				                   + getDoubleTabularString() + getThisPointString() + atributeName.get(i) + getSpaceString() + getEqualString()+ getSpaceString() + atributeName.get(i) + getSEMICOLON()
				                   + getJUMPLINE()+getTabularString() +getCBRACE() + getJUMPLINE()
						);
				
				getOutPut().write(getTabularString()  + getPUBLCISTRING() +getSpaceString() +type.get(i) + getSpaceString() + getGetString()  + atributeName.get(i) + getOPARENTHESIS()  
									+ getCPARENTHESIS() + getOBRACE() + getJUMPLINE() 
									+ getDoubleTabularString() + getReturnString()+ getSpaceString() +  getThisPointString()+ atributeName.get(i) + getSEMICOLON() + getJUMPLINE()
									+ getTabularString() + getCBRACE() + getJUMPLINE()
		);
			}
			
			
			getOutPut().write( getJUMPLINE()+getCBRACE());
			getOutPut().close();
			setExecutablePermisions(className);
			compileClassInRunTime(className);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			setClassCreated(false);
			
			System.out.println("class created " + isClassLoad());			
			
			e.printStackTrace();
		}
		
	}	

	/**
	 * Compile class in run time.
	 *
	 * @param className the class name
	 * @throws InterruptedException the interrupted exception
	 */
	public void compileClassInRunTime(String className) throws InterruptedException  {
		
		try {
			Process executeCompileJavaCode = Runtime.getRuntime().exec("javac " + getPathPojoClassStorage()+className+getEXTENSTION());
			System.out.println( "EXECUTION PROCESS" );
			moveClassFileToClassPathJava(getPathPojoClassStorage()+className+getEXTENSTION());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Move class file to class path java.
	 *
	 * @param pathOfJavaClass the path of java class
	 */
	public void moveClassFileToClassPathJava(String  pathOfJavaClass)   {
		
		try {
			
			Runtime.getRuntime().exec("mv " + pathOfJavaClass + " " + getClassPathPojoStorage() );
			
			//System.out.println(  "mv " + pathOfJavaClass + " " + getClassPathPojoStorage() );
			//System.out.println("Please press Enter to terminate...");
			//System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	/**
	 * Load class in run time.
	 *
	 * @param binClass the bin class
	 */
	public void loadClassInRunTime(String binClass) {
		
	    try {
	    	setClassLoad(true);
	    	ClassLoader classLoader;
			Class<?> myLoadClass ;
	    	classLoader = this.getClass().getClassLoader();
	        myLoadClass = classLoader.loadClass(binClass);
	        System.out.println("aClass.getName() = " + myLoadClass.getName());
	       
	    } catch (ClassNotFoundException e) {
	    	setClassLoad(false);
	    	
	    	System.out.println("class created " + isClassLoad());	
	        e.printStackTrace();
	    }
		
	}
	
	
	/**
	 * Sets the executable permisions.
	 *
	 * @param FileName the new executable permisions
	 */
	public void setExecutablePermisions(String FileName) {
		
		File file = new File(getPathPojoClassStorage()+FileName+getEXTENSTION());
        
        if(file.exists())
        {
            //Setting read permission for owner only
            file.setExecutable(true);
            
     
        }
       
    }
	
	
	
	/**
	 * Gets the out put.
	 *
	 * @return the out put
	 */
	public FileWriter getOutPut() {
		return outPut;
	}



	/**
	 * Sets the out put.
	 *
	 * @param outPut the new out put
	 */
	public void setOutPut(FileWriter outPut) {
		this.outPut = outPut;
	}



	/**
	 * Checks if is class created.
	 *
	 * @return true, if is class created
	 */
	public boolean isClassCreated() {
		return classCreated;
	}



	/**
	 * Sets the class created.
	 *
	 * @param classCreated the new class created
	 */
	public void setClassCreated(boolean classCreated) {
		this.classCreated = classCreated;
	}



	/**
	 * Gets the path pojo class storage.
	 *
	 * @return the path pojo class storage
	 */
	public String getPathPojoClassStorage() {
		return pathPojoClassStorage;
	}



	/**
	 * Sets the path pojo class storage.
	 *
	 * @param pathPojoClassStorage the new path pojo class storage
	 */
	public void setPathPojoClassStorage(String pathPojoClassStorage) {
		this.pathPojoClassStorage = pathPojoClassStorage;
	}



	/**
	 * Gets the classdefinition.
	 *
	 * @return the classdefinition
	 */
	public String getCLASSDEFINITION() {
		return CLASSDEFINITION;
	}



	/**
	 * Gets the obrace.
	 *
	 * @return the obrace
	 */
	public String getOBRACE() {
		return OBRACE;
	}



	/**
	 * Gets the cbrace.
	 *
	 * @return the cbrace
	 */
	public String getCBRACE() {
		return CBRACE;
	}



	/**
	 * Gets the oparenthesis.
	 *
	 * @return the oparenthesis
	 */
	public String getOPARENTHESIS() {
		return OPARENTHESIS;
	}



	/**
	 * Gets the cparenthesis.
	 *
	 * @return the cparenthesis
	 */
	public String getCPARENTHESIS() {
		return CPARENTHESIS;
	}



	/**
	 * Gets the publcistring.
	 *
	 * @return the publcistring
	 */
	public String getPUBLCISTRING() {
		return PUBLCISTRING;
	}



	/**
	 * Gets the privatestring.
	 *
	 * @return the privatestring
	 */
	public String getPRIVATESTRING() {
		return PRIVATESTRING;
	}



	/**
	 * Gets the jumpline.
	 *
	 * @return the jumpline
	 */
	public String getJUMPLINE() {
		return JUMPLINE;
	}



	/**
	 * Gets the semicolon.
	 *
	 * @return the semicolon
	 */
	public String getSEMICOLON() {
		return SEMICOLON;
	}



	/**
	 * Gets the extenstion.
	 *
	 * @return the extenstion
	 */
	public String getEXTENSTION() {
		return EXTENSTION;
	}



	/**
	 * Checks if is class load.
	 *
	 * @return true, if is class load
	 */
	public boolean isClassLoad() {
		return classLoad;
	}



	/**
	 * Sets the class load.
	 *
	 * @param classLoad the new class load
	 */
	public void setClassLoad(boolean classLoad) {
		this.classLoad = classLoad;
	}



	/**
	 * Gets the projectpackage.
	 *
	 * @return the projectpackage
	 */
	public String getProjectpackage() {
		return Projectpackage;
	}



	/**
	 * Checks if is class compile.
	 *
	 * @return true, if is class compile
	 */
	public boolean isClassCompile() {
		return classCompile;
	}



	/**
	 * Sets the class compile.
	 *
	 * @param classCompile the new class compile
	 */
	public void setClassCompile(boolean classCompile) {
		this.classCompile = classCompile;
	}



	/**
	 * Gets the lass path pojo storage.
	 *
	 * @return the lass path pojo storage
	 */
	public String getClassPathPojoStorage() {
		return classPathPojoStorage;
	}



	/**
	 * Sets the lass path pojo storage.
	 *
	 * @param classPathPojoStorage the new class path pojo storage
	 */
	public void setClassPathPojoStorage(String classPathPojoStorage) {
		this.classPathPojoStorage = classPathPojoStorage;
	}



	/**
	 * Gets the classextention.
	 *
	 * @return the classextention
	 */
	public String getCLASSEXTENTION() {
		return CLASSEXTENTION;
	}



	/**
	 * Gets the void string.
	 *
	 * @return the void string
	 */
	public String getVoidString() {
		return voidString;
	}



	/**
	 * Gets the this point string.
	 *
	 * @return the this point string
	 */
	public String getThisPointString() {
		return thisPointString;
	}



	/**
	 * Gets the equal string.
	 *
	 * @return the equal string
	 */
	public String getEqualString() {
		return equalString;
	}



	/**
	 * Gets the final string.
	 *
	 * @return the final string
	 */
	public String getFinalString() {
		return finalString;
	}



	/**
	 * Gets the space string.
	 *
	 * @return the space string
	 */
	public String getSpaceString() {
		return spaceString;
	}



	/**
	 * Gets the sets the string.
	 *
	 * @return the sets the string
	 */
	public String getSetString() {
		return setString;
	}



	/**
	 * Gets the gets the string.
	 *
	 * @return the gets the string
	 */
	public String getGetString() {
		return getString;
	}



	/**
	 * Gets the return string.
	 *
	 * @return the return string
	 */
	public String getReturnString() {
		return returnString;
	}



	/**
	 * Gets the tabular string.
	 *
	 * @return the tabular string
	 */
	public String getTabularString() {
		return tabularString;
	}



	/**
	 * Gets the double tabular string.
	 *
	 * @return the double tabular string
	 */
	public String getDoubleTabularString() {
		return doubleTabularString;
	}

	
}
