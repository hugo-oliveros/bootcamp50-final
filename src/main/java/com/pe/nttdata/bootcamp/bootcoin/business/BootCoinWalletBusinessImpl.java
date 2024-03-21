package com.pe.nttdata.bootcamp.bootcoin.business;

import com.pe.nttdata.bootcamp.bootcoin.commons.OperationEnum;
import com.pe.nttdata.bootcamp.bootcoin.dto.response.*;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.BootCoinWallet;
import com.pe.nttdata.bootcamp.bootcoin.service.BootCoinWalletService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *<b>Class</b>: {@link BootCoinWalletBusinessImpl}<br/>
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
@Service
@Slf4j
public class BootCoinWalletBusinessImpl {

  private final WebClient webClient;

  @Autowired
  private BootCoinWalletService bootCoinWalletService;

  private BootCoinWallet movError;

  private ResultResponseDto resultDto;

  public BootCoinWalletBusinessImpl(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:9090").build();
  }

  public Flux<BootCoinWallet> findAll() {
    return bootCoinWalletService.findAll();
  }

  public Mono<BootCoinWallet> findById(String id) {
    return bootCoinWalletService.findById(id);
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param bootcoinWallet {@link BootCoinWallet}
   * @return {@link Mono}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Mono
   * @see BootCoinWallet
   */
  public Mono<BootCoinWallet> save(BootCoinWallet bootcoinWallet) {

    return bootCoinWalletService.findByCustomerIdentityNumber(
               bootcoinWallet.getCustomer().getIdentityNumber())
              .log("Entering the CurrencyWallet save operation.")
              .map(res -> {
                if (res.getOperations() == null || res.getOperations().isEmpty()) {
                  res.setOperations(bootcoinWallet.getOperations());
                }
                return res;
              })
              .flatMap(operation -> {

                operation.getOperations().addAll(bootcoinWallet.getOperations());

                resultDto = validateNegativeOrPositiveResult(bootcoinWallet);

                if (!resultDto.isNegative()) {
                  if (Double.compare(resultDto.getAmount(),
                            operation.getCurrencyCoinAmount()) < 0) {
                    return bootCoinWalletService.save(operation);
                  } else {
                    movError =  BootCoinWallet.builder().build();
                    movError.setDescription("The amount exceeds what is allowed, please verify");
                    return Mono.just(movError);
                  }

                } else {
                  movError =  BootCoinWallet.builder().build();
                  movError.setDescription("This operation is not allowed because"
                          + " the total amount is negative.");
                  return Mono.just(movError);
                }

              })
              .switchIfEmpty(Mono.defer(() -> {
                resultDto = validateNegativeOrPositiveResult(bootcoinWallet);
                if (!resultDto.isNegative()) {
                  bootcoinWallet.setStatus(OperationEnum.OK.value());
                  return bootCoinWalletService.save(bootcoinWallet);
                } else {
                  movError =  BootCoinWallet.builder().build();
                  movError.setDescription("This operation is not allowed "
                          + "because the total amount is negative.");
                  return Mono.just(movError);
                }

              }).onErrorResume(error -> {
                movError = BootCoinWallet.builder().build();
                movError.setDescription("Error found...: "
                        + error);
                return Mono.just(movError);
              }));
  }


  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param id {@link String}
   * @param currencyWallet {@link BootCoinWallet}
   * @return {@link Mono}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Mono
   */
  public  Mono<BootCoinWallet> update(final String id, final BootCoinWallet currencyWallet) {
    return bootCoinWalletService.findById(id)
            .map(Optional::of)
            .defaultIfEmpty(Optional.empty())
            .flatMap(optional -> {
              if (optional.isPresent()) {
                currencyWallet.setId(new ObjectId(id));
                return bootCoinWalletService.save(currencyWallet);
              }
              return Mono.empty();
            });
  }

  public Mono<Void> deleteById(String id) {
    return bootCoinWalletService.deleteById(id);
  }


  /**
   * <p/>
   * Checks if the amount is negative and returns a {@link BootCoinWallet}.
   *
   * @param bootcoinWallet {@link BootCoinWallet}
   * @return {@link ResultResponseDto}&lt;{@link ResultResponseDto}&gt;
   * @see ResultResponseDto
   */
  private ResultResponseDto validateNegativeOrPositiveResult(BootCoinWallet bootcoinWallet) {
    ResultResponseDto result =  ResultResponseDto.builder().build();

    ArrayList<operation> operationArray = new ArrayList<operation>();

    double resultAmount = bootcoinWallet.getOperations().stream()
              .peek(ope -> {

                if (ope.getPaymentType().equals(OperationEnum.SEND.getValue())) {
                  Double amountNegative =  (Math.abs(ope.getAmount()) * -1);
                  ope.setAmount(amountNegative);
                  if (ope.getOperationMode().equals(OperationEnum.YANKI.getValue())) {
                    log.info("Operation Yanki");
                  }
                  if (ope.getOperationMode().equals(OperationEnum.TRANSFER.getValue())) {
                    log.info("Operation Transfer");
                  }
                }

                if (ope.getPaymentType().equals(OperationEnum.RECEIVE.getValue())) {
                  if (ope.getOperationMode().equals(OperationEnum.YANKI.getValue())) {

                    currencyWallet currencyWalletResponse = currencyWallet.builder().build();
                    currencyWalletResponse.setStatus(OperationEnum.OK.value());
                    currencyWalletResponse.setCurrencyCoinAmount(bootcoinWallet.getCurrencyCoinAmount());

                    customer customerResponseDto = customer.builder().build();
                    customerResponseDto.setIdentityNumber(bootcoinWallet.getCustomer().getIdentityNumber());
                    customerResponseDto.setEmail(bootcoinWallet.getCustomer().getEmail());
                    customerResponseDto.setMobileNumber(bootcoinWallet.getCustomer().getMobileNumber());

                    operation operationResponseDto = operation.builder().build();
                    operationResponseDto.setStatus(OperationEnum.OK.value());
                    operationResponseDto.setTime(new Date());
                    operationResponseDto.setAmount(ope.getAmount());
                    operationResponseDto.setPaymentType(OperationEnum.BOOTCOIN.value());

                    operationArray.add(operationResponseDto);

                    currencyWalletResponse.setCustomer(customerResponseDto);
                    currencyWalletResponse.setOperations(operationArray);

                    CurrencyWalletDto currencyWalletDto = CurrencyWalletDto.builder().build();
                    currencyWalletDto.setCurrencyWallet(currencyWalletResponse);

                    triggerCurrencyWallet(currencyWalletDto);

                  }
                  if (ope.getOperationMode().equals(OperationEnum.TRANSFER.getValue())) {
                    log.info("Operation Transfer");
                  }
                }

              }).collect(Collectors.summingDouble(d -> d.getAmount()));

    result.setNegative((Double.doubleToLongBits(resultAmount) < 0));
    result.setAmount(resultAmount);
    return result;
  }


  private void triggerCurrencyWallet(CurrencyWalletDto currencyWalletDto){
    log.info("\u001B[36mOperation Yanki Param {}\u001B[0m ", currencyWalletDto.getCurrencyWallet());
    this.webClient.post()
            .uri("/currencyWallet/api/v1/save")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(currencyWalletDto), CurrencyWalletDto.class)
            .retrieve()
            .bodyToMono(currencyWallet.class)
            .subscribe(
                    responseEntity -> {
                      log.info("\u001B[36mLog Execution=>\u001B[32m{}\u001B[33m\u001B[0m",responseEntity);
                    },
                    error ->{
                      log.info("Error {}",error);
                    }
            );
  }


}
