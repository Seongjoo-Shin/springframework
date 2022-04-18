package com.mycompany.webapp.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch12")
@Log4j2
public class Ch12Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch12/content";
	}
	
	@RequestMapping("/fileList")
	public String fileList() throws IOException {
		log.info("실행");
		return "ch12FileListView";
	}
	
	@RequestMapping("/fileDownload")
	public String fileDownload(
			@ModelAttribute("fileName") String fileName,
			@ModelAttribute("userAgent") @RequestHeader("User-Agent") String userAgent) { //, Model model) {
		log.info("실행");
//		model.addAttribute("fileName", fileName);
		return "ch12FileDownloadView";
	}
}
