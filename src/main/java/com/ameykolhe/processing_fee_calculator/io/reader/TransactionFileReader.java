package com.ameykolhe.processing_fee_calculator.io.reader;

import java.io.IOException;
import java.util.List;

import com.ameykolhe.processing_fee_calculator.entity.Transaction;

public interface TransactionFileReader {

	public List<Transaction> readFile(String fileName) throws IOException;
}
