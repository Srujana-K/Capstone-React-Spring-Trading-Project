package com.dbs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.entity.StockExchange;

import org.springframework.stereotype.Repository;
import com.dbs.entity.Custodian;

@Repository
public interface CustodianRepository extends JpaRepository<Custodian, String>{
	
}
