package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller("ch13Controller")
@RequestMapping("/ch13")
@Log4j2
public class Ch13Controller {
	
	public Ch13Controller() {
		log.info("실행");
	}
	
	@RequestMapping("/content")
	public String content() {
		return "ch13/content";
	}
}
