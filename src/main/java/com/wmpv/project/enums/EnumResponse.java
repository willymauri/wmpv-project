package com.wmpv.project.enums;

/**
 * @author william.patino
 * @version 1.0
 */
public enum EnumResponse {
	
	OK("OK"),
	
	ERROR("ERROR");
	
	private EnumResponse (String code) {
		this.code=code;
	}
	
	private String code;
	
	public String code() {
		return code;
	}
	
}