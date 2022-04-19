package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao1CreateByAnno;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service4ByInjectAnno {
	
	public Ch13Service4ByInjectAnno() {
		log.info("실행");
	}
	
	// 타입으로 주입
	@Resource
	private Ch13Dao1CreateByAnno ch13Dao;
	
	@Resource
	public void setCh13Dao1(Ch13Dao1CreateByAnno ch13Dao) {
		log.info("타입으로 주입 setter");
		this.ch13Dao = ch13Dao;
	}
	
	// 이름으로 주입
	/*@Resource(name="ch13Dao1")
	private Ch13Dao1CreateByAnno ch13Dao;
	
	@Resource(name="ch13Dao1")
	public void setCh13Dao1(Ch13Dao1CreateByAnno ch13Dao) {
		log.info("이름으로 주입 setter");
		this.ch13Dao = ch13Dao;
	}*/
}
