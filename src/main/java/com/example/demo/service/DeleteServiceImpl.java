package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteServiceImpl implements DeleteService {
	
	private final ReportRepository repository;

	@Override
	public void delete(Report report) {
		
		repository.delete(report);
	}

}
