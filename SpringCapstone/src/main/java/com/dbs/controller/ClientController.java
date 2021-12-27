package com.dbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidCustodianException;
import com.dbs.service.ClientService;
import com.dbs.service.CustodianService;

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	@Autowired
	CustodianService custodianService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClient(@PathVariable String id) throws InvalidClientException {
		return new ResponseEntity<Client>(clientService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Client>> getAllClients() {
		return new ResponseEntity<List<Client>>(clientService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/custodian/{custodianId}")
	public ResponseEntity<List<Client>> getAllClientsByCustodian(@PathVariable String custodianId) throws InvalidClientException, InvalidCustodianException {
		return new ResponseEntity<List<Client>>(clientService.findAllByCustodian(custodianService.findById(custodianId)), HttpStatus.OK);
	}
	
	
	
	
	
	
	

}
