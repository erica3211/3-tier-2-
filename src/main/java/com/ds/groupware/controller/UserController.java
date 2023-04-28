
package com.ds.groupware.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;
import com.ds.groupware.service.APIService;

import javax.annotation.Resource;

//@RestController //json형식으로 가져옴
@Controller
public class UserController {
	@Resource(name = "apiservice")
	APIService service;



	// 등록 페이지로 이동
	@RequestMapping(value = "/user/user_write")
	String user_write(UserDto dto, DeptDto deptdto, UHDto UHdto, HobbyDto hobbydto, Model model) {
		UserDto userdto = new UserDto();
		UHDto uhdto = new UHDto();
		model.addAttribute("getUserList", service.getUserList(dto));
		model.addAttribute("UserDto", userdto);
		model.addAttribute("UHDto", uhdto);
		model.addAttribute("getDeptList", service.getDeptList(deptdto));
		model.addAttribute("id",dto.getId());
		model.addAttribute("getHobbyList", service.getHobbyList(hobbydto));

		return "user_write";
	}

	// 회원 정보 저장
	@RequestMapping(value = "/user/user_save")
	String user_save(UserDto dto, UHDto uhdto) {
		if (dto.getIduncheck().equals("N")) {
			return "redirect:/user/user_write";
		}
		service.user_save(dto, uhdto);
		return "redirect:/";
	}
	
	// 아이디 확인
	@ResponseBody
	@RequestMapping(value = "/user/idcheck")
	HashMap<String, Object> idcheck(UserDto dto) {
		// 결과값을 hashmap에 넣어서 반환
		
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", service.idcheck(dto));
        return map;
	}


}