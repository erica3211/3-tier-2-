package com.ds.groupware.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;
import com.ds.groupware.service.DeptService;
import com.ds.groupware.service.HobbyService;
import com.ds.groupware.service.UHService;
import com.ds.groupware.service.UserService;



@Controller
public class AdminController {
	@Resource(name = "userService")
	UserService userservice;
	@Resource(name = "deptService")
	DeptService deptservice;
	@Resource(name = "hobbyService")
	HobbyService hobbyservice;
	@Resource(name = "uhService")
	UHService uhservice;

	// 관리자 기본화면
	@RequestMapping(value = "/admin")
	public String getList(UserDto dto, DeptDto deptdto, Model model) {
		
		List<UserDto> userlist = userservice.getList(dto);
		int total = userservice.getTotalCnt(dto);
		model.addAttribute("getUserList", userlist);
		model.addAttribute("getTotalCnt", total);
		System.out.println("데이터 총 개수 :" + total);
		System.out.println("admin");
		model.addAttribute("searchKey", dto.getSearchKey());
		return "admin";
	}

	//사용자 상세화면
	@RequestMapping(value = "/admin/{user_id}")
	public String getView(@PathVariable String user_id, UHDto UHdto, UserDto dto, HobbyDto H_dto, DeptDto D_dto,
			Model model) {
		UserDto userview = userservice.getView(user_id);
		List<UserDto> userlist = userservice.getList(dto);
		List<DeptDto> deptlist = deptservice.getList(D_dto);
		List<HobbyDto> hobbylist = hobbyservice.getList(H_dto);
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

		model.addAttribute("getDeptList", deptlist);
		model.addAttribute("getUserList", userlist);
		model.addAttribute("getHobbyList", hobbylist);
		model.addAttribute("getView", userview);
		model.addAttribute("getUHlist", uh);
		model.addAttribute("searchKey", dto.getSearchKey());
		return "admin";
	}

	// 회원 등록 (승인요청 -> 승인)
	@RequestMapping(value = "/user_update/{user_id}")
	String user_aprv_y(@PathVariable String user_id) {
		userservice.aprv_y(user_id);
		System.out.println("등록완료");
		return "redirect:/admin";
	}

	// 회원 삭제
	@RequestMapping(value = "/user_delete/{user_id}")
	String user_delete(@PathVariable String user_id) {
		userservice.delete(user_id);
		System.out.println("삭제완료");
		return "redirect:/admin";
	}

}
