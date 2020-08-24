package Pentaho;

import java.io.IOException;

import javax.swing.table.DefaultTableModel;
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

import org.pentaho.reporting.engine.classic.core.parameters.DefaultParameterDefinition;
import org.pentaho.reporting.engine.classic.core.parameters.PlainParameter;
import org.pentaho.reporting.engine.classic.core.style.ElementStyleKeys;
import org.pentaho.reporting.engine.classic.core.style.TextStyleKeys;
import org.pentaho.reporting.engine.classic.extensions.legacy.charts.*;



// TODO: Auto-generated Javadoc
/**
 * The Class PentahoLogic.
 */
public class PentahoLogic {
	
	
 	 final private TableDataFactory DATAFACTORY = new TableDataFactory();
	
 	private MasterReport report;
 	
	private DefaultTableModel tableModel;
	 
	private CategorySetDataCollector collector;

	private DefaultParameterDefinition paramDef;
	 
 	
	private ReportHeader header;
	 
 	
	private ReportFooter footer;
	 
 	
	private FormulaExpression formula;
	 
 
	private ItemBand itemBand;
	 
 	
	private Element label;
	
	private BarChartExpression chartExpression;

	/**
	 * Instantiates a new pentaho logic.
	 *
	 * @throws ReportProcessingException the report processing exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  PentahoLogic() throws ReportProcessingException, IOException {
		
	}
	
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
	       setCollector(makeCategorySetDataCollector("","Cost","","Cost","Product",false));
	      // Chart layout.
	     /* BarChartExpression chartExpression = new BarChartExpression();
	      chartExpression.setShowLegend(true);
	       // chartExpression.setBackgroundColor("blue");
	      chartExpression.setSeriesColor(
	       
	      );*/
	      String[] colorSeries  = {"#abcd37","#0392ce","#f9bc02","#66b033","#cc0099"} ;
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
	    	  
	      
		
		System.out.println("Devuelve true si ha logrado crearlo : " + PdfReportUtil.createPDF(report, "/home/alberto/Escritorio/suerte.pdf"));
		
	}
	
	public Element makeNumberFieldElement(String name, Float setX, Float setY, Float MinimunWidht, Float MinimunHeight, Boolean setBold) {
		  NumberFieldElementFactory numberFactory = new NumberFieldElementFactory();
		  
		  System.out.println( "dentro de la funcion " + "name " + name + " setX " + setX + " setY " + setY + "  minimunWIdth " + MinimunWidht + " MinimunHieght " + MinimunHeight +  "   setBOlt " + setBold  );
	      numberFactory.setFieldname(name);
	      numberFactory.setX(setX);
	      numberFactory.setY(setY);
	      numberFactory.setMinimumWidth(MinimunWidht);
	      numberFactory.setMinimumHeight(MinimunHeight);
	      numberFactory.setBold(setBold);
	      return numberFactory .createElement();
	}
	
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
	
	
	
	public  BarChartExpression makeBarChartExpression ( String[] colorSeries , Boolean showLegend , String backGroundColor ) {
		BarChartExpression auxChartExpression = new BarChartExpression();
		auxChartExpression.setShowLegend(showLegend);
		auxChartExpression.setBackgroundColor(backGroundColor);
		auxChartExpression.setSeriesColor(colorSeries );
		return auxChartExpression;
	}
	
	public CategorySetDataCollector makeCategorySetDataCollector(String name, String columName, String seriesName, String valueColumName,String CateegoryColum,Boolean autoGenerateMissingSeriesNames ) {
		 CategorySetDataCollector auxCollector = new CategorySetDataCollector();
		  auxCollector.setName(name);
		  auxCollector.setSeriesColumn(0, columName);
		  auxCollector.setSeriesName(0, seriesName);
		  auxCollector.setValueColumn(0, valueColumName);
		  auxCollector.setCategoryColumn(CateegoryColum);
		  auxCollector.setAutoGenerateMissingSeriesNames(autoGenerateMissingSeriesNames);
	      
	      return auxCollector;
	}

	public void initElementLabel() {
		setLabel(new Element());
	}
	

	public void addHeaderFooterToReport(ReportHeader header , ReportFooter Footer) {
		getReport().setReportHeader(header);
		getReport().setReportFooter(footer);
	}

	
	public void initHeader() {
		setHeader(new ReportHeader());
	}
	
	
	public void initFooter() {
		setFooter(new ReportFooter());
	}
	
	
	public void setFormulaParameters(String setName , String setFormula){
		
		getFormula().setName(setName);
		getFormula().setFormula(setFormula);
		
	}
	
	
	private void initFormulaExpression() {
		setFormula(new FormulaExpression());
	}
	
	

	
	public void initReport() {
		
		ClassicEngineBoot.getInstance().start();
		setReport(new MasterReport());
		
	}

	
	public void setDataFctoryToReport( ) {
		getReport().setDataFactory(DATAFACTORY);
	}

	
	public void setTableInDataFactory(String tablename, DefaultTableModel tableModel ) {
		DATAFACTORY.addTable(tablename, tableModel);
	}
	

	
	
	public MasterReport getReport() {
		return report;
	}


	public void setReport(MasterReport report) {
		this.report = report;
	}
	
	


	
	public TableDataFactory getDATAFACTORY() {
		return DATAFACTORY;
	}


	
	public DefaultTableModel getTableModel() {
		return tableModel;
	}


	
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}


	public DefaultParameterDefinition getParamDef() {
		return paramDef;
	}

	public void setParamDef(DefaultParameterDefinition paramDef) {
		this.paramDef = paramDef;
	}



	public ReportHeader getHeader() {
		return header;
	}


	
	public void setHeader(ReportHeader header) {
		this.header = header;
	}


	public ReportFooter getFooter() {
		return footer;
	}


	
	public void setFooter(ReportFooter footer) {
		this.footer = footer;
	}


	
	public FormulaExpression getFormula() {
		return formula;
	}


	public void setFormula(FormulaExpression formula) {
		this.formula = formula;
	}
	
	
	public ItemBand getItemBand() {
		return itemBand;
	}
	
	
	public void setItemBand(ItemBand itemBand) {
		this.itemBand = itemBand;
	}

	public Element getLabel() {
		return label;
	}


	
	public void setLabel(Element label) {
		this.label = label;
	}

	public CategorySetDataCollector getCollector() {
		return collector;
	}

	public void setCollector(CategorySetDataCollector collector) {
		this.collector = collector;
	}

	public BarChartExpression getChartExpression() {
		return chartExpression;
	}

	public void setChartExpression(BarChartExpression chartExpression) {
		this.chartExpression = chartExpression;
	}


	
}
