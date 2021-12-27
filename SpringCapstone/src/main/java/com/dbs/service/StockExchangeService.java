package com.dbs.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.dao.StockExchangeRepository;
import com.dbs.entity.Client;
import com.dbs.entity.Instrument;
import com.dbs.entity.StockExchange;
import com.dbs.exception.ValidationException;
import com.dbs.model.ClientsNetCash;
import com.dbs.model.CustodiansNetCash;

@Service
public class StockExchangeService {
	
	@Autowired
	StockExchangeRepository stockExchangeRepository;
	public List<StockExchange> findByClient(Client id) {
		return stockExchangeRepository.findByClient(id);
	}
	
	public Optional<StockExchange> findByClientAndInstrument(Client client, Instrument instrument){
		return stockExchangeRepository.findByClientAndInstrument(client, instrument);
	}
	public StockExchange save(Client client, Instrument instrument, Integer quantity) throws ValidationException {
        Optional<StockExchange> stockExchangeOpt = stockExchangeRepository.findByClientAndInstrument(client, instrument);
        StockExchange stockExchange;
        if(stockExchangeOpt.isPresent()) {
        	stockExchange = stockExchangeOpt.get();
        }
        else {
        	stockExchange = new StockExchange(client, instrument, 0, new Date());     	
        }
        System.out.println("foundd " + stockExchange);
        int finalQuantity = quantity + stockExchange.getQuantity();
        System.out.println("finall  " + finalQuantity);
        if(finalQuantity < 0) {
        	throw new ValidationException("the seller doesn't have the required number of stocks to complete transaction");
        }
        stockExchange.setQuantity(finalQuantity);
        System.out.println("savingg " + stockExchange);
        return stockExchangeRepository.save(stockExchange);
	}
	
	public List<ClientsNetCash> clientsStats() {
		return stockExchangeRepository.getClientWiseStats();
	}
	public List<CustodiansNetCash> custodiansStats() {
		return stockExchangeRepository.getCustodianWiseStats();
	}
	

	
}
