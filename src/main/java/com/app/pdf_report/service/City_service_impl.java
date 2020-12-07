package com.app.pdf_report.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.pdf_report.model.City;
import com.app.pdf_report.repo.CityRepository;
@Service
public class City_service_impl implements I_City_Service {

	@Autowired
	private  CityRepository  cityRepository;
	
	@Override
	public List<City> findAllCities() {
		List<City> list=new ArrayList<City>();
		list=(List<City>) cityRepository.findAll();
		return list;
	}

}
