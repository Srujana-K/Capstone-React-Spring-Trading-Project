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
import com.dbs.entity.Instrument;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidCustodianException;
import com.dbs.exception.InvalidInstrumentException;
import com.dbs.service.ClientService;
import com.dbs.service.CustodianService;
import com.dbs.service.InstrumentService;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {
	
	@Autowired
	InstrumentService instrumentService;
	@Autowired
	CustodianService custodianService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Instrument> getInstrument(@PathVariable String id) throws InvalidInstrumentException, InvalidClientException {
		return new ResponseEntity<Instrument>(instrumentService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Instrument>> findAll() {
		return new ResponseEntity<List<Instrument>>(instrumentService.findAll(), HttpStatus.OK);
	}

	
	
	
	
	
	
	

}
