package com.dbs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dbs.entity.Client;
import com.dbs.entity.Custodian;
import com.dbs.entity.Instrument;
import com.dbs.entity.StockExchange;
import com.dbs.model.ClientsNetCash;
import com.dbs.model.CustodiansNetCash;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Long>{
	List<StockExchange> findByClient(Client id);
    Optional<StockExchange> findByClientAndInstrument(Client client, Instrument instrument);
    @Query(value="select c.custodian_id as custodianId," +
            "sum(IF(s.order_direction='BUY' and s.order_status='COMPLETED',s.price * initial_quantity,0)) as totalBuyValue," +
            "sum(IF(s.order_direction='SELL' and s.order_status='COMPLETED',s.price * initial_quantity,0)) as totalSellValue " +
            "from client c left join stock s on c.id = s.client_id " +
            "group by c.custodian_id",nativeQuery = true)
    List<CustodiansNetCash> getCustodianWiseStats();

    @Query(value="select c.id as clientId," +
            "sum(if(s.order_direction = 'SELL' and s.order_status='COMPLETED',s.price * initial_quantity,0)) as totalSellValue," +
            "sum(if(s.order_direction = 'BUY' and s.order_status='COMPLETED',s.price * initial_quantity,0)) as totalBuyValue " +
            "from client c " +
            "left join stock s on c.id = s.client_id " +
            "group by c.id",nativeQuery = true)
    List<ClientsNetCash> getClientWiseStats();
}
