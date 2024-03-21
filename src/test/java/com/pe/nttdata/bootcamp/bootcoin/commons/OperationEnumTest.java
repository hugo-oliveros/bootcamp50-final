package com.pe.nttdata.bootcamp.bootcoin.commons;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class OperationEnumTest {

    @Test
    public void shouldValue() {
        assertThat(OperationEnum.OK.value(), is("OK"));
        assertThat(OperationEnum.SEND.value(), is("SEND"));
        assertThat(OperationEnum.ERROR.value(), is("ERROR"));
        assertThat(OperationEnum.RECEIVE.value(),  is("RECEIVE"));
        assertThat(OperationEnum.TRANSFER.value(),  is("TRANSFER"));
        assertThat(OperationEnum.YANKI.value(),  is("YANKI"));
    }

    @Test
    public void shouldGetValue() {
        assertThat(OperationEnum.OK.getValue(), is("OK"));
        assertThat(OperationEnum.SEND.getValue(), is("SEND"));
        assertThat(OperationEnum.ERROR.getValue(), is("ERROR"));
        assertThat(OperationEnum.RECEIVE.getValue(),  is("RECEIVE"));
        assertThat(OperationEnum.TRANSFER.getValue(),  is("TRANSFER"));
        assertThat(OperationEnum.YANKI.getValue(),  is("YANKI"));
    }

    @Test
    public void shouldOperationEnum() {
        assertThat(OperationEnum.OK.fromValue("OK").getValue(), is("OK"));
        assertThat(OperationEnum.SEND.fromValue("SEND").getValue(), is("SEND"));
        assertThat(OperationEnum.ERROR.fromValue("ERROR").getValue(), is("ERROR"));
        assertThat(OperationEnum.RECEIVE.fromValue("RECEIVE").getValue(),  is("RECEIVE"));
        assertThat(OperationEnum.TRANSFER.fromValue("TRANSFER").getValue(),  is("TRANSFER"));
        assertThat(OperationEnum.YANKI.fromValue("YANKI").getValue(),  is("YANKI"));
    }

    @Test
    public void shouldOkException() {
        assertThatIllegalArgumentException().isThrownBy(() -> OperationEnum.OK.fromValue("OK_ERROR"));
    }

    @Test
    public void shouldSendException() {
        assertThatIllegalArgumentException().isThrownBy(() -> OperationEnum.SEND.fromValue("SEND_ERROR"));
    }

    @Test
    public void shouldErrorException() {
        assertThatIllegalArgumentException().isThrownBy(() -> OperationEnum.ERROR.fromValue("ERROR_ERROR"));
    }

    @Test
    public void shouldTransferException() {
        assertThatIllegalArgumentException().isThrownBy(() -> OperationEnum.TRANSFER.fromValue("TRANSFER_ERROR"));
    }

    @Test
    public void shouldYankiException() {
        assertThatIllegalArgumentException().isThrownBy(() -> OperationEnum.YANKI.fromValue("YANKI_ERROR"));
    }

    @Test
    public void shouldToString() {
        assertThat(OperationEnum.OK.toString(), is("OK"));
        assertThat(OperationEnum.SEND.toString(), is("SEND"));
        assertThat(OperationEnum.ERROR.toString(), is("ERROR"));
        assertThat(OperationEnum.RECEIVE.toString(), is("RECEIVE"));
        assertThat(OperationEnum.TRANSFER.toString(), is("TRANSFER"));
        assertThat(OperationEnum.YANKI.toString(), is("YANKI"));

    }

}

