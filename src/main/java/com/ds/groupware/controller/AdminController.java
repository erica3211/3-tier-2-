package com.ds.groupware.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	private final RestTemplate restTemplate = new RestTemplate();


	// 관리자 기본화면
	@RequestMapping(value = "/admin")
	public String getList(Model model) {
		
		List<Data> userlist = restTemplate.getForObject("http://localhost:8081/api/userlist", List.class);
		int total = restTemplate.getForObject("http://localhost:8081/api/total", Integer.class);
		String searchkey = restTemplate.getForObject("http://localhost:8081/api/searchkey", String.class);
		model.addAttribute("getUserList", userlist);
		model.addAttribute("getTotalCnt", total);
		model.addAttribute("searchKey", searchkey);
		System.out.println("pt1:"+searchkey);
		return "admin";
	}

	//사용자 상세화면
		@RequestMapping(value = "/admin/{user_id}")
		public String getView(@PathVariable String user_id,UserDto dto, Model model) {
			
			List<Data> userlist = restTemplate.getForObject("http://localhost:8081/api/userlist", List.class);
			List<Data> deptlist = restTemplate.getForObject("http://localhost:8081/api/deptlist", List.class);
			List<Data> hobbylist = restTemplate.getForObject("http://localhost:8081/api/hobbylist", List.class);
			List<Data> uh = restTemplate.getForObject("http://localhost:8081/api/uh", List.class);
			String searchkey = restTemplate.getForObject("http://localhost:8081/api/searchkey", String.class);
			System.out.println("userid="+user_id);
			System.out.println(user_id+"의 hobbylist="+uh);
			
			URI uri = UriComponentsBuilder
					.fromUriString("http://localhost:8081")
					.path("/api/userview")
					.queryParam("user_id", user_id)
					.encode()
					.build()
					.toUri();
					
			UserDto userview = restTemplate.getForObject(uri, UserDto.class);
			
			model.addAttribute("getDeptList", deptlist);
			model.addAttribute("getUserList", userlist);
			model.addAttribute("getHobbyList", hobbylist);
			model.addAttribute("getView", userview);
			model.addAttribute("getUHlist", uh);
			model.addAttribute("searchKey", searchkey);
			return "admin";
		}

		// 회원 등록 (승인요청 -> 승인)
		@RequestMapping(value = "/user_update/{user_id}")
		String user_aprv_y(@PathVariable String user_id, UserDto dto) {
			String updateUrl = "http://localhost:8081/api/user_update/" + user_id; // bt 서버의 URI로 설정
			restTemplate.postForObject(updateUrl, dto, UserDto.class);
			System.out.println("등록완료");
			return "redirect:/admin";
		}

		// 회원 삭제
		@RequestMapping(value = "/user_delete/{user_id}")
		String user_delete(@PathVariable String user_id) {
			String deleteUrl = "http://localhost:8081/api/user_delete/" + user_id; // bt 서버의 URI로 설정
			restTemplate.delete(deleteUrl);
			System.out.println("삭제완료");
			return "redirect:/admin";
		}

}
