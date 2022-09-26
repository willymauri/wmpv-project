package com.wmpv.project.enums;

/**
 * @author william.patino
 * @version 1.0
 */
public enum EnumCatalog {
	
	ACTIVO("A"),
	INACTIVO("I"),
	
	AHORRO("H"),
	CORRIENTE("C"),
	
	DEBITO("D"),
	CREDITO("R");
	
	
	private EnumCatalog (String code) {
		this.code=code;
	}
	
	private String code;
	
	public String code() {
		return code;
	}
	
}