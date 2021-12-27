package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.entity.Stock;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidCustodianException;
import com.dbs.exception.InvalidInstrumentException;
import com.dbs.exception.ValidationException;
import com.dbs.model.OrderRequest;
import com.dbs.service.ClientService;
import com.dbs.service.CustodianService;
import com.dbs.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	ClientService clientService;
	@Autowired
	CustodianService custodianService;
	@Autowired
	StockService stockService;
	
	
	@GetMapping("/{stockDirection}")
	public ResponseEntity<List<Stock>> getAllStocksByDirection(@PathVariable String stockDirection) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByOrderDirection(stockDirection.toUpperCase()), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Stock> createStock(@RequestBody OrderRequest orderRequest) throws InvalidClientException, InvalidInstrumentException, ValidationException {
		return new ResponseEntity<Stock>(stockService.processTransaction(orderRequest), HttpStatus.OK);
	}
}
