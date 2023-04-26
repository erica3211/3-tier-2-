package com.ds.groupware.repository;

import java.util.List;

import com.ds.groupware.dto.UHDto;

public interface UHDao {
	List<UHDto> getList(UHDto uhdto);
	List<UHDto> getList(String user_id);
	int getTotalCnt(UHDto uhdto);
	public void insert(UHDto uhdto);
}
