package com.ds.groupware.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ds.groupware.dto.UserDto;
import com.ds.groupware.repository.UserDao;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	UserDao userDao;

	@Override
	public List<UserDto> getList(UserDto dto) {
		return userDao.getList(dto);
	}

	@Override
	public int getTotalCnt(UserDto dto) {
		return userDao.getTotalCnt(dto);
	}

	@Override
	public void insert(UserDto dto) {
		userDao.insert(dto);
		
	}

	@Override
	public UserDto getView(String user_id) {
		return userDao.getView(user_id);
	}

	@Override
	public void aprv_y(String user_id) {
		userDao.aprv_y(user_id);
		
	}

	@Override
	public void delete(String user_id) {
		userDao.delete(user_id);
		
	}

	@Override
	public int getIdCheck(UserDto dto) {
		return userDao.getIdCheck(dto);
	}
	
}
