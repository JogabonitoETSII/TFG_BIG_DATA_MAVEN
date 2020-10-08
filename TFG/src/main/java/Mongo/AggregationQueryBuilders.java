package Mongo;


import com.mongodb.client.model.Facet;
import com.mongodb.client.model.Projections;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;



// TODO: Auto-generated Javadoc
/**
 * The Class AggregationQueryBuilders.
 */
public class AggregationQueryBuilders  {

	
	/** The valueposition. */
	final public Integer VALUEPOSITION = 2;
	
	/** The fieldposition. */
	final public Integer FIELDPOSITION = 1;
	
	/** The filterposition. */
	final public Integer FILTERPOSITION = 0;
	
	/** The match. */
	final public String MATCH = "<match>";
	
	/** The endmatch. */
	final public String ENDMATCH = "</match>";
	
	/** The projection. */
	final public String PROJECTION = "<projection>";
	
	/** The endprojection. */
	final public String ENDPROJECTION = "</projection>";
	
	/** The Project staga. */
	private boolean ProjectStaga;
	
	/** The Match stage. */
	private boolean MatchStage;
	
	/** The Group stage. */
	private boolean GroupStage;
	
	/** The Undwin stage. */
	private boolean UndwinStage;
	
	/** The pipeline. */
	private List<Bson> pipeline ;
	
	/** The macht stage. */
	private MatchFilterObject machtStage;
	/**
	 * Instantiates a new aggregation query builders.
	 */
	public AggregationQueryBuilders() {
		clearStageBooleans();
		setPipeline(new ArrayList<Bson>());
	}
	
	
	/**
	 * Clear stage booleans.
	 */
	public void clearStageBooleans() {
		setProjectStaga(false);
		setMatchStage(false);
		setUndwinStage(false);
		setGroupStage(false);
		
	}
	/**
	 * Clear pipeline.
	 */
	public void clearPipeline() {
		getPipeline().clear();
	}
	
	
	/**
	 * Make project stage.
	 *
	 * @param fields the fields
	 * @param exclude the exclude
	 */
	public void makeProjectStage(ArrayList<String> fields , boolean exclude) {
		Bson projection = null;
		
		if(fields.size() > 0) {	
			setProjectStaga(true);
			if(!exclude) {
				projection = Projections.exclude(fields);
			}
			else {
				 projection = Projections.include(fields);
			}
			adddPipelineStage(Aggregates.project(projection));
		}
	}
	
	/**
	 * Make project stage.
	 *
	 * @param field the field
	 * @param exclude the exclude
	 */
	public void makeProjectStage(String field , boolean exclude) {
		Bson projection = null;
		
		if(!field.isEmpty()) {	
			setProjectStaga(true);
			if(!exclude) {
				projection = Projections.exclude(field);
			}
			else {
				 projection = Projections.include(field);
			}
			adddPipelineStage(Aggregates.project(projection));
		}
	}
	
	/**
	 * Make group stage.
	 *
	 * @param fieldName the field name
	 * @param acumulatorOperator the acumulator operator
	 * @param acumulatorNumOperator the acumulator num operator
	 */
	public void makeGroupStage(String fieldName , String acumulatorOperator , int acumulatorNumOperator ) { // crear clase independiente.. se puede pasar ArrayList de acumuladores y la expesion parece ser igual al 
		if(!fieldName.isEmpty() && !fieldName.isBlank()) {
			setGroupStage(true);
			BsonField sum1 = Accumulators.sum(acumulatorOperator.toLowerCase(), acumulatorNumOperator );
			Bson groutSatege = Aggregates.group(fieldName, sum1);
			adddPipelineStage(groutSatege);
		}
		
	}
	
	/**
	 * Make limit stage.
	 *
	 * @param limit the limit
	 */
	public void makeLimitStage(Integer limit) {
		Bson limitDocument = Aggregates.limit(limit);
		adddPipelineStage(limitDocument);
	}
	/**
	 * Make unwind stage.
	 *
	 * @param field the field
	 */
	public void makeUnwindStage(String field) {
		if(!field.isEmpty() && !field.isBlank()) {
			setUndwinStage(true);
			adddPipelineStage(Aggregates.unwind(field));
		}
	}
	
	/**
	 * Adds the match stage.
	 *
	 * @param matchStage the match stage
	 */
	public void addMatchStage(Bson matchStage) {
		if(matchStage != null) {
			setMatchStage(true);
			adddPipelineStage(Aggregates.match(matchStage));
		}
	}
	
	
	/**
	 * Addd pipeline stage.
	 *
	 * @param AddPipeline the add pipeline
	 */
	public  void adddPipelineStage (Bson AddPipeline){
		getPipeline().add(AddPipeline);
	}

   

	/**
	 * Gets the pipeline.
	 *
	 * @return the pipeline
	 */
	public List<Bson> getPipeline() {
		return pipeline;
	}


	/**
	 * Sets the pipeline.
	 *
	 * @param pipeline the new pipeline
	 */
	public void setPipeline(List<Bson> pipeline) {
		this.pipeline = pipeline;
	}

	/**
	 * Checks if is project staga.
	 *
	 * @return true, if is project staga
	 */
	public boolean isProjectStaga() {
		return ProjectStaga;
	}

	/**
	 * Sets the project staga.
	 *
	 * @param projectStaga the new project staga
	 */
	public void setProjectStaga(boolean projectStaga) {
		ProjectStaga = projectStaga;
	}

	/**
	 * Checks if is match stage.
	 *
	 * @return true, if is match stage
	 */
	public boolean isMatchStage() {
		return MatchStage;
	}

	/**
	 * Sets the match stage.
	 *
	 * @param matchStage the new match stage
	 */
	public void setMatchStage(boolean matchStage) {
		MatchStage = matchStage;
	}

	/**
	 * Checks if is group stage.
	 *
	 * @return true, if is group stage
	 */
	public boolean isGroupStage() {
		return GroupStage;
	}

	/**
	 * Sets the group stage.
	 *
	 * @param groupStage the new group stage
	 */
	public void setGroupStage(boolean groupStage) {
		GroupStage = groupStage;
	}

	/**
	 * Checks if is undwin stage.
	 *
	 * @return true, if is undwin stage
	 */
	public boolean isUndwinStage() {
		return UndwinStage;
	}

	/**
	 * Sets the undwin stage.
	 *
	 * @param undwinStage the new undwin stage
	 */
	public void setUndwinStage(boolean undwinStage) {
		UndwinStage = undwinStage;
	}


	/**
	 * Gets the macht stage.
	 *
	 * @return the macht stage
	 */
	public MatchFilterObject getMachtStage() {
		return machtStage;
	}


	/**
	 * Sets the macht stage.
	 *
	 * @param machtStage the new macht stage
	 */
	public void setMachtStage(MatchFilterObject machtStage) {
		this.machtStage = machtStage;
	}


	/**
	 * Gets the match.
	 *
	 * @return the match
	 */
	public String getMATCH() {
		return MATCH;
	}


	/**
	 * Gets the projection.
	 *
	 * @return the projection
	 */
	public String getPROJECTION() {
		return PROJECTION;
	}


	/**
	 * Gets the endmatch.
	 *
	 * @return the endmatch
	 */
	public String getENDMATCH() {
		return ENDMATCH;
	}


	/**
	 * Gets the endprojection.
	 *
	 * @return the endprojection
	 */
	public String getENDPROJECTION() {
		return ENDPROJECTION;
	}
	
}
