package com.ds.groupware.service;

import java.net.URI;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ds.groupware.dto.DeptDto;
import com.ds.groupware.dto.HobbyDto;
import com.ds.groupware.dto.UHDto;
import com.ds.groupware.dto.UserDto;

@Service("apiservice")
public class APIService {
	private final RestTemplate restTemplate = new RestTemplate();
	String baseurl = "http://localhost:8081/api/";
	
	@SuppressWarnings("unchecked")
	public List<UserDto> getUserList(UserDto dto) {
		String searchKey = dto.getSearchKey();
		String schuri = baseurl+"userlist?searchKey="+searchKey; 
		List<UserDto> userlist = restTemplate.getForObject(schuri, List.class);
		return userlist;
	}
	@SuppressWarnings("unchecked")
	public List<DeptDto> getDeptList(DeptDto deptdto) {
		List<DeptDto> deptlist = restTemplate.getForObject(baseurl+"deptlist", List.class);
		return deptlist;
	}
	public String getUHList(String user_id,UHDto uhdto) {
		String uri2 = baseurl + "uh?user_id=" + user_id; // bt 서버의 URI로 설정
		String uh = restTemplate.getForObject(uri2, String.class);
		System.out.println(user_id+"의 hobbylist="+uh);
		return uh;
	}
	@SuppressWarnings("unchecked")
	public List<HobbyDto> getHobbyList(HobbyDto H_dto) {
		List<HobbyDto> hobbylist = restTemplate.getForObject(baseurl+"hobbylist", List.class);
		return hobbylist;
	}
	public String getsearchKey(UserDto dto) {
		String searchKey = dto.getSearchKey();
		return searchKey;
	}
	public void user_aprv_y(String user_id, UserDto dto) {
		String updateUrl = baseurl + "user_update/" + user_id; // bt 서버의 URI로 설정
		restTemplate.postForObject(updateUrl, dto, UserDto.class);
		System.out.println("등록완료");
	}
	public void user_delete(String user_id) {
		String deleteUrl = baseurl + "user_delete/" + user_id; // bt 서버의 URI로 설정
		restTemplate.delete(deleteUrl);
		System.out.println("삭제완료");
	}
	
	public UserDto getUserview(String user_id,UserDto dto) {
		URI uri = UriComponentsBuilder
				.fromUriString(baseurl)
				.path("userview")
				.queryParam("user_id", user_id)
				.encode()
				.build()
				.toUri();
				
		UserDto userview = restTemplate.getForObject(uri, UserDto.class);
		return userview;
	}
	public void user_save(UserDto dto, UHDto uhdto) {

		restTemplate.postForObject(baseurl+"user_save", dto, UserDto.class);
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
			restTemplate.postForObject(baseurl+"uh_save", uhdto, UHDto.class);
		} else {
			String[] uhlist2 = uhlist.split(",");
			for (int i = 0; i < uhlist2.length; i++) {
				System.out.println(uhlist2[i]);
				uhdto.setHobby_cd(uhlist2[i]);
				restTemplate.postForObject(baseurl+"uh_save", uhdto, UHDto.class);
			}
			}
		}
		
		public boolean idcheck(UserDto dto) {
			// bt 프로젝트에서 getIdCheck() 호출
			Boolean result = restTemplate.postForObject(baseurl+"idcheck", dto, Boolean.class);
			return result;
	}
}
