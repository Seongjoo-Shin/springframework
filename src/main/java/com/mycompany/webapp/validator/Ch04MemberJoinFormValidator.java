package com.mycompany.webapp.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		log.info("실행");
		Ch04Member member = (Ch04Member) target;
		
		// mid검사
		if(member.getMid() == null || member.getMid().trim().equals("")) {
			errors.rejectValue("mid", "errors.mid.required", "mid는 필수 입력 사항입니다."); // ko or en은 스프링이 브라우저를 확인하고 정해줌
		} else {
			if(member.getMid().length() < 4) {
				errors.rejectValue("mid", "errors.mid.minlength", new Object[] {4} , "mid를 최소 4자 이상입력하세요.");
			}
		}
		
		// mpassword검사
		if(member.getMpassword() == null || member.getMpassword().trim().equals("")) {
			errors.rejectValue("mpassword", "errors.mpassword.required", "mpassword는 필수 입력 사항입니다.");
		} else {
			if(member.getMpassword().length() < 8) {
				errors.rejectValue("mpassword", "errors.mpassword.minlength", new Object[] {8}, "mpassword를 최소 8자 이상입력하세요.");
			}
		}
		
		// memail검사
		if(member.getMemail() == null || member.getMemail().trim().equals("")) {
			errors.rejectValue("memail", "errors.memail.required", "memail는 필수 입력 사항입니다.");
		} else {
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(member.getMemail());
			if(!matcher.matches()) {
				errors.rejectValue("memail", "errors.memail.invalid", "meamil은 이메일 형식이 아닙니다.");
			}
		}
		//mtel 검사
		if(member.getMtel() == null || member.getMtel().trim().equals("")) {
			errors.rejectValue("mtel", "errors.mtel.required", "mtel은 필수 입력 사항입니다.");
		} else {
			String regex = "^\\d{3}-\\d{3,4}-\\d{4}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(member.getMtel());
			if(!matcher.matches()) {
				errors.rejectValue("mtel", "errors.mtel.invalid", "mtel은 전화번호 형식이 아닙니다.");
			}
		}
	}
}
