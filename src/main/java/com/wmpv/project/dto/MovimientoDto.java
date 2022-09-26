package com.wmpv.project.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Data
public class MovimientoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private Date date;
    private BigDecimal balance;
    private BigDecimal value;
    private Integer idAccount;
    
}
