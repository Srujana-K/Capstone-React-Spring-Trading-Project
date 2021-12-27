package com.dbs.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instrument {
	
	@Id
	@Column
	String id;
	
	@Column
	String instrumentName;
	
	@Column
	int faceValue;
	
	@Column
	Date expiryDate;

	
	public Instrument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Instrument(String id, String instrumentName, int faceValue, Date expiryDate) {
		super();
		this.id = id;
		this.instrumentName = instrumentName;
		this.faceValue = faceValue;
		this.expiryDate = expiryDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Instrument [id=" + id + ", instrumentName=" + instrumentName + ", faceValue=" + faceValue
				+ ", expiryDate=" + expiryDate + "]";
	}

	
	
}
