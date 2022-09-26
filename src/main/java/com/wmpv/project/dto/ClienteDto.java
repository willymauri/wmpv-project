package com.wmpv.project.dto;


import java.io.Serializable;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Data
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;
    private String name;
    private String id;
    private String address;
    private String gender;
    private String phone;
    private Integer age;
    private String pass;
    private String status;
    
}
