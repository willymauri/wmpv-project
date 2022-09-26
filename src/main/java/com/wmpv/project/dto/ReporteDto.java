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
public class ReporteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private String estado;
    
}
