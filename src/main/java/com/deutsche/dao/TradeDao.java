package com.deutsche.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deutsche.model.Trade;

@Component
public class TradeDao {

	public static List<Trade> trades = new ArrayList<>();
	
	static {
		
		trades.add(new Trade("T1", 1, "CP-1", "B1",new GregorianCalendar(2020, 5, 20).getTime(), new Date(), false));
		trades.add(new Trade("T2", 2, "CP-2", "B1",new GregorianCalendar(2021, 5, 20).getTime(), new Date(), false));
		trades.add(new Trade("T3", 3, "CP-3", "B2",new GregorianCalendar(2014, 5, 20).getTime(), new Date(), false));
	
	}
	
	public List<Integer> fetchTradeVersionIds(){
		return trades.stream().map(m->m.getVersion()).collect(Collectors.toList());
	}
	
	public Optional<Trade>  fetchTradeByVersion(int version) {
		return trades.stream().filter(f->f.getVersion()==version).findFirst();
	}
	
	public void saveTrade(Trade trade) {
		trades.add(trade);
	}
	
	public List<Trade> fetchMaturityDateCrossedTrades(){
		return trades.stream().filter(t->t.getMaturityDate().before(new Date())).collect(Collectors.toList());
	}
	
	public Optional<Trade>  fetchTradeById(String tradeId) {
		return trades.stream().filter(f->f.getTradeId().equals(tradeId)).findFirst();
	}
	
	public List<Trade> fetchTrades() {
		return trades;
	}
}
