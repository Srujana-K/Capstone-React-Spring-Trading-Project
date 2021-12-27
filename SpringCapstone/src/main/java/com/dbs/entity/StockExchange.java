package com.dbs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
public class StockExchange {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id")
	Client client;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "instrument_id")
	Instrument instrument;

	@Column
	int quantity;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    Date timeStamp;
	
	public StockExchange() {
		super();
	}
	

	public StockExchange(Client client, Instrument instrument, int quantity,
			Date timeStamp) {
		super();
		this.client = client;
		this.instrument = instrument;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "StockExchange [id=" + id + ", client=" + client + ", ="  + ", instrument=" + instrument + ", "
				 + ", quantity=" + quantity + ", timeStamp=" + timeStamp + "]";
	}	
}
