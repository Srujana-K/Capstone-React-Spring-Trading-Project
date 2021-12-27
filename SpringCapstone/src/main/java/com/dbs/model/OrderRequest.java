package com.dbs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	String clientId;
	String instrumentId;
    Double price;
    Integer quantity;
    OrderDirection orderDirection;
	public OrderRequest(String clientId, String instrumentId, Double price, Integer quantity,
			OrderDirection orderDirection) {
		super();
		this.clientId = clientId;
		this.instrumentId = instrumentId;
		this.price = price;
		this.quantity = quantity;
		this.orderDirection = orderDirection;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
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
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public OrderDirection getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(OrderDirection orderDirection) {
		this.orderDirection = orderDirection;
	}
	@Override
	public String toString() {
		return "OrderRequest [clientId=" + clientId + ", instrumentId=" + instrumentId + ", price=" + price
				+ ", quantity=" + quantity + ", orderDirection=" + orderDirection + "]";
	}

}
