package com.ameykolhe.processing_fee_calculator.io.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ameykolhe.processing_fee_calculator.entity.Transaction;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class TransactionCSVReader implements TransactionFileReader {

	@Override
	public List<Transaction> readFile(String fileName) throws IOException {
		Path myPath = Paths.get(fileName);
		BufferedReader br = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);

        HeaderColumnNameMappingStrategy<Transaction> strategy
                = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Transaction.class);

        CsvToBean<Transaction> csvToBean = new CsvToBeanBuilder<Transaction>(br)
                .withMappingStrategy(strategy)
                .withIgnoreLeadingWhiteSpace(true)
                .build();

        List<Transaction> transactions = csvToBean.parse();
        
        br.close();

        return transactions;
        
	}

}
