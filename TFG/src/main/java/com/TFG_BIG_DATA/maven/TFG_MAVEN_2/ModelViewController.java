package com.TFG_BIG_DATA.maven.TFG_MAVEN_2;



import java.io.IOException;
import java.util.HashMap;

import Hadoop.HadoopLogic;
import Mongo.MongoLogic;
import Pentaho.PentahoLogic;



// TODO: Auto-generated Javadoc
/**
 * The Class ModelViewController.
 */
public class ModelViewController {
	
	/** The logics. */
	private LogicEnviroment logics;
	
	/** The graphics. */
	private GraphicsEnviroment graphics;
	
	/**
	 * Instantiates a new model view controller.
	 *
	 * @param args the args
	 */
	public ModelViewController(String[] args) {
		setLogics(new LogicEnviroment(args));
		setGraphics(new GraphicsEnviroment());
	}
	
	/**
	 * Gets the logics.
	 *
	 * @return the logics
	 */
	public LogicEnviroment getLogics() {
		return logics;
	}

	/**
	 * Sets the logics.
	 *
	 * @param logics the new logics
	 */
	public void setLogics(LogicEnviroment logics) {
		this.logics = logics;
	}

	/**
	 * Gets the graphics.
	 *
	 * @return the graphics
	 */
	public GraphicsEnviroment getGraphics() {
		return graphics;
	}

	/**
	 * Sets the graphics.
	 *
	 * @param graphics the new graphics
	 */
	public void setGraphics(GraphicsEnviroment graphics) {
		this.graphics = graphics;
	}

	
}
