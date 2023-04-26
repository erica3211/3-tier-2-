package com.ds.groupware.service;

import java.util.List;

import com.ds.groupware.dto.DeptDto;

public interface DeptService {
	List<DeptDto> getList(DeptDto dto);
}
