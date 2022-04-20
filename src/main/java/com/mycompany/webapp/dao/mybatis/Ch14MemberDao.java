package com.mycompany.webapp.dao.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Ch14Member;

@Mapper
public interface Ch14MemberDao {
	public int insert(Ch14Member member);
	public Ch14Member selectByMid(String mid);
}
