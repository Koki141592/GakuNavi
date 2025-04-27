package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	//リダイレクトされたときにGETリクエストでこの処理が行われる
	@GetMapping("/complete")
	private String complete() {
		
		return "complete";
	}
}
