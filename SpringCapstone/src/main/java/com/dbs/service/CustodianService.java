package com.dbs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dbs.dao.ClientRepository;
import com.dbs.dao.CustodianRepository;
import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidCustodianException;

@Service
public class CustodianService {
	
	@Autowired
	CustodianRepository custodianRepository;
	
	public Custodian findById(String id) throws InvalidClientException, InvalidCustodianException {
		Optional<Custodian> custodian = custodianRepository.findById(id);
        if(!custodian.isPresent()) {
            throw new InvalidCustodianException("Custodian not found with id - " + id);
        }
        return custodian.get();
	}
}
