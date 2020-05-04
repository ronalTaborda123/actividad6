package co.edu.ff.orders.product.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasePriceTest {

    @Test
    @DisplayName("BIG_DECIMAL retorna en mismo valor ingresado para el taxRate")
    void valueOfSameValue(){
        BigDecimal basePrice = new BigDecimal(0);
        BasePrice nameInstance= BasePrice.of(basePrice);
        assertEquals(nameInstance.BIG_DECIMAL(),basePrice);
    }

    @Test
    @DisplayName("Debe crear BasePrice validos")
    void isShouldPass(){
        BigDecimal basePrice = new BigDecimal(0);
        BigDecimal basePrice1 = new BigDecimal(1);

        assertAll(
                () ->assertNotNull(basePrice),
                () ->assertNotNull(basePrice1)
        );
    }

    @Test
    @DisplayName("No debe crear BasePrice validos")
    void isShouldNotPass(){
        BigDecimal basePrice = null;
        BigDecimal basePrice1 = new BigDecimal(-2);
        assertAll(
                () ->assertThrows(NullPointerException.class,() -> BasePrice.of(basePrice)),
                () ->assertThrows(IllegalArgumentException.class, () ->BasePrice.of(basePrice1))
        );
    }

}