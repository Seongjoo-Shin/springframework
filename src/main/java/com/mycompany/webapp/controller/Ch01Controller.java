package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @Controller는 자동으로 객체를 생성한다
@RequestMapping("/ch01")
//@Log4j2
public class Ch01Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("실행");
//		log.info("실행");
		return "ch01/content";
	}
	
}
