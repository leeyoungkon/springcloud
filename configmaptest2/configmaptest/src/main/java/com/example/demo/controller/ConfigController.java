package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/configmaptest")
public class ConfigController {
	
	//@Value("${APP_NAME}")
	private String appName="korea";

	//@Value("${APP_VERSION}")
	private String appVersion="1.0.0";
	
	@GetMapping("/config")
	public String getConfigInfo(Model model) {
		model.addAttribute("appName", appName);
        model.addAttribute("appVersion", appVersion);
        return "configView"; // configView.html을 렌더링
	}
	
	@GetMapping("/config2")
	public String getConfigInfo2(Model model) {
		model.addAttribute("appName", appName);
        model.addAttribute("appVersion", appVersion);
        return "configView2"; // configView.html을 렌더링
	}
	

}
