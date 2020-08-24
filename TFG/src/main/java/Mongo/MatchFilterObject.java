package Mongo;
import java.util.ArrayList;

import org.bson.conversions.Bson;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;


// TODO: Auto-generated Javadoc
/**
 * The Class MatchFilterObject.
 *
 * @author alberto
 */
public class MatchFilterObject {

	/** The filterseq. */
	final String FILTERSEQ ="EQ";
	
	/** The filtersin. */
	final String FILTERSIN ="IN";
	
	/** The filtersand. */
	final String FILTERSAND="AND";
	
	/** The filteror. */
	final String FILTEROR = "OR";
	
	/** The filterlte. */
	final String FILTERLTE = "LTE";
	
	/** The filterlt. */
	final String FILTERLT ="LT";
	
	/** The filtergt. */
	final String FILTERGT ="GT";
	
	/** The filtergte. */
	final String FILTERGTE = "GTE";
	
	/** The filterall. */
	final String FILTERALL = "ALL";
	
	/** The filternot. */
	final String FILTERNOT ="NOT";
	
	/** The filterexists. */
	final String FILTEREXISTS ="EXISTS";
	
	/** The F il TERREGEX. */
	final String FIlTERREGEX = "REGEX";
	
	/** The filtersne. */
	final String FILTERSNE = "NE";
	
	/** The equeals filter. */
	private boolean equealsFilter ;
	
	/** The in filter. */
	private boolean inFilter ;
	
	/** The and filter. */
	private boolean andFilter ;
	
	/** The or F ilter. */
	private boolean orFIlter ;
	
	/** The lte F ilter. */
	private boolean lteFIlter ;
	
	/** The lt filter. */
	private boolean ltFilter ;
	
	/** The gt filter. */
	private boolean gtFilter ;
	
	/** The gte filter. */
	private boolean gteFilter ;
	
	/** The all filter. */
	private boolean allFilter ;
	
	/** The not filter. */
	private boolean notFilter ;
	
	/** The exists filter. */
	private boolean existsFilter ;
	
	/** The regex filter. */
	private boolean regexFilter ;
	
	/** The not eqcual filter. */
	private boolean notEqcualFilter;
	
	/** The field value filter. */
	private ArrayList <FieldValueFilter> fieldValueFilter;
	
	/** The aux make filter. */
	private ArrayList<Bson> auxMakeFilter = new ArrayList<Bson>();
	
	/** The aux make and or not filter. */
	private ArrayList<Bson> auxMakeAndOrNotFilter = new ArrayList<Bson>();
	
	 
	
	/**
	 * Instantiates a new match filter object.
	 */
	public MatchFilterObject() {
		resetBooleanFilters();
		setFieldValueFilters(new ArrayList<FieldValueFilter>());
	}
	 																											
	/**
	 * Instantiates a new match filter object.
	 *
	 * @param fieldName the field name
	 * @param fieldValue the field value
	 * @param filter the filter
	 */
	public MatchFilterObject(ArrayList<ArrayList<String>> fieldName  ,ArrayList<ArrayList<Object>> fieldValue , ArrayList<String> filter) {
		resetBooleanFilters();
		setFieldValueFilters(new ArrayList<FieldValueFilter>());
			insertFieldValuePairs(fieldName,fieldValue,filter);
	}
	
	/**
	 * Reset boolean filters.
	 */
	public void resetBooleanFilters() {
		setEquealsFilter(false);
		setInFilter(false);
		setAndFilter(false);
		setOrFIlter(false);
		setLteFIlter(false);
		setLtFilter(false);
		setGteFilter(false);
		setGtFilter(false);
		setAllFilter(false);
		setNotFilter(false);
		setExistsFilter(false);
		setRegexFilter(false);
		setNotEqcualFilter(false);
	}
	
	/**
	 * Insert field value pairs.
	 *
	 * @param fieldName the field name
	 * @param fieldValue the field value
	 * @param filter the filter
	 */
	private void insertFieldValuePairs(ArrayList<ArrayList<String>> fieldName, ArrayList<ArrayList<Object>> fieldValue, ArrayList<String> filter) {
		FieldValueFilter auxInsertPairs;
		for(int i =  filter.size() -1 ;  i >= 0 ; i--) { //se recorre el bucle de forma inversa para leer en orden correcto los operadores de mongo.
			auxInsertPairs = new FieldValueFilter(fieldName.get(i),fieldValue.get(i),filter.get(i)); 
			getFieldValueFilters().add(auxInsertPairs);
		}
	}

	/**
	 * Match stage builder.
	 *
	 * @return the bson
	 */
	public Bson matchStageBuilder() {
		Bson auxReturnedQuery = null; 
		
		for(int i = 0; i < getFieldValueFilters().size(); i++) {
			auxReturnedQuery = GetFieldValuesToMakeBsonFilters(getFieldValueFilters().get(i));
		}
		return auxReturnedQuery ;
	}
	
	/**
	 * Gets the field values to make bson filters.
	 *
	 * @param getfieldValueFilters the getfield value filters
	 * @return the bson
	 */
	private Bson GetFieldValuesToMakeBsonFilters(FieldValueFilter getfieldValueFilters) {
		// TODO Auto-generated method stub
		Bson auxReturnedQuery = null;
		for(int j = 0; j < getfieldValueFilters.getFieldName().size(); j++) {
			//System.out.println("---------------------Error:  " +  getfieldValueFilters.getFieldName() );
			auxReturnedQuery =	makeBsonFilters(getfieldValueFilters.getField(j),getfieldValueFilters.getValue(j), getfieldValueFilters.getFilter());
		}
		return auxReturnedQuery;
	}

	/**
	 * Make bson filters.
	 *
	 * @param field the field
	 * @param value the value
	 * @param filter the filter
	 * @return the bson
	 */
	public Bson makeBsonFilters(String  field, Object value , String filter ) {
		
		String auxFilter = filter.toUpperCase();
		// crear variable independiente para los BSON que salgan de los operadores ADN OR Y NOT , este es el que se devolvera al final.
		// a los operadores AND OR Y NOT SE LES PASA EL 
		
		//System.out.println("FILTER uppercase  : "  + auxFilter + " | " + filter );		
		switch(auxFilter) {
		
			case FILTERSAND : 
				getAuxMakeAndOrNotFilter().add(filterAnd( getAuxMakeFilter() ));
				break;
			case FILTEROR :
				getAuxMakeAndOrNotFilter().add(filterOr( getAuxMakeFilter() ));
				break;
			case FILTERNOT:
				getAuxMakeAndOrNotFilter().add(filterNot(getAuxMakeFilter() ));
				break;
			case FILTERSIN :
				getAuxMakeFilter().add(filterIn(field,value));	
				break;
			case FILTERSEQ :
				getAuxMakeFilter().add(filterEq(field,value));
				break;
			case FILTERALL:
				getAuxMakeFilter().add(filterAll(field,value));
				break;
			case FILTEREXISTS :
				getAuxMakeFilter().add(filterExists(field));
				break;
			case FILTERGT :
				getAuxMakeFilter().add(filterGt(field, value));
				break;
			case FILTERGTE :
				getAuxMakeFilter().add(filtergte(field, value));
				break;
			case FILTERLT :
				getAuxMakeFilter().add(filterlt(field, value));
				break;
			case FILTERLTE :
				getAuxMakeFilter().add(filterlte(field, value));
				break;
			case FILTERSNE :
				getAuxMakeFilter().add(filterNeq(field, value));
				break;
			case FIlTERREGEX :
				getAuxMakeFilter().add(filterRegex(field, value));
				break;
			default :
				// System.out.println("Error filtro incorrecto. " + auxFilter );
			break;
					
		}
		if ((getAuxMakeAndOrNotFilter().size() > 0 ) && (getAuxMakeFilter().size() > 0)) {
			getAuxMakeAndOrNotFilter().add( filterAnd( getAuxMakeFilter() ));
			return filterAnd(getAuxMakeAndOrNotFilter());
		}
		else if ((getAuxMakeAndOrNotFilter().size() > 0 )) {
			return (filterAnd( getAuxMakeFilter() ));
		}
		return (filterAnd( getAuxMakeFilter() ));
	}
	
	/**
	 * Filter and.
	 *
	 * @param filtersBson the filters bson
	 * @return the bson
	 */
	public Bson filterAnd(ArrayList<Bson> filtersBson ) {
		setAndFilter(true);
		return Filters.and(filtersBson);
	}
	
	/**
	 * Filter or.
	 *
	 * @param filtersBson the filters bson
	 * @return the bson
	 */
	public Bson filterOr(ArrayList<Bson> filtersBson) {
		setOrFIlter(true);
		return Filters.or(filtersBson);
	}
	
	/**
	 * Filter not.
	 *
	 * @param filtersBson the filters bson
	 * @return the bson
	 */
	public Bson filterNot(ArrayList<Bson>  filtersBson) {
		setNotFilter(true);
		Bson auxFilterNot =  filterAnd(filtersBson);
		return Filters.not(auxFilterNot);
	}
	
	/**
	 * Filter in.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterIn(String field, Object value) {
		setInFilter(true);
		return Filters.in(field, value);
	}
	
	/**
	 * Filter eq.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterEq(String field, Object value) {
		setEquealsFilter(true);
		return Filters.eq(field, value);
	}
	
	/**
	 * Filter all.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterAll(String field, Object value) {
		setAllFilter(true);
		return Filters.all(field, value);
	}
	
	/**
	 * Filter exists.
	 *
	 * @param field the field
	 * @return the bson
	 */
	public Bson filterExists(Object field) {
		setExistsFilter(true);
		return Filters.exists((String) field);
	}
	
	/**
	 * Filter gt.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterGt(String field, Object value) {
		setGtFilter(true);
		return Filters.gt(field, value);
	}
	
	/**
	 * Filtergte.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filtergte(String field,Object value) {
		setGteFilter(true);
		return Filters.gte(field, value);
	}
	
	/**
	 * Filterlt.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterlt(String field, Object value) {
		setLtFilter(true);
		return Filters.lt(field, value);
	}
	
	/**
	 * Filterlte.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterlte(String field, Object value) {
		setLteFIlter(true);
		return Filters.lte(field, value);
	}
	
	/**
	 * Filter neq.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterNeq(String field, Object value) {
		setNotEqcualFilter(true);
		return Filters.ne(field, value);
	}
	
	/**
	 * Filter regex.
	 *
	 * @param field the field
	 * @param value the value
	 * @return the bson
	 */
	public Bson filterRegex(String field, Object value) {
		setRegexFilter(true);
		return Filters.regex(field, (String) value);
	}
	
	/**
	 * Gets the field value filters.
	 *
	 * @return the field value filters
	 */
	public ArrayList<FieldValueFilter> getFieldValueFilters() {
		return fieldValueFilter;
	}

	/**
	 * Sets the field value filters.
	 *
	 * @param fieldValue the new field value filters
	 */
	public void setFieldValueFilters(ArrayList<FieldValueFilter> fieldValue) {
		this.fieldValueFilter = fieldValue;
	}

	/**
	 * Checks if is equeals filter.
	 *
	 * @return true, if is equeals filter
	 */
	public boolean isEquealsFilter() {
		return equealsFilter;
	}

	/**
	 * Sets the equeals filter.
	 *
	 * @param equealsFilter the new equeals filter
	 */
	public void setEquealsFilter(boolean equealsFilter) {
		this.equealsFilter = equealsFilter;
	}

	/**
	 * Checks if is in filter.
	 *
	 * @return true, if is in filter
	 */
	public boolean isInFilter() {
		return inFilter;
	}

	/**
	 * Sets the in filter.
	 *
	 * @param inFilter the new in filter
	 */
	public void setInFilter(boolean inFilter) {
		this.inFilter = inFilter;
	}

	/**
	 * Checks if is and filter.
	 *
	 * @return true, if is and filter
	 */
	public boolean isAndFilter() {
		return andFilter;
	}

	/**
	 * Sets the and filter.
	 *
	 * @param andFilter the new and filter
	 */
	public void setAndFilter(boolean andFilter) {
		this.andFilter = andFilter;
	}

	/**
	 * Checks if is or F ilter.
	 *
	 * @return true, if is or F ilter
	 */
	public boolean isOrFIlter() {
		return orFIlter;
	}

	/**
	 * Sets the or F ilter.
	 *
	 * @param orFIlter the new or F ilter
	 */
	public void setOrFIlter(boolean orFIlter) {
		this.orFIlter = orFIlter;
	}

	/**
	 * Checks if is lte F ilter.
	 *
	 * @return true, if is lte F ilter
	 */
	public boolean isLteFIlter() {
		return lteFIlter;
	}

	/**
	 * Sets the lte F ilter.
	 *
	 * @param lteFIlter the new lte F ilter
	 */
	public void setLteFIlter(boolean lteFIlter) {
		this.lteFIlter = lteFIlter;
	}

	/**
	 * Checks if is lt filter.
	 *
	 * @return true, if is lt filter
	 */
	public boolean isLtFilter() {
		return ltFilter;
	}

	/**
	 * Sets the lt filter.
	 *
	 * @param ltFilter the new lt filter
	 */
	public void setLtFilter(boolean ltFilter) {
		this.ltFilter = ltFilter;
	}

	/**
	 * Checks if is gt filter.
	 *
	 * @return true, if is gt filter
	 */
	public boolean isGtFilter() {
		return gtFilter;
	}

	/**
	 * Sets the gt filter.
	 *
	 * @param gtFilter the new gt filter
	 */
	public void setGtFilter(boolean gtFilter) {
		this.gtFilter = gtFilter;
	}

	/**
	 * Checks if is gte filter.
	 *
	 * @return true, if is gte filter
	 */
	public boolean isGteFilter() {
		return gteFilter;
	}

	/**
	 * Sets the gte filter.
	 *
	 * @param gteFilter the new gte filter
	 */
	public void setGteFilter(boolean gteFilter) {
		this.gteFilter = gteFilter;
	}

	/**
	 * Checks if is all filter.
	 *
	 * @return true, if is all filter
	 */
	public boolean isAllFilter() {
		return allFilter;
	}

	/**
	 * Sets the all filter.
	 *
	 * @param allFilter the new all filter
	 */
	public void setAllFilter(boolean allFilter) {
		this.allFilter = allFilter;
	}

	/**
	 * Checks if is not filter.
	 *
	 * @return true, if is not filter
	 */
	public boolean isNotFilter() {
		return notFilter;
	}

	/**
	 * Sets the not filter.
	 *
	 * @param notFilter the new not filter
	 */
	public void setNotFilter(boolean notFilter) {
		this.notFilter = notFilter;
	}

	/**
	 * Checks if is exists filter.
	 *
	 * @return true, if is exists filter
	 */
	public boolean isExistsFilter() {
		return existsFilter;
	}

	/**
	 * Sets the exists filter.
	 *
	 * @param existsFilter the new exists filter
	 */
	public void setExistsFilter(boolean existsFilter) {
		this.existsFilter = existsFilter;
	}

	/**
	 * Checks if is regex filter.
	 *
	 * @return true, if is regex filter
	 */
	public boolean isRegexFilter() {
		return regexFilter;
	}

	/**
	 * Sets the regex filter.
	 *
	 * @param regexFilter the new regex filter
	 */
	public void setRegexFilter(boolean regexFilter) {
		this.regexFilter = regexFilter;
	}

	/**
	 * Checks if is not eqcual filter.
	 *
	 * @return true, if is not eqcual filter
	 */
	public boolean isNotEqcualFilter() {
		return notEqcualFilter;
	}

	/**
	 * Sets the not eqcual filter.
	 *
	 * @param notEqcualFilter the new not eqcual filter
	 */
	public void setNotEqcualFilter(boolean notEqcualFilter) {
		this.notEqcualFilter = notEqcualFilter;
	}

	/**
	 * Gets the field value filter.
	 *
	 * @return the field value filter
	 */
	public ArrayList<FieldValueFilter> getFieldValueFilter() {
		return fieldValueFilter;
	}

	/**
	 * Sets the field value filter.
	 *
	 * @param fieldValueFilter the new field value filter
	 */
	public void setFieldValueFilter(ArrayList<FieldValueFilter> fieldValueFilter) {
		this.fieldValueFilter = fieldValueFilter;
	}

	/**
	 * Gets the aux make filter.
	 *
	 * @return the aux make filter
	 */
	public ArrayList<Bson> getAuxMakeFilter() {
		return auxMakeFilter;
	}

	/**
	 * Sets the aux make filter.
	 *
	 * @param auxMakeFilter the new aux make filter
	 */
	public void setAuxMakeFilter(ArrayList<Bson> auxMakeFilter) {
		this.auxMakeFilter = auxMakeFilter;
	}

	/**
	 * Gets the aux make and or not filter.
	 *
	 * @return the aux make and or not filter
	 */
	public ArrayList<Bson> getAuxMakeAndOrNotFilter() {
		return auxMakeAndOrNotFilter;
	}

	/**
	 * Sets the aux make and or not filter.
	 *
	 * @param auxMakeAndOrNotFilter the new aux make and or not filter
	 */
	public void setAuxMakeAndOrNotFilter(ArrayList<Bson> auxMakeAndOrNotFilter) {
		this.auxMakeAndOrNotFilter = auxMakeAndOrNotFilter;
	}
	
 }
