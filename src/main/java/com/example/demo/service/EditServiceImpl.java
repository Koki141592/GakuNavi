package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditServiceImpl implements EditService {
	
	private final ReportRepository repository;

	@Override
	public void edit(Report report) {
		
        repository.update(report);
	}

}
