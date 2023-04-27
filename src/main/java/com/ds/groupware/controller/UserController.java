
package com.ds.groupware.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;
import com.ds.groupware.service.DeptService;
import com.ds.groupware.service.HobbyService;
import com.ds.groupware.service.UHService;
import com.ds.groupware.service.UserService;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

//@RestController //json형식으로 가져옴
@Controller
public class UserController {
	
	private final RestTemplate restTemplate = new RestTemplate();



	// 등록 페이지로 이동
	@RequestMapping(value = "/user/user_write")
	String user_write(UserDto dto, DeptDto D_dto, UHDto UHdto, HobbyDto H_dto, Model model) {
		List<Data> userlist = restTemplate.getForObject("http://localhost:8081/api/userlist", List.class);
		List<Data> deptlist = restTemplate.getForObject("http://localhost:8081/api/deptlist", List.class);
		List<Data> hobbylist = restTemplate.getForObject("http://localhost:8081/api/hobbylist", List.class);
		UserDto userdto = new UserDto();
		UHDto uhdto = new UHDto();
		//System.out.println(userlist.get(1).getId());
		model.addAttribute("getUserList", userlist);
		model.addAttribute("UserDto", userdto);
		model.addAttribute("UHDto", uhdto);
		model.addAttribute("getDeptList", deptlist);
		model.addAttribute("id",dto.getId());
		model.addAttribute("getHobbyList", hobbylist);

		return "user_write";
	}

	// 회원 정보 저장
	@RequestMapping(value = "/user/user_save")
	String user_save(UserDto dto, UHDto uhdto) {
		System.out.println(dto.getIduncheck());
		if (dto.getIduncheck().equals("N")) {
			return "redirect:/user/user_write";
		}
		System.out.println(dto.getId());
		restTemplate.postForObject("http://localhost:8081/api/user_save", dto, UserDto.class);
		if(uhdto.getHobby_cd().equals("")) {
			uhdto.setHobby_cd("f");
		}
		else if(uhdto.getHobby_cd().contains("f")) {
			uhdto.setHobby_cd("f");
		}
		String uhlist = uhdto.getHobby_cd();
		System.out.println(uhlist);
		System.out.println(uhlist.length());
		if (uhlist.length() == 1) {
			restTemplate.postForObject("http://localhost:8081/api/uh_save", uhdto, UHDto.class);
		} else {
			String[] uhlist2 = uhlist.split(",");
			for (int i = 0; i < uhlist2.length; i++) {
				System.out.println(uhlist2[i]);
				uhdto.setHobby_cd(uhlist2[i]);
				restTemplate.postForObject("http://localhost:8081/api/uh_save", uhdto, UHDto.class);
			}
		}
		return "redirect:/";
	}
	
	// 아이디 확인
	@ResponseBody
	@RequestMapping(value = "/user/idcheck")
	HashMap<String, Object> idcheck(UserDto dto) {
		// bt 프로젝트에서 getIdCheck() 호출
		Boolean result = restTemplate.postForObject("http://localhost:8081/api/idcheck", dto, Boolean.class);
		// 결과값을 hashmap에 넣어서 반환
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", result);
        return map;
	}


}