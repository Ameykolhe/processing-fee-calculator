package com.ameykolhe.processing_fee_calculator.io.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ameykolhe.processing_fee_calculator.entity.Summary;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class SummaryFileCSVWriter implements SummaryFileWriter {

	@Override
	public void writeFile(String fileName, List<Summary> summaryList) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		Path myPath = Paths.get(fileName);
		var writer = Files.newBufferedWriter(myPath, StandardCharsets.UTF_8);
		
		writer.write("Client Id,Transaction Type,Transaction Date,Priority,Processing Fee\n");
		
		StatefulBeanToCsv<Summary> beanToCsv = new StatefulBeanToCsvBuilder<Summary>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        beanToCsv.write(summaryList);
        
        writer.close();
		
	}


}
