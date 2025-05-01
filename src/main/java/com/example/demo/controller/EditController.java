package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Report;
import com.example.demo.form.ReportEditForm;
import com.example.demo.service.EditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {
	
	private final EditService service; 
	
	//レポートを入力する画面に遷移するための処理
	@PostMapping("/show-edit-form")
	public String showEditForm(@ModelAttribute ReportEditForm form, Model model) {
	    model.addAttribute("reportEditForm", form); // 明示的にModelへ追加
	    return "edit-report";
	}
	
	//入力内容を取得して表示する処理
	@PostMapping("/edit-report")
	public String editReport(
			@Validated @ModelAttribute  ReportEditForm form,
			BindingResult result) {
		
		//入力エラーがある場合は登録画面に戻す処理
		if (result.hasErrors()) {
			return "edit-report";
		}
		
		//正常な場合にレビュー登録画面に遷移する処理
		return "confirm-edit-report";
	}
	
	//確認画面を表示するための処理
	@PostMapping("/confirm-edit-report")
	public String confirmEditReport(
			@Validated ReportEditForm form,
			BindingResult result, 
			RedirectAttributes redirectAttributes) {
		
		//入力エラーがあるときはレビュー登録画面に戻す
		if(result.hasErrors()) {
			return "edit-report";
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
		
		service.edit(report);
		
		System.out.println("更新対象レポートID: " + report.getReportId());
		
		//メッセージをフラッシュスコープに格納して保持する
		redirectAttributes.addFlashAttribute("msg", "レビュー更新");
		
		return "redirect:/complete";
	}
}
