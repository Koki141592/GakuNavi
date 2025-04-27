package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Report;
import com.example.demo.form.ReportRegistForm;
import com.example.demo.service.RegistService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistController {
	
	private final RegistService service;
	
	//レポートを入力する画面に遷移するための処理
	@PostMapping("/show-report-form")
	public String showReportForm(@ModelAttribute ReportRegistForm form) {
		
		return "regist-report";
	}
	
	//確認画面から戻ってきたときの処理
	@PostMapping("/show-report-form-ret")
	public String showReportFormRet(@ModelAttribute ReportRegistForm form) {
		
		return "regist-report";
	}
	
	//入力内容を取得して表示する処理
	@PostMapping("/regist-report")
	public String registReport(
			@ModelAttribute("reportRegistForm") @Validated ReportRegistForm form,
			BindingResult result) {
		
		//入力エラーがある場合は登録画面に戻す処理
		if (result.hasErrors()) {
			return "regist-report";
		}
		
		//正常な場合にレビュー登録画面に遷移する処理
		return "confirm-regist-report";
	}
	
	//確認画面を表示するための処理
	@PostMapping("/confirm-regist-report")
	public String confirmRegistReport(
			@Validated ReportRegistForm form,
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		//入力エラーがあるときはレビュー登録画面に戻す
		if(result.hasErrors()) {
			return "regist-report";
		}
		
		Report report = new Report();
		report.setStudentId(form.getStudentId());
		report.setSubject(form.getSubject());
		report.setField(form.getField());
		report.setDate(form.getDate());
		report.setRating(form.getRating());
		report.setComment(form.getComment());
		service.regist(report);
		
		//メッセージをフラッシュスコープに格納して保持する
		redirectAttributes.addFlashAttribute("msg", "レビュー登録");
		
		return "redirect:/complete";
	}
}
