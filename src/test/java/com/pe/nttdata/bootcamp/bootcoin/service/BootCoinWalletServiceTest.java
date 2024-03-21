package com.pe.nttdata.bootcamp.bootcoin.service;

import com.pe.nttdata.bootcamp.bootcoin.business.BootCoinWalletBusinessImpl;
import com.pe.nttdata.bootcamp.bootcoin.commons.OperationEnum;
import com.pe.nttdata.bootcamp.bootcoin.dto.BootCoinWalletDto;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.BootCoinWallet;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.Customer;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.Operation;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BootCoinWalletServiceTest {

    @InjectMocks
    private BootCoinWalletBusinessImpl bootCoinWalletBusinessImpl;

    @Mock
    private BootCoinWalletService bootCoinWalletService;

    @BeforeEach
    public void init() {

    }

    @Test
    void findAll() {
        BootCoinWallet currencyWalle = new BootCoinWallet();

        currencyWalle.setStatus("OK");
        currencyWalle.setCurrencyCoinAmount(1000.0);

        Customer customer = new Customer();
        customer.setIdentityNumber("10520000");
        customer.setEmail("xxxxx@gmail.com");
        customer.setMobileNumber("9573643");


        Operation operation = new Operation();
        operation.setStatus("OK");
        operation.setPaymentType(OperationEnum.SEND.value());
        operation.setAmount(100.0);

        ArrayList<Operation> operations = new ArrayList<Operation>();
        operations.add(operation);

        currencyWalle.setCustomer(customer);
        currencyWalle.setOperations(operations);
        currencyWalle.setDescription("");

        when(bootCoinWalletService.findAll()).thenReturn(Flux.just(currencyWalle));

        Flux<BootCoinWallet> result = bootCoinWalletService.findAll();

        StepVerifier.create(result)
                .expectNext(currencyWalle)
                .expectComplete()
                .verify();

    }

    @Test
    void create() {


        BootCoinWallet currencyWalle = new BootCoinWallet();
        currencyWalle.setId(new ObjectId("65e781f9d121b02f4b951c25"));
        currencyWalle.setStatus("OK");
        currencyWalle.setCurrencyCoinAmount(1000.0);

        Customer customer = new Customer();
        customer.setIdentityNumber("10520000");
        customer.setEmail("xxxxx@gmail.com");
        customer.setMobileNumber("9573643");

        Operation operation = new Operation();
        operation.setStatus("OK");
        operation.setPaymentType(OperationEnum.SEND.value());
        operation.setAmount(100.0);

        ArrayList<Operation> operations = new ArrayList<Operation>();
        operations.add(operation);

        currencyWalle.setCustomer(customer);
        currencyWalle.setOperations(operations);
        currencyWalle.setDescription("");

        BootCoinWalletDto bootCoinWalletDto = new BootCoinWalletDto();
        bootCoinWalletDto.setBootcoinWallet(currencyWalle);

        when(bootCoinWalletService.save(bootCoinWalletDto.getBootcoinWallet())).thenReturn(Mono.just(currencyWalle));

        Mono<BootCoinWallet> result = bootCoinWalletService.save(bootCoinWalletDto.getBootcoinWallet());

        StepVerifier.create(result)
                .expectNext(currencyWalle)
                .expectComplete()
                .verify();

        }


    @Test
    void delete() {
        final String _id = "65f8c54790c82e1a32b81182";
        when(bootCoinWalletService.deleteById(_id)).thenReturn(Mono.empty());
        Mono<Void> resul = bootCoinWalletService.deleteById(_id);
        StepVerifier.create(resul)
                .expectComplete()
                .verify();
    }

}
