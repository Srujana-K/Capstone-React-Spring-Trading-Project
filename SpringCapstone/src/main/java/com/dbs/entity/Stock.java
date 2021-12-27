package com.dbs.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dbs.model.OrderDirection;
import com.dbs.model.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	long id;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    Instrument instrument;

    Double price;

    Integer quantity;
    
    Integer initialQuantity;
	
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    OrderDirection orderDirection;

    @Temporal(TemporalType.TIMESTAMP)
    Date timeStamp;

    
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(Client client, Instrument instrument, Double price, Integer quantity, OrderStatus orderStatus,
			OrderDirection orderDirection, Date timeStamp, Integer initialQuantity) {
		super();
		this.client = client;
		this.instrument = instrument;
		this.price = price;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
		this.orderDirection = orderDirection;
		this.timeStamp = timeStamp;
		this.initialQuantity = initialQuantity;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getInitialQuantity() {
		return initialQuantity;
	}

	public void setInitialQuantity(Integer initialQuantity) {
		this.initialQuantity = initialQuantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderDirection getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", client=" + client + ", instrument=" + instrument + ", price=" + price
				+ ", quantity=" + quantity + ", initialQuantity=" + initialQuantity + ", orderStatus=" + orderStatus
				+ ", orderDirection=" + orderDirection + ", timeStamp=" + timeStamp + "]";
	}

	
    
    
}
