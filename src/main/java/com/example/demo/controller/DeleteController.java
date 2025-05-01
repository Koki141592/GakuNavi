package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Report;
import com.example.demo.form.ReportDeleteForm;
import com.example.demo.service.DeleteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteController {
	
	private final DeleteService service;
	
	//削除のポストリクエスト
	@PostMapping("/delete-report")
	public String deleteReport(
			@Validated @ModelAttribute ReportDeleteForm form,
			BindingResult result) {
		
		//エラーが起きた時の処理
		if(result.hasErrors()) {
			
			throw new IllegalArgumentException("**deleteReport()**");
		}
		
		return "confirm-delete-report";
	}
	
	//削除確認のポストリクエストが送られてきたときの処理
	@PostMapping("/confirm-delete-report")
	public String confirmDeleteReport(
			@Validated @ModelAttribute ReportDeleteForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {
		
		//エラーが起きた時の処理
		if(result.hasErrors()) {
			throw new IllegalArgumentException("**confirmDeleteReport()**");
		}  
		
		//フォームからDTOに値を格納する処理
		Report report = new Report();
		report.setReportId(form.getReportId());
		report.setStudentId(form.getStudentId());
		report.setSubject(form.getSubject());
		report.setField(form.getField());
		report.setDate(form.getDate());
		report.setRating(form.getRating());
		report.setComment(form.getComment());
		
		service.delete(report);
		
		//フラッシュスコープに文字列を格納する処理
		redirectAttributes.addFlashAttribute("msg", "レポート削除" );
		
		return "redirect:/complete";
	}
}
