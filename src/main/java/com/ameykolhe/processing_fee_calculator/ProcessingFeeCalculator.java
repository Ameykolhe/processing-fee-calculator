package com.ameykolhe.processing_fee_calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ameykolhe.processing_fee_calculator.entity.Summary;
import com.ameykolhe.processing_fee_calculator.entity.Transaction;

public class ProcessingFeeCalculator {

	public List<Summary> calculateFee(List<Transaction> transactions) {
		Map<Summary, Integer> summaryMap = new TreeMap<>();
		
		transactions.forEach( transaction -> {
			int processingFee = 0;
			
			if(transactions.contains(transaction)) {
				processingFee = 10;
			}
			else if (transaction.getPriorityFlag().contentEquals("Y")){
				processingFee = 500;
			}
			else if (transaction.getTransactionType().equals("SELL") || transaction.getTransactionType().equals("WITHDRAW")) {
				processingFee = 100;
			}
			else if (transaction.getTransactionType().equals("BUY") || transaction.getTransactionType().equals("DEPOSIT")) {
				processingFee = 100;
			}
			Summary summary = new Summary(
					transaction.getClientId(),
					transaction.getTransactionType(),
					transaction.getTransactionDate(),
					transaction.getPriorityFlag()
				);
			
			if (summaryMap.containsKey(summary)) {
				summaryMap.put(summary, summaryMap.get(summary) + processingFee);
			}
			else {
				summaryMap.put(summary, processingFee);
			}
			
		});
		
		List<Summary> summaryList = new ArrayList<>();
		
		summaryMap.forEach( (key, value) -> {
			key.setProcessingFee(value);
			summaryList.add(key);
		});
		
		return summaryList;
	}
}
