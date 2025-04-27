package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Report {
	
	//データの受け渡しの役割
	private Integer reportId;
	private Integer studentId;
	private String subject;
	private String field;
	private Date date;
	private Integer rating;
	private String comment;
}
