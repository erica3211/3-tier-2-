package com.ds.groupware.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ds.groupware.dto.UHDto;

@Repository("uhDao")
public class UHDaoImpl implements UHDao{
	@Autowired
	SqlSessionTemplate sm;

	@Override
	public List<UHDto> getList(UHDto uhdto) {
		return sm.selectList("UH_getList",uhdto);
	}

	@Override
	public int getTotalCnt(UHDto uhdto) {
		return sm.selectOne("UH_getTotalCnt",uhdto);
	}

	@Override
	public void insert(UHDto uhdto) {
		sm.insert("UH_insert", uhdto);
		
	}

	@Override
	public List<UHDto> getList(String user_id) {
		return sm.selectList("UH_getIDhobbynmList",user_id);
	}
	
	
}
