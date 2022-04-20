package com.mycompany.webapp.service;

import javax.annotation.Resource;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	
	// Service를 사용하면서 나오는 결과이므로 여기서 작성
	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED 
	}
	
	public enum LoginResult {
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FAIL
	}
	
	@Resource
	private Ch14MemberDao memberDao;
	
	public JoinResult join(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if(dbMember == null) {
			// 암호화
			PasswordEncoder passwordEcnoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			member.setMpassword(passwordEcnoder.encode(member.getMpassword()));
			int result = memberDao.insert(member);	
			return JoinResult.SUCCESS;
		} else {
			return JoinResult.DUPLICATED;
		}
	}

	public LoginResult login(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if(dbMember == null) {
			return LoginResult.FAIL_MID;
		} else {
			PasswordEncoder passwordEcnoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if(passwordEcnoder.matches(member.getMpassword(), dbMember.getMpassword())) { // 입력 pwd와 db pwd를 비교하는 방법
				return LoginResult.SUCCESS;
			} else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}
	}
}
