package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportListServiceImpl implements ReportListService {
	
	private final ReportRepository repository;

	@Override
	public List<Report> findByStudentId(int studentId) {
		
		List<Report> list = repository.selectByStudentId(studentId);
		
		return list;
	}

}
