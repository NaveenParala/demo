package com.app.pdf_report.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.pdf_report.model.City;
import com.app.pdf_report.service.I_City_Service;
import com.app.pdf_report.util.GeneratePdfReport;
import com.itextpdf.text.Header;

@Controller
public class CityController {
	
	@Autowired
	private I_City_Service  service;
	
	@RequestMapping(value = "/pdfReport",method = RequestMethod.GET,
			produces = MediaType.APPLICATION_PDF_VALUE )
	public ResponseEntity<InputStreamResource> citesReport(){
		
		List<City> cities=service.findAllCities();
		ByteArrayInputStream bis=GeneratePdfReport.citiReport(cities);
		HttpHeaders header=new HttpHeaders();
		header.add("Content-Disposition","inline;filename=citiesReport.pdf");

		return ResponseEntity
				.ok()
				.headers(header)
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));

	}

}
