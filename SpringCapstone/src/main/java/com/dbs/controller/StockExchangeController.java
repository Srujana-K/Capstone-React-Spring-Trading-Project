package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.dao.StockExchangeRepository;
import com.dbs.model.ClientsNetCash;
import com.dbs.model.CustodiansNetCash;

@RestController
@RequestMapping("/dashboard")
public class StockExchangeController {
	@Autowired
	StockExchangeRepository stockExchangeRepository;
	
	@GetMapping("/client")
	public ResponseEntity<List<ClientsNetCash>> getClientStats() {
		return new ResponseEntity<List<ClientsNetCash>>(stockExchangeRepository.getClientWiseStats(), HttpStatus.OK);
	}
	
	@GetMapping("/custodian")
	public ResponseEntity<List<CustodiansNetCash>> getCustodianStats() {
		return new ResponseEntity<List<CustodiansNetCash>>(stockExchangeRepository.getCustodianWiseStats(), HttpStatus.OK);
	}

}
