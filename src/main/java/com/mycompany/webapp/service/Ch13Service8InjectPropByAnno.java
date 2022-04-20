package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service8InjectPropByAnno {
	
	@Value("${service.prop1}") private int prop1;
	private double prop2;
	private boolean prop3;
	private String prop4;
	
	
//	public Ch13Service8InjectPropByAnno() {
//		log.info("실행1 : " + toString());
//	}
	
	public Ch13Service8InjectPropByAnno(@Value("${service.prop2}") double prop2, @Value("${service.prop3}") boolean prop3) {
		this.prop2 = prop2;
		this.prop3 = prop3;
		log.info("실행2 : " + toString());
	}
	
	@Value("${service.prop4}") 
	public void setProp4(String prop4) {
		this.prop4 = prop4;
		log.info("실행 : " + toString());
	}
	
	@Override
    public String toString() {
	    String str = "{";
	    str += "prop1:" + prop1 +", ";
	    str += "prop2:" + prop2 +", ";
	    str += "prop3:" + prop3 +", ";
	    str += "prop4:" + prop4;
	    str += "}";
	    return str;
	}
	
	
}
