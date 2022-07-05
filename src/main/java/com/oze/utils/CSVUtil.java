package com.oze.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import com.oze.entity.Patient;

public class CSVUtil {
	
	public static ByteArrayInputStream patientRecordsToCsv(List<Patient> patients) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try(ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);){
			for(Patient patient: patients) {
				List<String> data = Arrays.asList(
							String.valueOf(patient.getId()),
							String.valueOf(patient.getName()),
							String.valueOf(patient.getAge()),
							String.valueOf(patient.getLastVisitDate())
						);
				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		}catch(IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
		
		
	}

}
