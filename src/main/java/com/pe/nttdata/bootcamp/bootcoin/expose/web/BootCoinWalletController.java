package com.pe.nttdata.bootcamp.bootcoin.expose.web;

import com.pe.nttdata.bootcamp.bootcoin.business.BootCoinWalletBusinessImpl;
import com.pe.nttdata.bootcamp.bootcoin.dto.BootCoinWalletDto;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.BootCoinWallet;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *<b>Class</b>: {@link BootCoinWalletController}<br/>
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
 *<li>mar. 20, 2024 (acronym) Creation class.</li>
 *</ul>
 *@version 1.0
 */
@RestController
@RequestMapping("bootcoinWallet/api/v1")
public class BootCoinWalletController {

  /**
   * .
   * BootCoinWalletBusinessImpl bootCoinWalletBusiness
   **/


  private BootCoinWalletBusinessImpl bootCoinWalletBusiness;


  public BootCoinWalletController(BootCoinWalletBusinessImpl bootCoinWalletBusiness) {
    this.bootCoinWalletBusiness = bootCoinWalletBusiness;
  }

  /**
   * </p>
   * Flux all elements from Mongo passing
   * for reactivate Flux.
   * return all elements from Mono passing
   * for reactivate Flux and return Status OK.
   *
   **/
  @GetMapping(value = "/all")
  @ResponseStatus(HttpStatus.OK)
  public Flux<BootCoinWallet> getAll() {
    return bootCoinWalletBusiness.findAll();
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param id {@link String}
   * @return {@link Mono}&lt;{@link BootCoinWalletDto}&gt;
   * @see String
   * @see Mono
   */
  @GetMapping(value = "/find/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<BootCoinWallet> find(final @PathVariable("id") @NotNull String id) {
    return bootCoinWalletBusiness.findById(id);
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param bootCoinWalletDto {@link BootCoinWalletDto}
   * @return {@link Mono}&lt;{@link BootCoinWalletDto}&gt;
   * @see String
   * @see Mono
   */
  @PostMapping(path = "/save")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<BootCoinWallet> create(final @RequestBody @NotNull
                                   BootCoinWalletDto bootCoinWalletDto) {
    return bootCoinWalletBusiness.save(bootCoinWalletDto.getBootcoinWallet());
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param id {@link String}
   * @return {@link Mono}&lt;{@link Void}&gt;
   * @see String
   * @see Mono
   * @see Void
   */
  @DeleteMapping(value = "/delete/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> delete(final @PathVariable("id") @NotNull String id) {
    return bootCoinWalletBusiness.deleteById(id);
  }

}
