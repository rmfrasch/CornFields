package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author RYANF This class is meant to define a corn field location with its
 *         name and dimensions using a coordinate system of passes and ranges.
 *         This is also linked to mySQL database of 'Field', and controls
 *         entries into the 'Fields' table.
 */

//Field is the name of the database using the Entity annotation to have persistence through JPA
@Entity(name = "FIELD")
//Fields is the name of the table within the database.  The annotation for the table.
@Table(name = "FIELDS")
public class FieldDescription {
	//Describes the name of the field, with the annotation for the table column header.
	@Column(name = "FIELDNAME")
	private String fieldName;
	//Is the number of passes in the field, with annotation for the column header.
	@Column(name = "PASSNUM")
	private int passNum;
	//Is the number of ranges in the field, with annotation for the column header in the table.
	@Column(name = "RANGENUM")
	private int rangeNum;
	
	//What the lab said to enter into my class for annotations for automated ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int fieldID;

	// Constructors

	public FieldDescription(String fieldName, int passNum, int rangeNum) {
		super();
		this.fieldName = fieldName;
		this.passNum = passNum;
		this.rangeNum = rangeNum;
	}

	public FieldDescription(String fieldName) {
		this.fieldName = fieldName;
		this.fieldID = fieldID;
	}

	public FieldDescription() {

	}
	//Getters and Setters
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	public int getRangeNum() {
		return rangeNum;
	}

	public void setRangeNum(int rangeNum) {
		this.rangeNum = rangeNum;
	}

	public int getFieldID() {
		return fieldID;
	}

	public void setFieldID(int fieldID) {
		this.fieldID = fieldID;
	}
	//Print a record on the database.
	public String print() {
		return "ID: " + fieldID + "\t\tfieldName: " + fieldName + "\tNumber of passes: " + passNum
				+ "\tNumber of ranges: " + rangeNum;
	}

}
