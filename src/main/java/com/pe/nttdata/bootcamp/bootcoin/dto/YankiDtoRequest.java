package com.pe.nttdata.bootcamp.bootcoin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pe.nttdata.bootcamp.bootcoin.dto.response.currencyWallet;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class YankiDtoRequest {

    private currencyWallet currencyWalle;

}
