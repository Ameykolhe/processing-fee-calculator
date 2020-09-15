package com.ameykolhe.processing_fee_calculator.entity;

import java.util.Comparator;
import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

public class Summary implements Comparable<Summary>{
	
	// @CsvBindByName(column="Client Id")
	@CsvBindByPosition(position=0)
	private String clientId;
	
	// @CsvBindByName(column="Transaction Type")
	@CsvBindByPosition(position=1)
	private String transactionType;
	
	// @CsvBindByName(column="Transaction Date")
	@CsvBindByPosition(position=2)
	@CsvDate("MM-dd-yyyy")
	private Date date;

	// @CsvBindByName(column="Priority")
	@CsvBindByPosition(position=3)
	private String priorityFlag;
	
	// @CsvBindByName(column="Processing Fee")
	@CsvBindByPosition(position=4)
	private double processingFee;
	
	public Summary(String clientId, String transactionType, Date date, String priorityFlag) {
		super();
		this.clientId = clientId;
		this.transactionType = transactionType;
		this.date = date;
		this.priorityFlag = priorityFlag;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(double processingFee) {
		this.processingFee = processingFee;
	}

	@Override
	public String toString() {
		return "Summary [clientId=" + clientId + ", transactionType=" + transactionType + ", date=" + date
				+ ", priorityFlag=" + priorityFlag + ", processingFee=" + processingFee + "]";
	}
	
	@Override
	public int compareTo(Summary o) {
	    return Comparator.comparing(Summary::getClientId)
	      .thenComparing(Summary::getTransactionType)
	      .thenComparing(Summary::getDate, Comparator.nullsLast(Comparator.naturalOrder()))
	      .thenComparing(Summary::getPriorityFlag)
	      .compare(this, o);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Summary)) {
			return false;
		}
		Summary other = (Summary) obj;
		if (clientId == null) {
			if (other.clientId != null) {
				return false;
			}
		} else if (!clientId.equals(other.clientId)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (priorityFlag == null) {
			if (other.priorityFlag != null) {
				return false;
			}
		} else if (!priorityFlag.equals(other.priorityFlag)) {
			return false;
		}
		if (transactionType == null) {
			if (other.transactionType != null) {
				return false;
			}
		} else if (!transactionType.equals(other.transactionType)) {
			return false;
		}
		return true;
	}
}
