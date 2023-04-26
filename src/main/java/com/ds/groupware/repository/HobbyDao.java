package com.ds.groupware.repository;

import java.util.List;

import com.ds.groupware.dto.HobbyDto;

public interface HobbyDao {
	List<HobbyDto> getList(HobbyDto dto);
}
