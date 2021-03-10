package com.deutsche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.model.Trade;
import com.deutsche.service.TradeService;

@RestController
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	/* 
	 * rest api to add trade under store 
	 * with required validations 
	 * 
	 * */
	@PostMapping("/trades")
	public Trade addTrade(@RequestBody Trade trade) {
		
		tradeService.validateTrade(trade);
		
		return tradeService.addTrade(trade);
	}
	
	@GetMapping("/trades/{tradeId}")
	public Trade fetchTrade(@PathVariable("tradeId") String tradeId) {
		
		return tradeService.fetchTradeById(tradeId);
	}
	
	@GetMapping("/trades")
	public List<Trade> fetchTrades() {
		
		return tradeService.fetchTrades();
	}
	
	/* 
	 * cron API to update expired flag if trade crosses maturity date
	 * this API can execute 5 minute interval of time
	 *  */
	@Scheduled(fixedRate = 300000)
	public ResponseEntity<Object> updateExpiredTrade() {
		tradeService.updateExpiredFlag();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
