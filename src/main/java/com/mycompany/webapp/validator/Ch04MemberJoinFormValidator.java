package com.mycompany.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberJoinFormValidator implements Validator{
	
	// 유효성 검사가 가능한 객체인지를 조사하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("실행");
		// isAssignableForm() -> Ch04Member || Ch04Member의 자식인 경우에 true
		boolean result = Ch04Member.class.isAssignableFrom(clazz);
		return result;
	}
	
	// supports가 true인 경우에만 validate를 실행
	@Override
	public void validate(Object target, Errors errors) {
		
	}
}
