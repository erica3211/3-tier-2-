package com.ds.groupware.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;
import com.ds.groupware.service.APIService;



@Controller
public class AdminController {
	@Resource(name = "apiservice")
	APIService service;
	

	// 관리자 기본화면
	@RequestMapping(value = "/admin")
	public String getList(UserDto dto, Model model) {
		model.addAttribute("getUserList", service.getUserList(dto));
		model.addAttribute("searchKey", service.getsearchKey(dto));
		return "admin";
	}

	//사용자 상세화면
		@RequestMapping(value = "/admin/{user_id}")
		public String getView(@PathVariable String user_id,HobbyDto hobbydto, UserDto dto,UHDto uhdto, DeptDto deptdto, Model model) {
			model.addAttribute("getDeptList", service.getDeptList(deptdto));
			model.addAttribute("getUserList", service.getUserList(dto));
			model.addAttribute("getHobbyList", service.getHobbyList(hobbydto));
			model.addAttribute("getView",service.getUserview(user_id, dto));
			model.addAttribute("getUHlist", service.getUHList(user_id, uhdto));
			model.addAttribute("searchKey", service.getsearchKey(dto));
			return "admin";
		}

		// 회원 등록 (승인요청 -> 승인)
		@RequestMapping(value = "/user_update/{user_id}")
		String user_aprv_y(@PathVariable String user_id, UserDto dto) {
			service.user_aprv_y(user_id, dto);
			return "redirect:/admin";
		}

		// 회원 삭제
		@RequestMapping(value = "/user_delete/{user_id}")
		String user_delete(@PathVariable String user_id) {
			service.user_delete(user_id);
			return "redirect:/admin";
		}

}
