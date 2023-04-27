package com.ds.groupware.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;
import com.ds.groupware.service.DeptService;
import com.ds.groupware.service.HobbyService;
import com.ds.groupware.service.UHService;
import com.ds.groupware.service.UserService;



@RestController
@RequestMapping(value = "/api")
public class ApiController {
	@Resource(name = "userService")
	UserService userservice;
	@Resource(name = "deptService")
	DeptService deptservice;
	@Resource(name = "hobbyService")
	HobbyService hobbyservice;
	@Resource(name = "uhService")
	UHService uhservice;
	
	//admin화면
	@RequestMapping(value = "/userlist")
	public List<UserDto> getUserList(UserDto dto) {
		
		List<UserDto> userlist = userservice.getList(dto);
		return userlist;
	}
	
	@RequestMapping(value = "/total")
	public int getTotalList(UserDto dto) {
		
		int total = userservice.getTotalCnt(dto);
		return total;
	}
	@RequestMapping(value = "/searchkey")
	public String getSearchKey(UserDto dto) {
		
		String searchkey = dto.getSearchKey();
		System.out.println("bt:"+searchkey);
		return searchkey;
	}

	//사용자 상세화면
	@RequestMapping(value = "userview")
	public UserDto getUserView(String user_id) {
		UserDto userview = userservice.getView(user_id);
		return userview;
	}
	
	@RequestMapping(value = "deptlist")
	public List<DeptDto> getDeptlist(DeptDto D_dto) {
		List<DeptDto> deptlist = deptservice.getList(D_dto);
		return deptlist;
	}
	@RequestMapping(value = "hobbylist")
	public List<HobbyDto> getHobbylist(HobbyDto H_dto) {
		List<HobbyDto> hobbylist = hobbyservice.getList(H_dto);
		return hobbylist;
	}
	@RequestMapping(value = "uh")
	public StringBuffer getUHlist(String user_id,HobbyDto H_dto) {
		List<UHDto> uhlist = uhservice.getList(user_id);
		StringBuffer uh = new StringBuffer();
		for (int i = 0; i < uhlist.size(); i++) {
			uh.append(uhlist.get(i).getHobby_cd());
			if (i < uhlist.size() - 1) {
				uh.append(",");
			} else if (uhlist.size() == 1) {
				
			}
		}
		System.out.println(uhlist);
		System.out.println(uh);
		return uh;
	}

	@RequestMapping(value = "user_save")
	public ResponseEntity<UserDto> UserSave(@RequestBody UserDto dto) {
		userservice.insert(dto);
		return ResponseEntity.ok(dto);
	}
	@RequestMapping(value = "uh_save")
	public ResponseEntity<UHDto> UHSave(@RequestBody UHDto uhdto) {
		uhservice.insert(uhdto);
		return ResponseEntity.ok(uhdto);
	}
	// 회원 삭제
	@RequestMapping(value = "/user_delete/{user_id}")
	ResponseEntity<Void> user_delete(@PathVariable String user_id) {
		userservice.delete(user_id);
		return ResponseEntity.noContent().build();
	}
	// 회원 등록 (승인요청 -> 승인)
	@RequestMapping(value = "/user_update/{user_id}")
	ResponseEntity<UserDto> user_aprv_y(@PathVariable String user_id, @RequestBody UserDto dto) {
		userservice.aprv_y(user_id);
		return ResponseEntity.ok().body(dto);
	}
	// 아이디 체크
	@ResponseBody
	@RequestMapping(value = "idcheck")
	public boolean idCheck(@RequestBody UserDto dto) {
		int userIdCount = userservice.getIdCheck(dto);
		if (userIdCount > 0) {
            return true;
        } else {
            return false;
        }
    }
	


}
