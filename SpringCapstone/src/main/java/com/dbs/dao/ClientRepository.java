package com.dbs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;

@Repository
public interface ClientRepository extends JpaRepository< Client, String>{
	
	public List<Client> findAllByCustodian(Custodian custodian);

}
