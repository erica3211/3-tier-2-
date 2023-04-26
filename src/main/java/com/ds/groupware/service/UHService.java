package com.ds.groupware.service;

import java.util.List;

import com.ds.groupware.dto.UHDto;

public interface UHService {
	List<UHDto> getList(UHDto uhdto);
	int getTotalCnt(UHDto uhdto);
	void insert(UHDto uhdto);
	List<UHDto> getList(String user_id);
}
