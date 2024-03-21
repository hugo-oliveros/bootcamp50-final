package com.pe.nttdata.bootcamp.bootcoin.util;

import com.pe.nttdata.bootcamp.bootcoin.model.entity.BootCoinWallet;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.Customer;
import com.pe.nttdata.bootcamp.bootcoin.model.entity.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

import static com.pe.nttdata.bootcamp.bootcoin.commons.OperationEnum.SEND;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class MapperUtilsTest {

    @Test
    public void shouldObjectValue() {

        BootCoinWallet currencyWalle = new BootCoinWallet();

        currencyWalle.setStatus("OK");
        currencyWalle.setCurrencyCoinAmount(1000.0);

        Customer customer = new Customer();
        customer.setIdentityNumber("10520000");
        customer.setEmail("xxxxx@gmail.com");
        customer.setMobileNumber("9573643");

        Operation operation = new Operation();
        operation.setStatus("OK");
        operation.setPaymentType(SEND.value());
        operation.setAmount(100.0);

        ArrayList<Operation> operations = new ArrayList<Operation>();
        operations.add(operation);

        currencyWalle.setCustomer(customer);
        currencyWalle.setOperations(operations);
        currencyWalle.setDescription("");

        assertThat(MapperUtils.mapper(BootCoinWallet.class,currencyWalle).getDescription(),is(""));

    }

    @Test
    public void shouldStringValue() {
        assertThat(MapperUtils.mapper(BootCoinWallet.class,"{ description:'OK' }").getDescription(),is("OK"));
    }

    @Test
    public void shouldWithNullString() {
        String json=null;
        assertNull(MapperUtils.mapper(BootCoinWallet.class,json));
    }

    @Test
    public void shouldWithNullObject() {
        BootCoinWallet currencyWalle=null;
        assertNull(MapperUtils.mapper(BootCoinWallet.class,currencyWalle));
    }

}
