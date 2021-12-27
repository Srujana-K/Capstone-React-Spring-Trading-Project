package com.dbs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.entity.Client;
import com.dbs.entity.Instrument;
import com.dbs.entity.Stock;
import com.dbs.model.OrderDirection;
import com.dbs.model.OrderStatus;

public interface StockRepository extends JpaRepository<Stock, Long> {
	List<Stock> findAllByOrderDirectionAndOrderStatus(OrderDirection orderDirection, OrderStatus orderStatus);

	List<Stock> findAllByOrderDirection(OrderDirection orderDirection);

	List<Stock> findAllByOrderDirectionAndOrderStatusAndInstrument(OrderDirection orderDirection, 
			OrderStatus orderStatus, Instrument instrument);

	Optional<Stock> findByClientAndInstrumentAndPriceAndOrderDirectionAndOrderStatus(Client client, Instrument instrument,
				double price, OrderDirection orderDirection, OrderStatus orderStatus);

	//Optional<Stock> findBy
}
