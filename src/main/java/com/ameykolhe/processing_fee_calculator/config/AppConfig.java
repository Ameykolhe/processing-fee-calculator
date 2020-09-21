package com.ameykolhe.processing_fee_calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ameykolhe.processing_fee_calculator.ProcessingFeeCalculator;
import com.ameykolhe.processing_fee_calculator.io.reader.CSVTransactionReader;
import com.ameykolhe.processing_fee_calculator.io.reader.XLSXTransactionReader;
import com.ameykolhe.processing_fee_calculator.io.writer.CSVSummaryFileWriter;
import com.ameykolhe.processing_fee_calculator.io.writer.XLSXSummaryFileWriter;

@Configuration
@ComponentScan("com.ameykolhe.processing_fee_calculator")
public class AppConfig {
	
	@Bean
	public XLSXTransactionReader xlsxTransactionReader() {
		return new XLSXTransactionReader();
	}
	
	@Bean
	public CSVTransactionReader csvTransactionReader() {
		return new CSVTransactionReader();
	}
	
	@Bean
	public XLSXSummaryFileWriter xlsxSummaryFileWriter() {
		return new XLSXSummaryFileWriter();
	}
	
	@Bean
	public CSVSummaryFileWriter csvSummaryFileWriter() {
		return new CSVSummaryFileWriter();
	}
	
	@Bean
	public ProcessingFeeCalculator averageIncomeCalculator() {
		return new ProcessingFeeCalculator();
	}
}
