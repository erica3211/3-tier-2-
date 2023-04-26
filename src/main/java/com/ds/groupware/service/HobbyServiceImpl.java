package com.ds.groupware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.repository.HobbyDao;

import javax.annotation.Resource;

@Service("hobbyService")
public class HobbyServiceImpl implements HobbyService{
	@Resource(name="hobbyDao")
	HobbyDao dao;

	@Override
	public List<HobbyDto> getList(HobbyDto dto) {
		return dao.getList(dto);
	}

	
}
