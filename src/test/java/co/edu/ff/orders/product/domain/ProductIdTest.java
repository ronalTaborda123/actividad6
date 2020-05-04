package co.edu.ff.orders.product.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdTest {

    @Test
    @DisplayName("vaALong retorna en mismo valor ingresado para el productId")
    void valueOfSameValue(){
        Long productId = 1L;
        ProductId nameInstance= ProductId.of(productId);
        assertEquals(nameInstance.vaALong(),productId);
    }

    @Test
    @DisplayName("Debe crear productId validos")
    void isShouldPass(){
        Long productId = 5L;
        Long productId2 = 1L;

        assertAll(
                () ->assertNotNull(productId),
                () ->assertNotNull(productId2)
        );
    }

    @Test
    @DisplayName("No debe crear name validos")
    void isShouldNotPass(){
        Long productId = null;
        Long productId2 = 0l;

        assertAll(
                () ->assertThrows(NullPointerException.class,() -> ProductId.of(productId)),
                () ->assertThrows(IllegalArgumentException.class, () ->ProductId.of(productId2))
        );
    }

}