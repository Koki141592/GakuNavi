package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Report;

public interface ReportRepository {
	
	void add(Report report);
	
	List<Report> selectByStudentId(int studentId);

	void update(Report report);
	
	void delete(Report report);
}
