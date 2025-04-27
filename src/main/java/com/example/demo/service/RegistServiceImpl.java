package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistServiceImpl implements RegistService {

	private final ReportRepository repository;
	
	@Override
	public void regist(Report report) {
		
		repository.add(report);
	}

}
