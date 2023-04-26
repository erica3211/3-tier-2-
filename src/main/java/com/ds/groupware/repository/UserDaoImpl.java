package com.ds.groupware.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ds.groupware.dto.UserDto;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SqlSessionTemplate sm;
	
	@Override
	public List<UserDto> getList(UserDto dto) {
		return sm.selectList("User_getList",dto);
	}

	@Override
	public int getTotalCnt(UserDto dto) {
		return sm.selectOne("User_getTotalCnt",dto);
	}

	@Override
	public void insert(UserDto dto) {
		sm.insert("User_insert", dto);
		
	}

	@Override
	public UserDto getView(String user_id) {
		return sm.selectOne("User_getView",user_id);
	}

	@Override
	public void aprv_y(String user_id) {
		sm.update("User_Aprv_Y",user_id);
		
	}

	@Override
	public void delete(String user_id) {
		sm.delete("User_delete",user_id);
		
	}

	@Override
	public int getIdCheck(UserDto dto) {
		return sm.selectOne("User_idcheck",dto);
	}
	


}
