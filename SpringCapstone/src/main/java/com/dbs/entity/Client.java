package com.dbs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table

public class Client {
	
	@Id
	@Column
	String id;
	
	@Column
	String clientName;
	
//	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "custodian_id")
	Custodian custodian;
	
	@Column
	double transactionLimit;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String id, String clientName, Custodian custodian, double transactionLimit) {
		super();
		this.id = id;
		this.clientName = clientName;
		this.custodian = custodian;
		this.transactionLimit = transactionLimit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Custodian getCustodian() {
		return custodian;
	}

	public void setCustodian(Custodian custodian) {
		this.custodian = custodian;
	}

	public double getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(double transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + id + ", clientName=" + clientName + ", custodian=" + custodian + ", price="
				+ transactionLimit + "]";
	}
	
	
}
