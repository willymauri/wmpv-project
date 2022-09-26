package com.wmpv.project.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author william.patino
 * @version 1.0
 */
public class ResponseData<T> extends ResponseModel {
	
	/**
	 * Serializado 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private T data;
	
	public ResponseData(String code) {
		super(code);
	}
}
