package com.dbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.dbs.dao.ClientRepository;
import com.dbs.dao.InstrumentRepository;
import com.dbs.entity.Client;
import com.dbs.entity.Instrument;
import com.dbs.exception.InvalidClientException;
import com.dbs.exception.InvalidInstrumentException;

@Service
public class InstrumentService {
	
	@Autowired
	InstrumentRepository instrumentRepository;
	
	public Instrument findById(String id) throws InvalidInstrumentException {
		Optional<Instrument> instrument = instrumentRepository.findById(id);
        if(!instrument.isPresent()) {
            throw new InvalidInstrumentException("Instrument not found with id - " + id);
        }
        return instrument.get();
	}
	
	public List<Instrument> findAll() {
		return instrumentRepository.findAll();
	}

}
