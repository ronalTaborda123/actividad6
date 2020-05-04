package co.edu.ff.orders.product.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxRateTest {

    @Test
    @DisplayName("BIG_DECIMAL retorna en mismo valor ingresado para el taxRate")
    void valueOfSameValue(){
        BigDecimal taxRate = new BigDecimal(0);
        TaxRate nameInstance= TaxRate.of(taxRate);
        assertEquals(nameInstance.BIG_DECIMAL(),taxRate);
    }

    @Test
    @DisplayName("Debe crear taxRate validos")
    void isShouldPass(){
        BigDecimal taxRate = new BigDecimal(0);
        BigDecimal taxRate2 = new BigDecimal(1);

        assertAll(
                () ->assertNotNull(taxRate),
                () ->assertNotNull(taxRate2)
        );
    }

    @Test
    @DisplayName("No debe crear taxRate validos")
    void isShouldNotPass(){
        BigDecimal taxRate = null;
        BigDecimal taxRate3 = new BigDecimal(3);

        assertAll(
                () ->assertThrows(NullPointerException.class,() -> TaxRate.of(taxRate)),
                () ->assertThrows(IllegalArgumentException.class, () ->TaxRate.of(taxRate3))
        );
    }
}