package com.ds.groupware.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
	protected String searchKey="";
	protected String iduncheck="";
	
	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getIduncheck() {
		return iduncheck;
	}

	public void setIduncheck(String idunchecked) {
		this.iduncheck = idunchecked;
	}
}
