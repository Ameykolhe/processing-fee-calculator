package com.ameykolhe.processing_fee_calculator.io.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ameykolhe.processing_fee_calculator.entity.Transaction;

public class XLSXTransactionReader implements TransactionFileReader {

	@Override
	public List<Transaction> readFile(String fileName) throws IOException {
		List<Transaction> incomeRecords = new ArrayList<>();
		
		FileInputStream fis = new FileInputStream(fileName);
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		
		Iterator<Row> rowIterator = mySheet.iterator();
		
		rowIterator.next();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Transaction record = new Transaction();
			
			Cell externalTransactionId = row.getCell(0);
			Cell clientId = row.getCell(1);
			Cell securityId = row.getCell(2);
			Cell transactionType = row.getCell(3);
			Cell transactionDate = row.getCell(4);
			Cell marketValue = row.getCell(5);
			Cell priorityValue = row.getCell(6);

			record.setExternalTransactionId(externalTransactionId.getStringCellValue());
			record.setClientId(clientId.getStringCellValue());
			record.setSecurityId(securityId.getStringCellValue());
			record.setTransactionType(transactionType.getStringCellValue());
			record.setTransactionDate(transactionDate.getDateCellValue());
			record.setMarketValue(marketValue.getNumericCellValue());
			record.setPriorityFlag(priorityValue.getStringCellValue());
			
			incomeRecords.add(record);
		}
		
		myWorkBook.close();
		fis.close();
		return incomeRecords;
	}

}
