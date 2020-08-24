package Test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Mongo.MatchFilterObject;

// TODO: Auto-generated Javadoc
/**
 * The Class MatchFilterIObjectTest.
 */
public class MatchFilterIObjectTest {

	/** The aux field name. */
	private ArrayList<String> auxFieldName;

	/** The aux field value. */
	private ArrayList<Object> auxFieldValue;

	/** The field name. */
	private ArrayList<ArrayList<String>> fieldName;

	/** The field value. */
	private ArrayList<ArrayList<Object>> fieldValue;

	/** The filters. */
	private ArrayList<String> filters;

	/**
	 * Test.
	 */
	@Test
	public void test() {
		/*
		 * insterFielValuePairTest(); MatchAndTest(); MatchOrTest(); MatchNotTest();
		 * MatchAllTest(); MatchEquealsTest(); MatchExistsTest(); MatchGteTest();
		 * MatchGtTest(); MatchInTest(); MatchLteTest(); MatchLtTest(); MatchNeTest();
		 * MatchRegexTest();
		 */
	}

	/**
	 * Inster fiel value pair test.
	 */
	public void insterFielValuePairTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("nad");
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("nad");
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("nad");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		Assert.assertEquals(3, auxTest.getFieldValueFilters().size());

	}

	/**
	 * Match and test.
	 */
	public void MatchAndTest() {

		initAtributes();
		makeTestArgumentsFields("asdas", "Match");
		makeTestArguments("and");

		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isAndFilter());

	}

	/**
	 * Match or test.
	 */
	public void MatchOrTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("or");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isOrFIlter());
	}

	/**
	 * Match not test.
	 */
	public void MatchNotTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("nOt");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isNotFilter());
	}

	/**
	 * Match equeals test.
	 */
	public void MatchEquealsTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("eQuaLS");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isEquealsFilter());
	}

	/**
	 * Match in test.
	 */
	public void MatchInTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("iN");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isInFilter());
	}

	/**
	 * Match lt test.
	 */
	public void MatchLtTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("lt");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isLtFilter());
	}

	/**
	 * Match lte test.
	 */
	public void MatchLteTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("lte");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isLteFIlter());
	}

	/**
	 * Match gt test.
	 */
	public void MatchGtTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("gT");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isGtFilter());
	}

	/**
	 * Match gte test.
	 */
	public void MatchGteTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("GtE");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isGteFilter());
	}

	/**
	 * Match all test.
	 */
	public void MatchAllTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("AlL");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isAllFilter());
	}

	/**
	 * Match exists test.
	 */
	public void MatchExistsTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("eXiSTs");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isExistsFilter());
	}

	/**
	 * Match regex test.
	 */
	public void MatchRegexTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("rEgEX");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isRegexFilter());
	}

	/**
	 * Match ne test.
	 */
	public void MatchNeTest() {
		initAtributes();
		makeTestArgumentsFields("asdd", "asdd");
		makeTestArguments("Ne");
		MatchFilterObject auxTest = new MatchFilterObject(fieldName, fieldValue, filters);
		auxTest.matchStageBuilder();
		Assert.assertTrue(auxTest.isNotEqcualFilter());
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public ArrayList<ArrayList<String>> getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(ArrayList<ArrayList<String>> fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the field value.
	 *
	 * @return the field value
	 */
	public ArrayList<ArrayList<Object>> getFieldValue() {
		return fieldValue;
	}

	/**
	 * Sets the field value.
	 *
	 * @param fieldValue the new field value
	 */
	public void setFieldValue(ArrayList<ArrayList<Object>> fieldValue) {
		this.fieldValue = fieldValue;
	}

	/**
	 * Gets the filters.
	 *
	 * @return the filters
	 */
	public ArrayList<String> getFilters() {
		return filters;
	}

	/**
	 * Sets the filters.
	 *
	 * @param filters the new filters
	 */
	public void setFilters(ArrayList<String> filters) {
		this.filters = filters;
	}

	/**
	 * Gets the aux field name.
	 *
	 * @return the aux field name
	 */
	public ArrayList<String> getAuxFieldName() {
		return auxFieldName;
	}

	/**
	 * Sets the aux field name.
	 *
	 * @param auxFieldName the new aux field name
	 */
	public void setAuxFieldName(ArrayList<String> auxFieldName) {
		this.auxFieldName = auxFieldName;
	}

	/**
	 * Gets the aux field value.
	 *
	 * @return the aux field value
	 */
	public ArrayList<Object> getAuxFieldValue() {
		return auxFieldValue;
	}

	/**
	 * Sets the aux field value.
	 *
	 * @param auxFieldValue the new aux field value
	 */
	public void setAuxFieldValue(ArrayList<Object> auxFieldValue) {
		this.auxFieldValue = auxFieldValue;
	}

	/**
	 * Make test arguments.
	 *
	 * @param filter the filter
	 */
	public void makeTestArguments(String filter) {
		getFilters().add(filter);
		getFieldName().add(getAuxFieldName());
		getFieldValue().add(getAuxFieldValue());
	}

	/**
	 * Make test arguments fields.
	 *
	 * @param fieldName  the field name
	 * @param fieldValue the field value
	 */
	public void makeTestArgumentsFields(String fieldName, String fieldValue) {
		getAuxFieldName().add(fieldName);
		getAuxFieldValue().add(fieldValue);
	}

	/**
	 * Inits the atributes.
	 */
	public void initAtributes() {
		setFieldName(new ArrayList<ArrayList<String>>());
		setFieldValue((new ArrayList<ArrayList<Object>>()));
		setFilters(new ArrayList<String>());
		setAuxFieldName(new ArrayList<String>());
		setAuxFieldValue(new ArrayList<Object>());
	}

	/**
	 * Reset auxiliars.
	 */
	public void resetAuxiliars() {
		setAuxFieldName(new ArrayList<String>());
		setAuxFieldValue(new ArrayList<Object>());
	}

}
