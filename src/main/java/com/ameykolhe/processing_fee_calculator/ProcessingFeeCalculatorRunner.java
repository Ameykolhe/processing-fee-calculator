package com.ameykolhe.processing_fee_calculator;

import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ameykolhe.processing_fee_calculator.config.AppConfig;
import com.ameykolhe.processing_fee_calculator.entity.Summary;
import com.ameykolhe.processing_fee_calculator.entity.Transaction;
import com.ameykolhe.processing_fee_calculator.io.reader.TransactionFileReader;
import com.ameykolhe.processing_fee_calculator.io.writer.SummaryFileWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ProcessingFeeCalculatorRunner {

	public static void main(String[] args) {
		
		Options options = new Options();

        Option input = new Option("i", "input", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        
        String inputFilePath = cmd.getOptionValue("input");
        String outputFilePath = cmd.getOptionValue("output");
        
        String readerBeanId = inputFilePath.split("\\.")[1] + "TransactionReader";
        String writerBeanId = outputFilePath.split("\\.")[1]+ "SummaryFileWriter";


		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			
			TransactionFileReader reader = context.getBean(readerBeanId, TransactionFileReader.class);
			ProcessingFeeCalculator calculator = context.getBean(ProcessingFeeCalculator.class);
			SummaryFileWriter writer = context.getBean(writerBeanId, SummaryFileWriter.class);
			
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("                          Processing Fee Calculator");
			System.out.println("\n\n");
			System.out.println("---------  READING TRANSACTION FILE");
			
			List<Transaction> transactions = reader.readFile(inputFilePath);
			
			System.out.println("---------  CALCULATING TRANSACTION FEES");
			List<Summary> summaryList = calculator.calculateFee(transactions);
			
			System.out.println("---------  WRITING SUMMARY FILE");
			writer.writeFile(outputFilePath, summaryList);
			
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
