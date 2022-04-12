package com.mycompany.webapp.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mycompany.webapp.dto.Ch04Member;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Ch04MemberLoginFormValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		log.info("ㅎㅇ");
		boolean result = Ch04Member.class.isAssignableFrom(clazz);
		return result;
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		log.info("ㅎㅇㅎㅇ");
		Ch04Member member = (Ch04Member) target;
		
		// mid검사
		if(member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", null, "mid는 필수 입력 사항입니다.");
		} else {
			if(member.getMid().length() < 4) {
				errors.rejectValue("mid", null, "mid를 최소 4자 이상입력하세요. ^^");
			}
		}
		
		// mpassword검사
		if(member.getMpassword() == null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", null, "mpassword는 필수 입력 사항입니다.");
		} else {
			if(member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", null, "mpassword를 최소 8자 이상입력하세요. ^^");
			}
		}
	}
	
}
