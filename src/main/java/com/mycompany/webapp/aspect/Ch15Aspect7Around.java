package com.mycompany.webapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect7Around {

	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();

		Object result = joinPoint.proceed();
		
		long end = System.nanoTime();
		long howLong = end - start;
		
		// proceed에서 실행한 메소드 이름 얻기
		String methodName = joinPoint.getSignature().toShortString();
		
		log.info(methodName + "의 실행 시간 : " + howLong + "ns");
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		
		return result;
	}
}
