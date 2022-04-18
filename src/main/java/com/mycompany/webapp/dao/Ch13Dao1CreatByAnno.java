package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class Ch13Dao1CreatByAnno {
	public Ch13Dao1CreatByAnno() {
		log.info("실행");
	}
}
