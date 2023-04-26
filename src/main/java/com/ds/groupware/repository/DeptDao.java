package com.ds.groupware.repository;

import java.util.List;

import com.ds.groupware.dto.DeptDto;

public interface DeptDao {
	List<DeptDto> getList(DeptDto dto);
}
