package Mongo;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldValueFilter.
 *
 * @author alberto
 */

public class FieldValueFilter {

	/** The field name. */
	private ArrayList<String> fieldName ;
	
	/** The field value. */
	private ArrayList<Object> fieldValue;
	
	/** The filter. */
	private String filter;
	
	/**
	 * Instantiates a new field value filter.
	 *
	 * @param fieldName the field name
	 * @param fieldValue the field value
	 * @param filter the filter
	 */
	public FieldValueFilter (ArrayList<String> fieldName , ArrayList<Object> fieldValue , String filter) {

		setFieldName(fieldName);
		setFieldValue(fieldValue);
		setFilter(filter);
	}
	
	/**
	 * Gets the value.
	 *
	 * @param i the i
	 * @return the value
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public Object getValue(int i) throws IndexOutOfBoundsException{
		try {
			return getFieldValue().get(i);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			
		}
		return null;
		
	}
	
	/**
	 * Gets the field.
	 *
	 * @param i the i
	 * @return the field
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public String getField(int i) throws IndexOutOfBoundsException{
		try {
			return getFieldName().get(i);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
		
	}
	
	/**
	 * Gets the filter.
	 *
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the filter.
	 *
	 * @param filter the new filter
	 */
	public void setFilter( String filter) {
		this.filter = filter;
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public ArrayList<String> getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(ArrayList<String> fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Gets the field value.
	 *
	 * @return the field value
	 */
	public ArrayList<Object> getFieldValue() {
		return fieldValue;
	}

	/**
	 * Sets the field value.
	 *
	 * @param fieldValue the new field value
	 */
	public void setFieldValue(ArrayList<Object> fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
