package com.pe.nttdata.bootcamp.bootcoin.service;

import com.pe.nttdata.bootcamp.bootcoin.model.entity.BootCoinWallet;
import com.pe.nttdata.bootcamp.bootcoin.service.repository.BootCoinWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *<b>Class</b>: {@link BootCoinWalletService}<br/>
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
 *<li>Mar. 8, 2024 (acronym) Creation class.</li>
 *</ul>
 *@version 1.0
 */
@Service
public class BootCoinWalletService {

  @Autowired
  private BootCoinWalletRepository currencyWalletRepository;

  public BootCoinWalletService(BootCoinWalletRepository currencyWalletRepository) {
    this.currencyWalletRepository = currencyWalletRepository;
  }


  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @return {@link Flux}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Flux
   */
  public Flux<BootCoinWallet> findAll() {
    return currencyWalletRepository.findAll();
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param id {@link String}
   * @return {@link Mono}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Mono
   */
  public Mono<BootCoinWallet> findById(String id) {
    return currencyWalletRepository.findById(id);
  }


  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param identityNumber {@link String}
   * @return {@link Mono}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Mono
   */
  public Mono<BootCoinWallet> findByCustomerIdentityNumber(String identityNumber) {
    return currencyWalletRepository.findByCustomerIdentityNumber(identityNumber);
  }

  /**
   * <p/>
   * Flux all elements from Mongo passing for
   * reactivate Flux passing the id as a parameter.
   *
   * @param currencyWallet {@link String}
   * @return {@link Mono}&lt;{@link BootCoinWallet}&gt;
   * @see String
   * @see Mono
   */
  public Mono<BootCoinWallet> save(BootCoinWallet currencyWallet) {
    return currencyWalletRepository.save(currencyWallet);
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
  public Mono<Void> deleteById(String id) {
    return currencyWalletRepository.deleteById(id);
  }

}