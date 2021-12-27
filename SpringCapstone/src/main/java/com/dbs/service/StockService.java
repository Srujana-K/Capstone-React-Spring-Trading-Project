package com.dbs.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.dao.StockRepository;
import com.dbs.entity.Client;
import com.dbs.entity.Instrument;
import com.dbs.entity.Stock;
import com.dbs.entity.StockExchange;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidInstrumentException;
import com.dbs.exception.ValidationException;
import com.dbs.model.OrderDirection;
import com.dbs.model.OrderRequest;
import com.dbs.model.OrderStatus;

import jdk.internal.org.jline.utils.Log;

import java.util.stream.Collectors;
import java.util.Comparator;

@Service
public class StockService {

	@Autowired
	ClientService clientService;
	@Autowired
	InstrumentService instrumentService;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	StockExchangeService stockExchangeService;

	public Stock processTransaction(OrderRequest orderRequest)
			throws InvalidClientException, InvalidInstrumentException, ValidationException {

		if (orderRequest.getOrderDirection().equals(OrderDirection.BUY)) {
			return this.buyStock(orderRequest);
		}
		if (orderRequest.getOrderDirection().equals(OrderDirection.SELL)) {
			return this.sellStock(orderRequest);
		}
		throw new ValidationException("Invalid order direction");
	}

	public Stock buyStock(OrderRequest orderRequest) throws InvalidClientException, InvalidInstrumentException, ValidationException {
		Client client = clientService.findById(orderRequest.getClientId());
		Instrument instrument = instrumentService.findById(orderRequest.getInstrumentId());
		
		double stockPrice = orderRequest.getPrice() * orderRequest.getQuantity();
		
		//check if 
		if(client.getTransactionLimit() < stockPrice) {
			throw new ValidationException("No sufficient amount");
		}
		
		Optional<Stock> stockOpt = 
			stockRepository.findByClientAndInstrumentAndPriceAndOrderDirectionAndOrderStatus(client, instrument
					, orderRequest.getPrice(), OrderDirection.BUY, OrderStatus.PROCESSING);
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			if(stock.getPrice().equals(orderRequest.getPrice())) {
				stock.setQuantity(stock.getQuantity() + orderRequest.getQuantity());
				stock.setInitialQuantity(stock.getQuantity());
				stock.setTimeStamp(new Date());
				return save(stock, stockPrice, orderRequest.getQuantity(), client, true);
			}
		}
		Stock buyerStock = new Stock(client, instrument, orderRequest.getPrice(), orderRequest.getQuantity(),
				OrderStatus.PROCESSING, OrderDirection.BUY, new Date(), orderRequest.getQuantity());
		
		stockRepository.save(buyerStock);
		List<Stock> sellerStocks = stockRepository.findAllByOrderDirectionAndOrderStatusAndInstrument(
				OrderDirection.SELL, OrderStatus.PROCESSING, instrument).stream()
				.sorted(Comparator.comparing(Stock::getId))
//				.filter(e -> e.getClient().getTransactionLimit() >= stockPrice)
				.filter(e -> e.getPrice().equals(orderRequest.getPrice()))
				.collect(Collectors.toList());
		
		if(sellerStocks.isEmpty()) {
			System.out.println("no sellers");
			stockRepository.save(buyerStock);
		}
		
		Optional<Stock> sellerWithRequiredQuantityOpt = sellerStocks.stream()
				.sorted(Comparator.comparing(Stock::getId))
                .filter(e -> e.getQuantity() >= buyerStock.getQuantity())
                .min(Comparator.comparing(Stock::getId));
		if(sellerWithRequiredQuantityOpt.isPresent()) {
			matchStocks(client, sellerWithRequiredQuantityOpt.get().getClient(), buyerStock, 
					sellerWithRequiredQuantityOpt.get(), true);
		}
		else {
			 int requiredStockQuantity = buyerStock.getQuantity();
			 for (Stock sellerStock : sellerStocks){
				 Client seller = sellerStock.getClient();
	             if (requiredStockQuantity <= sellerStock.getQuantity()) {
	            	 matchStocks(client, seller, buyerStock, sellerStock,true);
	                 break;
	             } 
	             else {
	                requiredStockQuantity = requiredStockQuantity - sellerStock.getQuantity();
	                matchStocks(client, seller, buyerStock,sellerStock, false);
	             }
	         }
		}
		return stockRepository.save(buyerStock);
	}


	private Stock sellStock(OrderRequest orderRequest) throws ValidationException, InvalidInstrumentException, InvalidClientException {

		Instrument instrument = instrumentService.findById(orderRequest.getInstrumentId());
        Client seller = clientService.findById(orderRequest.getClientId());

        StockExchange existingStock = stockExchangeService.findByClientAndInstrument(seller, instrument).orElseThrow(() ->
                new ValidationException("seems like the seller doesn't have the specified stocks"));
        if(existingStock.getQuantity() < orderRequest.getQuantity()){
            throw new ValidationException("the seller doesn't have the specified number of stocks");
        }
        System.out.println("done");
        Optional<Stock> stockOpt = stockRepository.findByClientAndInstrumentAndPriceAndOrderDirectionAndOrderStatus(seller, instrument,
        										orderRequest.getPrice(), OrderDirection.SELL, OrderStatus.PROCESSING);
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			System.out.println("presemtt " + stock);
			if(stock.getPrice().equals(orderRequest.getPrice())) {
				stock.setQuantity(stock.getQuantity() + orderRequest.getQuantity());
				stock.setInitialQuantity(stock.getQuantity());
				stock.setTimeStamp(new Date());
				System.out.println("Stockk " +stock);
				return stockRepository.save(stock);
			}
		}
		
        Stock sellerStock = new Stock(seller, instrument, orderRequest.getPrice(), orderRequest.getQuantity(),
				OrderStatus.PROCESSING, OrderDirection.SELL, new Date(), orderRequest.getQuantity());
        stockRepository.save(sellerStock);
        
        double stockPrice = orderRequest.getQuantity() * orderRequest.getPrice();
        List<Stock> buyerStocks = stockRepository.findAllByOrderDirectionAndOrderStatusAndInstrument(
				OrderDirection.BUY, OrderStatus.PROCESSING, instrument).stream()
				.sorted(Comparator.comparing(Stock::getId))
				.filter(e -> e.getClient().getTransactionLimit() >= stockPrice)
				.filter(e -> e.getPrice().equals(orderRequest.getPrice()))
				.collect(Collectors.toList());
		
		if(buyerStocks.isEmpty()) {
			System.out.println("no buyers");
			stockRepository.save(sellerStock);
		}
		Optional<Stock> buyerWithRequiredQuantityOpt = buyerStocks.stream()
                .filter(e -> e.getQuantity() >= sellerStock.getQuantity())
                .min(Comparator.comparing(Stock::getId));
		if(buyerWithRequiredQuantityOpt.isPresent()) {
			matchStocks(seller, buyerWithRequiredQuantityOpt.get().getClient(), sellerStock, 
					buyerWithRequiredQuantityOpt.get(), true);
		}
		else {
			 int requiredStockQuantity = sellerStock.getQuantity();
			 for (Stock buyerStock : buyerStocks){
				 Client buyer = buyerStock.getClient();
	             if (requiredStockQuantity <= buyerStock.getQuantity()) {
	            	 matchStocks(buyer, seller, buyerStock, sellerStock, false);
	                 break;
	             } 
	             else {
	                requiredStockQuantity = requiredStockQuantity - buyerStock.getQuantity();
	                matchStocks(buyer, seller, buyerStock, sellerStock, true);
	             }
	         }
		}       
		return stockRepository.save(sellerStock);
	}
	
	private Stock save(Stock stock, double stockPrice, Integer quantity, Client client, boolean isBuyer) {
		if(isBuyer) {
			client.setTransactionLimit(client.getTransactionLimit() - stockPrice);
		}
		else {
			client.setTransactionLimit(client.getTransactionLimit() + stockPrice);
		}
		return stockRepository.save(stock);
	}

	
	private void matchStocks(Client buyer, Client seller, Stock buyerStock, Stock sellerStock, boolean consider) throws ValidationException {
		Stock stock;
        if(consider){ //consider the quantity and price present in buyerOrderBookInstance while exchanging the stock
        	stock = buyerStock;
        }
        else{
        	stock = sellerStock;
        }

        stockExchangeService.save(buyer, stock.getInstrument(), stock.getQuantity());
        stockExchangeService.save(seller, stock.getInstrument(), -1 * stock.getQuantity());

        //adjust the transaction limit
        buyer.setTransactionLimit(buyer.getTransactionLimit() - stock.getQuantity() * stock.getPrice());
        seller.setTransactionLimit(seller.getTransactionLimit() + stock.getQuantity() * stock.getPrice());
        clientService.save(buyer);
        clientService.save(seller);

        // we need to adjust the stock quantity in the order request of seller and buyer
        int quantityToBeSubtracted = stock.getQuantity();
        buyerStock.setQuantity(buyerStock.getQuantity() - quantityToBeSubtracted);
        sellerStock.setQuantity(sellerStock.getQuantity() - quantityToBeSubtracted);
        if(buyerStock.getQuantity().equals(0)){
        	buyerStock.setOrderStatus(OrderStatus.COMPLETED);
        }
        if(sellerStock.getQuantity().equals(0)){
        	sellerStock.setOrderStatus(OrderStatus.COMPLETED);
        }
        stockRepository.save(buyerStock);
        stockRepository.save(sellerStock);
		
	}
	

	public List<Stock> getStocksByOrderDirection(String orderDirection) {
		if (orderDirection.equals("SELL")) {
			return stockRepository.findAllByOrderDirection(OrderDirection.SELL);
		}
		return stockRepository.findAllByOrderDirection(OrderDirection.BUY);
	}

	public Stock findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
