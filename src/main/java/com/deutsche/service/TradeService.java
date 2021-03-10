package com.deutsche.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.dao.TradeDao;
import com.deutsche.exceptions.MaturityDateInValid;
import com.deutsche.exceptions.TradeNotFound;
import com.deutsche.exceptions.VersionNotValid;
import com.deutsche.model.Trade;

@Service
public class TradeService {

	@Autowired
	private TradeDao tradeDao;
	
	public void validateTrade(Trade trade) {
		/* version should be greater than 1 */
		if(trade.getVersion() < 1) {
			throw new VersionNotValid("Version should be greater than 0");
		}
		/* maturity date should be in future date */
		if(trade.getMaturityDate().before(new Date())) {
			throw new MaturityDateInValid("Maturity date should be future date");
		}
	}
	
	public Trade addTrade(Trade trade) {
		/* fetch existing record of trade by version */
		Optional<Trade> existingTrade = tradeDao.fetchTradeByVersion(trade.getVersion());
		
		/* if it exist then update it or create new trade */ 
		if(existingTrade.isPresent()) {
			Trade dbTrade = existingTrade.get();
			dbTrade.setBookId(trade.getBookId());
			dbTrade.setCounterPartyId(trade.getCounterPartyId());
			dbTrade.setMaturityDate(trade.getMaturityDate());
			return dbTrade;
		} else {
			trade.setTradeId(UUID.randomUUID().toString());
			trade.setCreatedDate(new Date());
			tradeDao.saveTrade(trade);
			return trade;
		}
		
	}
	
	public void updateExpiredFlag() {
		List<Trade> trades = tradeDao.fetchMaturityDateCrossedTrades();
		for (Trade trade : trades) {
			trade.setExpired(true);
		}
	}
	
	public Trade fetchTradeById(String tradeId) {
		Optional<Trade> trade = tradeDao.fetchTradeById(tradeId);
		if(trade.isPresent()) {
			return trade.get();
		}
		throw new TradeNotFound();
	}
	
	public List<Trade> fetchTrades() {
		return tradeDao.fetchTrades();
	}
}
