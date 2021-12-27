package com.dbs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Custodian {
	
	@Id
	@Column
	String id;
	
	@Column
	String custodianName;

	@OneToMany(mappedBy = "custodian")
	private List<Client> clients;
	
	public Custodian() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Custodian(String id, String custodianName) {
		super();
		this.id = id;
		this.custodianName = custodianName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustodianName() {
		return custodianName;
	}

	public void setCustodianName(String custodianName) {
		this.custodianName = custodianName;
	}

	@Override
	public String toString() {
		return "Custodian [id=" + id + ", custodianName=" + custodianName + "]";
	}
	
	
}
