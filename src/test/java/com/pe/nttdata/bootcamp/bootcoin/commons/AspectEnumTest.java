package com.pe.nttdata.bootcamp.bootcoin.commons;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class AspectEnumTest {

    @Test
    public void shouldValue() {
        assertThat(AspectEnum.EXITO.value(), is("1"));
        assertThat(AspectEnum.MENSAJEOK.value(), is("OK"));
        assertThat(AspectEnum.ERROR.value(), is("0"));
    }

    @Test
    public void shouldGetValue() {
        assertThat(AspectEnum.EXITO.getValue(), is("1"));
        assertThat(AspectEnum.MENSAJEOK.getValue(), is("OK"));
        assertThat(AspectEnum.ERROR.getValue(), is("0"));
    }

    public void shouldOperationEnum() {
        assertThat(AspectEnum.EXITO.fromValue("1").getValue(), is("1"));
        assertThat(AspectEnum.MENSAJEOK.fromValue("OK").getValue(), is("OK"));
        assertThat(AspectEnum.ERROR.fromValue("0").getValue(), is("0"));
    }

    @Test
    public void shouldExitoException() {
        assertThatIllegalArgumentException().isThrownBy(() -> AspectEnum.EXITO.fromValue("EXITO_ERROR"));
    }

    @Test
    public void shouldMensajeOkException() {
        assertThatIllegalArgumentException().isThrownBy(() -> AspectEnum.MENSAJEOK.fromValue("MENSAJEOK_ERROR"));
    }

    @Test
    public void shouldErrorException() {
        assertThatIllegalArgumentException().isThrownBy(() -> AspectEnum.ERROR.fromValue("ERROR_ERROR"));
    }

    @Test
    public void shouldToString() {
        assertThat(AspectEnum.EXITO.toString(), is("1"));
        assertThat(AspectEnum.MENSAJEOK.toString(), is("OK"));
        assertThat(AspectEnum.ERROR.toString(), is("0"));
    }


}
