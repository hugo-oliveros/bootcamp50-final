package com.pe.nttdata.bootcamp.bootcoin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *<b>Class</b>: {@link BootCoinWalletDto}<br/>
 *<b>Copyright</b>: &Copy; 2024 NTTDATA Per&uacute;. <br/>
 *<b>Company</b>: NTTDATA del Per&uacute;. <br/>
 *
 *@author NTTDATA Per&uacute;. (EVE) <br/>
 *<u>Developed by</u>: <br/>
 *<ul>
 *<li>Hugo Oliveros Monti</li>
 *</ul>
 *<u>Changes</u>:<br/>
 *<ul>
 *<li>feb. 29, 2024 (acronym) Creation class.</li>
 *</ul>
 *@version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AppProcesoLogDto {

  private Date fechaInicioEjecucion;
  private Date fechaFinEjecucion;
  private String estadoEjecucion;
  private String descripcionEstadoEjecucion;
  private String modulo;
  private String claseProgramacion;
  private String metodoProgramacion;
  private String parametroEntrada;
  private String resultadoSalida;
  private Integer duracionMs;

}
