package com.ds.groupware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.groupware.dto.UHDto;
import com.ds.groupware.repository.UHDao;

import javax.annotation.Resource;

@Service("uhService")
public class UHServiceImpl implements UHService{
	@Resource(name="uhDao")
	UHDao uhdao;

	@Override
	public List<UHDto> getList(UHDto uhdto) {
		return uhdao.getList(uhdto);
	}

	@Override
	public int getTotalCnt(UHDto uhdto) {
		return uhdao.getTotalCnt(uhdto);
	}

	@Override
	public void insert(UHDto uhdto) {
		uhdao.insert(uhdto);
		
	}

	@Override
	public List<UHDto> getList(String user_id) {
		return uhdao.getList(user_id);
	}
}
