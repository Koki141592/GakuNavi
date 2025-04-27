package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Report;
import com.example.demo.form.ReportSearchForm;
import com.example.demo.service.ReportListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReportListController {
	
	private final ReportListService service;
	
	@PostMapping("/search-report")
	private String searchReport(@ModelAttribute ReportSearchForm form,
			Model model) {
		
		List<Report> list = service.findByStudentId(form.getStudentId());
		
				
		if (list.size() > 0) {
			model.addAttribute("reportList", list);
		}
		
		return "report-list";
	}
}
