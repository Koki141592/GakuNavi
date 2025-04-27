package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Report;

public interface ReportListService {
	
	List<Report> findByStudentId(int studentId);
}
