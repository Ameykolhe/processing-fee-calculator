package com.ameykolhe.processing_fee_calculator;

import java.io.IOException;
import java.util.List;

import com.ameykolhe.processing_fee_calculator.entity.Summary;
import com.ameykolhe.processing_fee_calculator.entity.Transaction;
import com.ameykolhe.processing_fee_calculator.io.reader.TransactionCSVReader;
import com.ameykolhe.processing_fee_calculator.io.reader.TransactionFileReader;
import com.ameykolhe.processing_fee_calculator.io.writer.SummaryFileCSVWriter;
import com.ameykolhe.processing_fee_calculator.io.writer.SummaryFileWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ProcessingFeeCalculatorRunner {

	public static void main(String[] args) {
		TransactionFileReader reader = new TransactionCSVReader();
		ProcessingFeeCalculator calculator = new ProcessingFeeCalculator();
		SummaryFileWriter writer = new SummaryFileCSVWriter();
		
			List<Transaction> transactions;
			
			List<Summary> summaryList;
			
			try {
				
				System.out.println("----------------------------------------------------------------------------------");
				System.out.println("                          Processing Fee Calculator");
				System.out.println("\n\n");
				System.out.println("---------  READING TRANSACTION FILE");
				
				transactions = reader.readFile("Sample_Data_Fee_Calculator.csv");
				
				System.out.println("---------  CALCULATING TRANSACTION FEES");
				summaryList = calculator.calculateFee(transactions);
				
				System.out.println("---------  WRITING SUMMARY FILE");
				writer.writeFile("Summary_Report.csv", summaryList);
				
				System.out.println("\n\n");
				System.out.println("----------------------------------------------------------------------------------");
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (CsvDataTypeMismatchException e) {
				e.printStackTrace();
			} catch (CsvRequiredFieldEmptyException e) {
				e.printStackTrace();
			}
			
			
			
	}

}
