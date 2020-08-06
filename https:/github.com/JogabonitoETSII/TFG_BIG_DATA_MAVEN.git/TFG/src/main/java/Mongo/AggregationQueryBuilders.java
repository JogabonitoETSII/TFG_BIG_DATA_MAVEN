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

	private boolean ProjectStaga;
	private boolean MatchStage;
	private boolean GroupStage;
	private boolean UndwinStage;
	
	/** The pipeline. */
	private List<Bson> pipeline ;
	/**
	 * Instantiates a new aggregation query builders.
	 */
	public AggregationQueryBuilders() {
		clearStageBooleans();
		setPipeline(new ArrayList<Bson>());
	}
	
	
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
	 * @param excludeId the exclude id
	 */
	public void makeProjectStage(ArrayList<String> fields , boolean excludeId) {
		Bson projection = null;
		
		if(fields.size() > 0) {	
			setProjectStaga(true);
			if(!excludeId) {
			
				projection = Projections.exclude(fields);
			
			}
			else {
				 projection = Projections.include(fields);
			}
			adddPipelineStage(Aggregates.project(projection));
		}
	}
	
	/**
	 * Make group stage.
	 *
	 * @param fieldName the field name
	 */
	public void makeGroupStage(String fieldName , String acumulatorOperator , int acumulatorNumOperator ) { // crear clase independiente.. se puede pasar ArrayList de acumuladores y la expesion parece ser igual al 
		if(!fieldName.isEmpty() && !fieldName.isBlank()) {
			setGroupStage(true);
			BsonField sum1 = Accumulators.sum(acumulatorOperator.toLowerCase(), acumulatorNumOperator );
			Bson groutSatege = Aggregates.group(fieldName, sum1);
			adddPipelineStage(groutSatege);
		}
		
	}
	
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

	public boolean isProjectStaga() {
		return ProjectStaga;
	}

	public void setProjectStaga(boolean projectStaga) {
		ProjectStaga = projectStaga;
	}

	public boolean isMatchStage() {
		return MatchStage;
	}

	public void setMatchStage(boolean matchStage) {
		MatchStage = matchStage;
	}

	public boolean isGroupStage() {
		return GroupStage;
	}

	public void setGroupStage(boolean groupStage) {
		GroupStage = groupStage;
	}

	public boolean isUndwinStage() {
		return UndwinStage;
	}

	public void setUndwinStage(boolean undwinStage) {
		UndwinStage = undwinStage;
	}
	
}
