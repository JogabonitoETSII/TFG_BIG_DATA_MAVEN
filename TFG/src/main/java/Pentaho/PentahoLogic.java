package Pentaho;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.pentaho.reporting.engine.classic.core.elementfactory.LabelElementFactory;
import org.pentaho.reporting.engine.classic.core.elementfactory.NumberFieldElementFactory;
import org.pentaho.reporting.engine.classic.core.elementfactory.TextFieldElementFactory;
import org.pentaho.reporting.engine.classic.core.function.FormulaExpression;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.plugin.jfreereport.reportcharts.BarChartExpression;
import org.pentaho.plugin.jfreereport.reportcharts.collectors.CategorySetDataCollector;
import org.pentaho.reporting.engine.classic.core.*;
import org.pentaho.reporting.engine.classic.extensions.legacy.charts.LegacyChartElementModule;
import org.pentaho.reporting.engine.classic.extensions.legacy.charts.LegacyChartType;

import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;

import EnviromentClass.ReportObject;

import org.pentaho.reporting.engine.classic.core.parameters.DefaultParameterDefinition;
import org.pentaho.reporting.engine.classic.core.parameters.PlainParameter;
import org.pentaho.reporting.engine.classic.core.style.ElementStyleKeys;
import org.pentaho.reporting.engine.classic.core.style.TextStyleKeys;
import org.pentaho.reporting.engine.classic.extensions.legacy.charts.*;



// TODO: Auto-generated Javadoc
/**
 * The Class PentahoLogic.
 */
public class PentahoLogic  extends ReportObject{
	public String reportType ;
	private final String OUTPUTEXTENCION = ".pdf";
	private final String TABLE = "TABLE";
	
	/** The colums names. */
	private String [] columsNames;
	
	/** The import delimiter. */
	private String importDelimiter;
	
	/** The series color report. */
	private String [] seriesColorReport;
	
	/** The select data colum. */
	private String selectDataColum;
	
	/** The data ejex. */
	private String dataEjex;
	
	/** The data E je Y. */
	private String dataEJeY;
 	 /** The datafactory. */
 	 final private TableDataFactory DATAFACTORY = new TableDataFactory();
	
 	/** The report. */
	 private MasterReport report;
 	
	/** The table model. */
	private DefaultTableModel tableModel;
	 
	/** The collector. */
	private CategorySetDataCollector collector;

	/** The param def. */
	private DefaultParameterDefinition paramDef;
	 
 	
	/** The header. */
	private ReportHeader header;
	 
 	
	/** The footer. */
	private ReportFooter footer;
	 
 	
	/** The formula. */
	private FormulaExpression formula;
	 
 
	/** The item band. */
	private ItemBand itemBand;
	 
 	
	/** The label. */
	private Element label;
	
	/** The chart expression. */
	private BarChartExpression chartExpression;

	/**
	 *  The wordcountinputcolumsn.
	 *
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */

	
	/**
	 * Instantiates a new pentaho logic.
	 *
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PentahoLogic() throws ReportProcessingException, IOException {
		
	}
	
	
	/**
	 * Creates the report word counts.
	 *
	 * @param filePath the file path
	 * @param fileName the file name
	 * @param delimiter the delimiter
	 * @param columNames the colum names
	 * @param selectDataColum the select data colum
	 * @param DataEjeY the data eje Y
	 * @param dataEjeX the data eje X
	 * @param colorSeries the color series
	 * @return the boolean
	 * @throws FileNotFoundException the file not found exception
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Boolean createReportWordCounts(String filePath , String fileName,String delimiter ,String[] columNames , String selectDataColum, String  DataEjeY,String dataEjeX,String[] colorSeries) throws FileNotFoundException, ReportProcessingException, IOException {
		initReport();
		//importDataToTableReportDelimeterFormat( filePath,fileName, delimiter);
		makeTableReport(importDataToTableReportDelimeterFormat( filePath,fileName, delimiter),columNames,selectDataColum,DataEjeY,dataEjeX,colorSeries);
		 return PdfReportUtil.createPDF(getReport(),filePath+getOutputFileReportName()+OUTPUTEXTENCION);
	}
	
	/**
	 * Import data to table report cound words.
	 *
	 * @param filePath the file path
	 * @param fileName the file name
	 * @param delimiter the delimiter
	 * @return the object[][]
	 * @throws FileNotFoundException the file not found exception
	 */
	public Object[][] importDataToTableReportDelimeterFormat(String filePath , String fileName , String delimiter) throws FileNotFoundException {
		File input = new File(filePath+fileName);
		System.out.println("ruta para el fichero a analizar "  + filePath+fileName);
		Scanner reader = new Scanner(input);
		String[] inputLineText;
		ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> inputRowData = new ArrayList<Object>();
		if(input.exists()) {
			//System.out.println("existe el fichero? ");
			//reader.useDelimiter(delimiter);
			while(reader.hasNextLine()) {
				inputLineText = reader.nextLine().split(delimiter);
				for(int i = 0; i < inputLineText.length ; i++) {
				    if(inputLineText[i].matches("[-+]?\\d*\\.?\\d+")) {
						 //System.out.println("detecta enteros=? " + inputLineText[i]);
						inputRowData.add(Double.parseDouble(inputLineText[i]));
					}
					else {	
						inputRowData.add(inputLineText[i]);
						//System.out.println("solo detecta Strings? " + inputLineText[i]);
					}
				}
				if(inputLineText != null) {
					//System.out.println("entramos al detectar el salto de linea? + " + inputRowData);
					data.add(inputRowData);
					inputRowData = new ArrayList<Object>();
				}
			}
			return transforDataInput(data);
		}
		return null;
	}
	
	/**
	 * Transfor data input.
	 *
	 * @param data the data
	 * @return the object[][]
	 */
	private Object[][] transforDataInput(ArrayList<ArrayList<Object>> data) {
		Object[][] objectMatrix = new Object[data.size()][];
		for(int i = 0 ; i < data.size() ; i++) {
			System.out.println("contenido del arrayList data : " +  data.get(i));
			objectMatrix[i] = data.get(i).toArray();
		}
		return objectMatrix;
	}

	/**
	 * Creates the table report.
	 *
	 * @param data the data
	 * @param columNames the colum names
	 * @param numColums the num colums
	 * @param dataEjeY the data eje Y
	 * @param dataEJeX the data E je X
	 * @param colorSeries the color series
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void makeTableReport(Object[][] data, String[] columNames , String numColums, String  dataEjeY,String dataEJeX,String[] colorSeries ) throws ReportProcessingException, IOException {
		  // Create a TableDataFactory.
	      DATAFACTORY.addTable("default", createTable(data, columNames));
	      // Add the factory to the report.
	      setDataFctoryToReport();
	      //init and set headerReport
	      initHeader();
	      getReport().setReportHeader(getHeader());
	      // se collector of data
	      //  setCollector(makeCategorySetDataCollector("CategorySetCollectorFunction","Cost","","Cost","Product",false));
	      // new String[] {"Line", "Product", "Cost", "Quantity"}
	      // makeCategorySetDataCollector(String name, String numColums, String seriesName, String valueEJeY,String ValueEjeX,Boolean autoGenerateMissingSeriesNames )
	      setCollector(makeCategorySetDataCollector("",numColums,"",dataEjeY,dataEJeX,false));
	      setChartExpression(makeBarChartExpression(colorSeries, false, "white"));
	      getHeader().addElement(makeElement(new LegacyChartType(), "MySuperCoolChartFromApi", getChartExpression(), getCollector(), 200f, 500f));
	     // System.out.println("Devuelve true si ha logrado crearlo : " + PdfReportUtil.createPDF(report, "/home/alberto/Escritorio/suerte.pdf"));

	}
	
	/**
	 * Creates the table.
	 *
	 * @param data the data
	 * @param columNames the colum names
	 * @return the table model
	 */
	private TableModel createTable(Object[][] data, String[] columNames ) {
		
		System.out.println("inserta los datos bien en la tabla?   "  + data);
		System.out.println("el nombre de las columnas + " + columNames);
		return new DefaultTableModel(data, columNames);
	}

	/**
	 * Creates the default report.
	 *
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createDefaultReport() throws ReportProcessingException, IOException {
		// Declaring a report.
		  initReport();
	       
	      // Define a simple TableModel.
	      DefaultTableModel tableModel = new DefaultTableModel (
	        new Object[][]
	        {
	          {"Line One", "Product One",   10, 100},
	          {"Line One", "Product Two",   20, 200},
	          {"Line Two", "Product Three", 30, 300},
	          {"Line Two", "Product Four",  40, 400},
	          {"Line Two", "Product Five",  50, 500},
	        },
	        new String[] {"Line", "Product", "Cost", "Quantity"}
	      );

	      // Create a TableDataFactory.
	      DATAFACTORY.addTable("default", tableModel);
	      // Add the factory to the report.
	      setDataFctoryToReport();
	      // Create a formula expression.
	      initFormulaExpression();
	      	
	      // Configure the formulas properties.
	      //setFormulaParameters("totalCost", "\"=[Cost]*[Quantity]\"");
	      formula.setName("totalCost");
	      formula.setFormula("=[Cost]*[Quantity]");
	      getReport().addExpression(formula);
	      // Creating report header object.
	      initHeader();
	      getReport().setReportHeader(getHeader());

	      // Defining a data collector for the chart.
	     //  setCollector(makeCategorySetDataCollector("CategorySetCollectorFunction","Cost","","Cost","Product",false));
	      // new String[] {"Line", "Product", "Cost", "Quantity"}
	      setCollector(makeCategorySetDataCollector("","Product","","Cost","Product",false));
	      //setCollector(new CategorySetDataCollector());
	      // Chart layout.
	     /* BarChartExpression chartExpression = new BarChartExpression();
	      chartExpression.setShowLegend(true);
	       // chartExpression.setBackgroundColor("blue");
	      chartExpression.setSeriesColor(
	       
	      );*/
	      String[] colorSeries  = {"#abcd37","#0392ce","#f9bc02","#66b033","#cc0099"} ;
	      String[] colorSeries2 = {""};
	      setChartExpression(makeBarChartExpression(colorSeries, false, "white"));

	      
	      
	     /* // Declaring an element for the chart.
	      Element chartElement = new Element();
	      chartElement.setElementType(new LegacyChartType());
	      chartElement.setName("MySuperCoolChartFromApi");
	      chartElement.setAttributeExpression(
	        AttributeNames.Core.NAMESPACE,
	        AttributeNames.Core.VALUE, 
	        chartExpression);
	      chartElement.setAttribute(
	        LegacyChartElementModule.NAMESPACE,
	        LegacyChartElementModule.PRIMARY_DATA_COLLECTOR_FUNCTION_ATTRIBUTE,
	        getCollector());
	      chartElement.getStyle().setStyleProperty(ElementStyleKeys.MIN_HEIGHT, 200F);
	      chartElement.getStyle().setStyleProperty(ElementStyleKeys.MIN_WIDTH, 500F);*/
	      
	      getHeader().addElement(makeElement(new LegacyChartType(), "MySuperCoolChartFromApi", getChartExpression(), getCollector(), 200f, 500f));
	      
	  

	      // Creating a label element factory instance.
	     /* LabelElementFactory factory = new LabelElementFactory();

	      // Configuring the label's text.
	      factory.setText("Product");
	      factory.setX(1f);
	      factory.setY(250f);
	      factory.setMinimumWidth(100f);
	      factory.setMinimumHeight(20f);
	      factory.setBold(true);

	      // Instantiate the label element.
	      Element label1 = factory.createElement();*/
	      getHeader().addElement(  makeLabelElement("Product", 1f, 250f, 100f, 20f, true));

	      // Configuring the label's text.
	     getHeader().addElement( makeLabelElement("total Cost", 101f, 250f, 100f, 20f, true));

	      // Getting the item band to host the elements.
	      ItemBand itemBand = getReport().getItemBand();

	      // Adding a text field for the product name, added to the item band.
	      /*TextFieldElementFactory textFactory = new TextFieldElementFactory(); 
	      textFactory.setFieldname("Product");
	      textFactory.setX(1f);
	      textFactory.setY(1f); 
	      textFactory.setMinimumWidth(100f);
	      textFactory.setMinimumHeight(20f);
	      Element nameField = textFactory.createElement(); */
	      itemBand.addElement(makeTextFieldElement("Product", 1f, 1f, 100f, 20f, false));
	      // Adding a number filed with the total cost of the products.
	     /* NumberFieldElementFactory numberFactory = new NumberFieldElementFactory();
	      numberFactory.setFieldname("totalCost");
	      numberFactory.setX(101f);
	      numberFactory.setY(1f);
	      numberFactory.setMinimumWidth(100f);
	      numberFactory.setMinimumHeight(20f);
	      Element totalCost = numberFactory.createElement();
	      
	     itemBand.addElement(totalCost);*/
	      
	      itemBand.addElement(makeNumberFieldElement("totalCost", 101f, 1f, 100f, 20f, false));
	      
	    //  System.out.println("make number elemten  - ----------------- 11111111111111111111111 " +  totalCost.getName());
	      
	     // System.out.println("make number elemten  - -----------------"  + makeNumberFieldElement("total -----Cost", 101f, 1f, 100f, 20f, false).getName() );
	    	  
	      
		
		System.out.println("Devuelve true si ha logrado crearlo : " + PdfReportUtil.createPDF(report, "/home/alberto/Escritorio/suerte2.pdf"));
		
	}
	
	/**
	 * Make number field element.
	 *
	 * @param name the name
	 * @param setX the set X
	 * @param setY the set Y
	 * @param MinimunWidht the minimun widht
	 * @param MinimunHeight the minimun height
	 * @param setBold the set bold
	 * @return the element
	 */
	public Element makeNumberFieldElement(String name, Float setX, Float setY, Float MinimunWidht, Float MinimunHeight, Boolean setBold) {
		  NumberFieldElementFactory numberFactory = new NumberFieldElementFactory();
		  
		//  System.out.println( "dentro de la funcion " + "name " + name + " setX " + setX + " setY " + setY + "  minimunWIdth " + MinimunWidht + " MinimunHieght " + MinimunHeight +  "   setBOlt " + setBold  );
	      numberFactory.setFieldname(name);
	      numberFactory.setX(setX);
	      numberFactory.setY(setY);
	      numberFactory.setMinimumWidth(MinimunWidht);
	      numberFactory.setMinimumHeight(MinimunHeight);
	      numberFactory.setBold(setBold);
	      return numberFactory .createElement();
	}
	
	/**
	 * Make element.
	 *
	 * @param charType the char type
	 * @param name the name
	 * @param chartExpression the chart expression
	 * @param collector the collector
	 * @param height the height
	 * @param width the width
	 * @return the element
	 */
	public Element makeElement( LegacyChartType charType, String name,BarChartExpression  chartExpression , CategorySetDataCollector  collector, Float height, Float width ) {
		
		 Element auxChartElement = new Element();
		 auxChartElement.setElementType(new LegacyChartType());
		 auxChartElement.setName("MySuperCoolChartFromApi");
		 auxChartElement.setAttributeExpression(
	        AttributeNames.Core.NAMESPACE,
	        AttributeNames.Core.VALUE, 
	        chartExpression);
		 auxChartElement.setAttribute(
	        LegacyChartElementModule.NAMESPACE,
	        LegacyChartElementModule.PRIMARY_DATA_COLLECTOR_FUNCTION_ATTRIBUTE,
	        collector);
		 auxChartElement.getStyle().setStyleProperty(ElementStyleKeys.MIN_HEIGHT, height);
		 auxChartElement.getStyle().setStyleProperty(ElementStyleKeys.MIN_WIDTH, width);
		return auxChartElement;
	}
	
	/**
	 * Make label element.
	 *
	 * @param Text the text
	 * @param setX the set X
	 * @param setY the set Y
	 * @param MinimunWidht the minimun widht
	 * @param MinimunHeight the minimun height
	 * @param setBold the set bold
	 * @return the element
	 */
	public Element makeLabelElement(String Text, Float setX, Float setY, Float MinimunWidht, Float MinimunHeight, Boolean setBold) {
		
		LabelElementFactory auxELementFactory =  new LabelElementFactory();
		 // Configuring the label's text.
		auxELementFactory .setText(Text);
		auxELementFactory .setX(setX);
		auxELementFactory .setY(setY);
		auxELementFactory .setMinimumWidth(MinimunWidht);
		auxELementFactory .setMinimumHeight(MinimunHeight);
		auxELementFactory .setBold(setBold);
	   // Instantiate the label element.
		return auxELementFactory.createElement();
	}
	
	/**
	 * Make text field element.
	 *
	 * @param name the name
	 * @param setX the set X
	 * @param setY the set Y
	 * @param MinimunWidht the minimun widht
	 * @param MinimunHeight the minimun height
	 * @param setBold the set bold
	 * @return the element
	 */
	public Element makeTextFieldElement(String name, Float setX, Float setY, Float MinimunWidht, Float MinimunHeight, Boolean setBold) {
		
		TextFieldElementFactory auxTextFactory = new TextFieldElementFactory();
		// Configuring the label's text.
		auxTextFactory.setFieldname(name);
		auxTextFactory .setX(setX);
		auxTextFactory .setY(setY);
		auxTextFactory .setMinimumWidth(MinimunWidht);
		auxTextFactory .setMinimumHeight(MinimunHeight);
		auxTextFactory .setBold(setBold);
	   // Instantiate the label element.
		return auxTextFactory.createElement();
		
	}
	
	
	
	/**
	 * Make bar chart expression.
	 *
	 * @param colorSeries the color series
	 * @param showLegend the show legend
	 * @param backGroundColor the back ground color
	 * @return the bar chart expression
	 */
	public  BarChartExpression makeBarChartExpression ( String[] colorSeries , Boolean showLegend , String backGroundColor ) {
		BarChartExpression auxChartExpression = new BarChartExpression();
		auxChartExpression.setShowLegend(showLegend);
		auxChartExpression.setBackgroundColor(backGroundColor);
		auxChartExpression.setSeriesColor(colorSeries);
		return auxChartExpression;
	}
	
	/**
	 * Make category set data collector.
	 *
	 * @param name the name
	 * @param numColums the num colums
	 * @param seriesName the series name
	 * @param valueEJeY the value E je Y
	 * @param ValueEjeX the value eje X
	 * @param autoGenerateMissingSeriesNames the auto generate missing series names
	 * @return the category set data collector
	 * 
	 * "CategorySetCollectorFunction","Cost","","Cost","Product",false)
	 */
	public CategorySetDataCollector makeCategorySetDataCollector(String name, String numColums, String seriesName, String valueEJeY,String ValueEjeX,Boolean autoGenerateMissingSeriesNames ) {
		 CategorySetDataCollector auxCollector = new CategorySetDataCollector();
		  auxCollector.setName(name);
		  auxCollector.setSeriesColumn(0, numColums);
		  auxCollector.setSeriesName(0, seriesName);
		  auxCollector.setValueColumn(0, valueEJeY);
		  auxCollector.setCategoryColumn(ValueEjeX);
		  auxCollector.setAutoGenerateMissingSeriesNames(autoGenerateMissingSeriesNames);
	      
	      return auxCollector;
	}

	/**
	 * Inits the element label.
	 */
	public void initElementLabel() {
		setLabel(new Element());
	}
	

	/**
	 * Adds the header footer to report.
	 *
	 * @param header the header
	 * @param Footer the footer
	 */
	public void addHeaderFooterToReport(ReportHeader header , ReportFooter Footer) {
		getReport().setReportHeader(header);
		getReport().setReportFooter(footer);
	}

	
	/**
	 * Inits the header.
	 */
	public void initHeader() {
		setHeader(new ReportHeader());
	}
	
	
	/**
	 * Inits the footer.
	 */
	public void initFooter() {
		setFooter(new ReportFooter());
	}
	
	
	/**
	 * Sets the formula parameters.
	 *
	 * @param setName the set name
	 * @param setFormula the set formula
	 */
	public void setFormulaParameters(String setName , String setFormula){
		
		getFormula().setName(setName);
		getFormula().setFormula(setFormula);
		
	}
	
	
	/**
	 * Inits the formula expression.
	 */
	private void initFormulaExpression() {
		setFormula(new FormulaExpression());
	}
	
	

	
	/**
	 * Inits the report.
	 */
	public void initReport() {
		
		ClassicEngineBoot.getInstance().start();
		setReport(new MasterReport());
		
	}

	
	/**
	 * Sets the data fctory to report.
	 */
	public void setDataFctoryToReport( ) {
		getReport().setDataFactory(DATAFACTORY);
	}

	
	/**
	 * Sets the table in data factory.
	 *
	 * @param tablename the tablename
	 * @param tableModel the table model
	 */
	public void setTableInDataFactory(String tablename, DefaultTableModel tableModel ) {
		DATAFACTORY.addTable(tablename, tableModel);
	}
	

	
	
	/**
	 * Gets the report.
	 *
	 * @return the report
	 */
	public MasterReport getReport() {
		return report;
	}


	/**
	 * Sets the report.
	 *
	 * @param report the new report
	 */
	public void setReport(MasterReport report) {
		this.report = report;
	}
	
	


	
	/**
	 * Gets the datafactory.
	 *
	 * @return the datafactory
	 */
	public TableDataFactory getDATAFACTORY() {
		return DATAFACTORY;
	}


	
	/**
	 * Gets the table model.
	 *
	 * @return the table model
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}


	
	/**
	 * Sets the table model.
	 *
	 * @param tableModel the new table model
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}


	/**
	 * Gets the param def.
	 *
	 * @return the param def
	 */
	public DefaultParameterDefinition getParamDef() {
		return paramDef;
	}

	/**
	 * Sets the param def.
	 *
	 * @param paramDef the new param def
	 */
	public void setParamDef(DefaultParameterDefinition paramDef) {
		this.paramDef = paramDef;
	}



	/**
	 * Gets the header.
	 *
	 * @return the header
	 */
	public ReportHeader getHeader() {
		return header;
	}


	
	/**
	 * Sets the header.
	 *
	 * @param header the new header
	 */
	public void setHeader(ReportHeader header) {
		this.header = header;
	}


	/**
	 * Gets the footer.
	 *
	 * @return the footer
	 */
	public ReportFooter getFooter() {
		return footer;
	}


	
	/**
	 * Sets the footer.
	 *
	 * @param footer the new footer
	 */
	public void setFooter(ReportFooter footer) {
		this.footer = footer;
	}


	
	/**
	 * Gets the formula.
	 *
	 * @return the formula
	 */
	public FormulaExpression getFormula() {
		return formula;
	}


	/**
	 * Sets the formula.
	 *
	 * @param formula the new formula
	 */
	public void setFormula(FormulaExpression formula) {
		this.formula = formula;
	}
	
	
	/**
	 * Gets the item band.
	 *
	 * @return the item band
	 */
	public ItemBand getItemBand() {
		return itemBand;
	}
	
	
	/**
	 * Sets the item band.
	 *
	 * @param itemBand the new item band
	 */
	public void setItemBand(ItemBand itemBand) {
		this.itemBand = itemBand;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public Element getLabel() {
		return label;
	}


	
	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(Element label) {
		this.label = label;
	}

	/**
	 * Gets the collector.
	 *
	 * @return the collector
	 */
	public CategorySetDataCollector getCollector() {
		return collector;
	}

	/**
	 * Sets the collector.
	 *
	 * @param collector the new collector
	 */
	public void setCollector(CategorySetDataCollector collector) {
		this.collector = collector;
	}

	/**
	 * Gets the chart expression.
	 *
	 * @return the chart expression
	 */
	public BarChartExpression getChartExpression() {
		return chartExpression;
	}

	/**
	 * Sets the chart expression.
	 *
	 * @param chartExpression the new chart expression
	 */
	public void setChartExpression(BarChartExpression chartExpression) {
		this.chartExpression = chartExpression;
	}

	/**
	 * Builds the report.
	 *
	 * @param filePath the file path
	 * @param fileName the file name
	 */


	@Override
	public Boolean buildReport(String filePath, String fileName) {
		if (getReportType().toUpperCase().contentEquals(TABLE)) {
			try {
				return createReportWordCounts(filePath, fileName, getImportDelimiter(), getColumsNames(), getSelectDataColum(), getDataEJeY(), getDataEjex(), getSeriesColorReport());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReportProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}


	/**
	 * Gets the colums names.
	 *
	 * @return the colums names
	 */
	public String[] getColumsNames() {
		return columsNames;
	}


	/**
	 * Sets the colums names.
	 *
	 * @param columsNames the new colums names
	 */
	public void setColumsNames(String[] columsNames) {
		this.columsNames = columsNames;
	}


	/**
	 * Gets the import delimiter.
	 *
	 * @return the import delimiter
	 */
	public String getImportDelimiter() {
		return importDelimiter;
	}


	/**
	 * Sets the import delimiter.
	 *
	 * @param importDelimiter the new import delimiter
	 */
	public void setImportDelimiter(String importDelimiter) {
		this.importDelimiter = importDelimiter;
	}


	/**
	 * Gets the series color report.
	 *
	 * @return the series color report
	 */
	public String[] getSeriesColorReport() {
		return seriesColorReport;
	}


	/**
	 * Sets the series color report.
	 *
	 * @param seriesColorReport the new series color report
	 */
	public void setSeriesColorReport(String[] seriesColorReport) {
		this.seriesColorReport = seriesColorReport;
	}


	/**
	 * Gets the select data colum.
	 *
	 * @return the select data colum
	 */
	public String getSelectDataColum() {
		return selectDataColum;
	}


	/**
	 * Sets the select data colum.
	 *
	 * @param selectDataColum the new select data colum
	 */
	public void setSelectDataColum(String selectDataColum) {
		this.selectDataColum = selectDataColum;
	}


	/**
	 * Gets the data ejex.
	 *
	 * @return the data ejex
	 */
	public String getDataEjex() {
		return dataEjex;
	}


	/**
	 * Sets the data ejex.
	 *
	 * @param dataEjex the new data ejex
	 */
	public void setDataEjex(String dataEjex) {
		this.dataEjex = dataEjex;
	}


	/**
	 * Gets the data E je Y.
	 *
	 * @return the data E je Y
	 */
	public String getDataEJeY() {
		return dataEJeY;
	}


	/**
	 * Sets the data E je Y.
	 *
	 * @param dataEJeY the new data E je Y
	 */
	public void setDataEJeY(String dataEJeY) {
		this.dataEJeY = dataEJeY;
	}

	public String getReportType() {
		return reportType;
	}


	/**
	 * Sets the report type.
	 *
	 * @param reportType the new report type
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
}
