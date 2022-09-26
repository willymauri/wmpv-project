package com.wmpv.project.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author william.patino
 * @version 1.0
 */
@Data
public class ResponseModel implements Serializable {
    
	/**
	 * Serializado 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
    String message;
    
    public ResponseModel(String code) {
    	this.code = code;
    	this.message = "";
    }
}
