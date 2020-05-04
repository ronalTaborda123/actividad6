package co.edu.ff.orders.product.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryQuantityTest {
    @Test
    @DisplayName("valuInteger retorna en mismo valor ingresado para el InventoryQuantity")
    void valueOfSameValue(){
        Integer value = 525;
        InventoryQuantity nameInstance= InventoryQuantity.of(value);
        assertEquals(nameInstance.valuInteger(),value);
    }

    @Test
    @DisplayName("Debe crear InventoryQuantity validos")
    void isShouldPass(){
        Integer value = 44555;
        Integer value1 = 2;

        assertAll(
                () ->assertNotNull(value),
                () ->assertNotNull(value1)
        );
    }

    @Test
    @DisplayName("No debe crear InventoryQuantity validos")
    void isShouldNotPass(){
        Integer value = null;
        Integer value1 = -2;

        assertAll(
                () ->assertThrows(NullPointerException.class,() -> InventoryQuantity.of(value)),
                () ->assertThrows(IllegalArgumentException.class, () ->InventoryQuantity.of(value1))
        );
    }
}