package com.ameykolhe.processing_fee_calculator.io.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ameykolhe.processing_fee_calculator.entity.Summary;

public class XLSXSummaryFileWriter implements SummaryFileWriter {

	@Override
	public void writeFile(String fileName, List<Summary> summaryList) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Average Income Summary");
		
		Row row;
		row = sheet.createRow(0);
		row.createCell(0).setCellValue("Client Id");
		row.createCell(1).setCellValue("Transaction Type");
		row.createCell(2).setCellValue("Transaction Date");
		row.createCell(3).setCellValue("Priority");
		row.createCell(4).setCellValue("Processing Fee");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		for(int i = 1; i <= summaryList.size(); i++) {
			row = sheet.createRow(i);
			Summary record = summaryList.get(i - 1);
			
			row.createCell(0).setCellValue(record.getClientId());
			row.createCell(1).setCellValue(record.getTransactionType());
			row.createCell(2).setCellValue(dateFormat.format(record.getDate()));
			row.createCell(3).setCellValue(record.getPriorityFlag());
			row.createCell(4).setCellValue(record.getProcessingFee());
		}
		
		
		FileOutputStream fos = new FileOutputStream(fileName);
		
		workbook.write(fos);
		
		workbook.close();	
		fos.close();


	}

}
