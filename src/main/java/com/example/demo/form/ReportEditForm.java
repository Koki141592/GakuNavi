package com.example.demo.form;

import java.sql.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ReportEditForm {
	
	@NotNull(message="入力してください。")
	@Min(value=1, message="正の整数を入力してください。")
	private Integer reportId;
	
	@NotNull(message="入力してください")
	@Min(value=1, message="1-10で指定してください")
	@Max(value=10, message="1-10で指定してください")
	private Integer studentId;
	
	private String studentName;
	
	@Size(min=1, max=10, message="教科名入力してください")
	private String subject;
	
	@Size(min=1, max=32, message="分野を入力してください")
	private String field;
	
	@Past(message="今日以前の日付を入力してください")
	private Date date;
	
	@NotNull(message="入力してください")
	@Min(value=1, message="1-5で指定してください")
	@Max(value=5, message="1-5で指定してください")
	private Integer rating;
	
	@Size(min=1, max=150, message="1字から150字で書いてください")
	private String comment;

}
