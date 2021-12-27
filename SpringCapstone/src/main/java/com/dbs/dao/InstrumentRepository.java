package com.dbs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.entity.Instrument;

public interface InstrumentRepository extends JpaRepository<Instrument, String>{

}
