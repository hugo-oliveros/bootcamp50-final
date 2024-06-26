package com.pe.nttdata.bootcamp.bootcoin.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.NoObjectIdSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 *<b>Class</b>: {@link currencyWallet}<br/>
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
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class currencyWallet implements Serializable {

  private static final long serialVersionUID = -1L;

  @Id
  @JsonSerialize(using = NoObjectIdSerializer.class)
  private ObjectId id;
  private String status;
  private com.pe.nttdata.bootcamp.bootcoin.dto.response.customer customer;
  private Double currencyCoinAmount;
  private ArrayList<operation> operations;
  private String description;

}
