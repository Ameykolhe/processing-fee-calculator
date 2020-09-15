package com.ameykolhe.processing_fee_calculator.entity;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class Transaction {
	
	@CsvBindByName(column="External Transaction Id")
	private String externalTransactionId;
	
	@CsvBindByName(column="Client Id")
	private String clientId;
	
	@CsvBindByName(column="Security Id")
	private String securityId;
	
	@CsvBindByName(column="Transaction Type")
	private String transactionType;
	
	@CsvBindByName(column="Transaction Date")
	@CsvDate("MM-dd-yyyy")
	private Date transactionDate;
	
	@CsvBindByName(column="Market Value")
	private double marketValue;
	
	@CsvBindByName(column="Priority Flag")
	private String priorityFlag;
	
	public String getExternalTransactionId() {
		return externalTransactionId;
	}
	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSecurityId() {
		return securityId;
	}
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public double getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	public String getPriorityFlag() {
		return priorityFlag;
	}
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	
	@Override
	public boolean equals(Object obj) {

		Transaction other = (Transaction) obj;
		if (clientId == null) {
			if (other.clientId != null) {
				return false;
			}
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (securityId == null) {
			if (other.securityId != null) {
				return false;
			}
		} else if (!securityId.equals(other.securityId)) {
			return false;
		}
		if (transactionDate == null) {
			if (other.transactionDate != null) {
				return false;
			}
		} else if (!transactionDate.equals(other.transactionDate)) {
			return false;
		}
		
		if(transactionType.equals("BUY") && other.transactionType.equals("SELL")) {
			return true;
		}
		if(transactionType.equals("SELL") && other.transactionType.equals("BUY")) {
			return true;
		}
		if(transactionType.equals("DEPOSIT") && other.transactionType.equals("WITHDRAW")) {
			return true;
		}
		if(transactionType.equals("WITHDRAW") && other.transactionType.equals("DEPOSIT")) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "Transaction [externalTransactionId=" + externalTransactionId + ", clientId=" + clientId
				+ ", securityId=" + securityId + ", transactionType=" + transactionType + ", transactionDate="
				+ transactionDate + ", marketValue=" + marketValue + ", priorityFlag=" + priorityFlag + "]";
	}
}
