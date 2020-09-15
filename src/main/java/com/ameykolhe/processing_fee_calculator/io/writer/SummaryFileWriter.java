package com.ameykolhe.processing_fee_calculator.io.writer;

import java.io.IOException;
import java.util.List;

import com.ameykolhe.processing_fee_calculator.entity.Summary;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface SummaryFileWriter {
	
	public void writeFile(String fileName, List<Summary> summaryList) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException;

}
