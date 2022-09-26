package com.wmpv.project.dto;


import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * @version 1.0
 * @autor william.patino
 **/
@Data
public class CuentaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAccount;
    private Integer idClient;
    private String numberAccount;
    private String typeAccount;
    private String status;
    private BigDecimal initBalance;
    
}
