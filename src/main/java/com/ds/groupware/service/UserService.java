package com.ds.groupware.service;

import java.util.List;

import com.ds.groupware.dto.UserDto;

public interface UserService {
	List<UserDto> getList(UserDto dto);
	UserDto getView(String user_id);
	int getTotalCnt(UserDto dto);
	void insert(UserDto dto);
	void aprv_y(String user_id);
	void delete(String user_id);
	int getIdCheck(UserDto dto);
}
