package com.ds.groupware.service;

import java.util.List;

import com.ds.groupware.dto.HobbyDto;

public interface HobbyService {
	List<HobbyDto> getList(HobbyDto dto);
}
