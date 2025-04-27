package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Student;
import com.example.demo.form.StudentSearchForm;
import com.example.demo.service.StudentListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StudentListController {
	
	private final StudentListService service;
	
	//GETリクエストで生徒の検索画面を表示(検索結果は表示されていない)
	@GetMapping("/top-page")
	private String studentList(@ModelAttribute StudentSearchForm form) {
		return "student-list";
	}
	
	
	//検索されたときに結果を表示する処理
	@PostMapping("/student-search")
	private String studentSearch(
			@ModelAttribute StudentSearchForm form,
			Model model) {
		
		List<Student> list = service.findByNameWildCard(form.getStudentName());
		
		model.addAttribute("studentList", list);		
		
		return "student-list";
	}
}
