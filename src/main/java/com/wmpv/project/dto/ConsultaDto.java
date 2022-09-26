package com.wmpv.project.dto;


import java.io.Serializable;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Data
public class ConsultaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;
    private Integer size;
    private String order;
    private String id;
    
}
