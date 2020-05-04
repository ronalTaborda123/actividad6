package co.edu.ff.orders.product.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionTest {
    @Test
    @DisplayName("valueOf retorna en mismo valor ingresado para el description")
    void valueOfSameValue(){
        String description = "Cilantro";
        Description nameInstance= Description.of(description);
        assertEquals(nameInstance.valueOf(),description);
    }

    @Test
    @DisplayName("Debe crear descriptiones validas")
    void isShouldPass(){
        String description = "Tomates frescos";
        String description2 = "Cebolla morado";

        assertAll(
                () ->assertNotNull(description),
                () ->assertNotNull(description2)
        );
    }

    @Test
    @DisplayName("No debe crear name validos")
    void isShouldNotPass(){
        String description1 = null;
        String description2 = "";
        String description3 = RandomStringUtils.randomAlphanumeric(281);

        assertAll(
                () ->assertThrows(NullPointerException.class,() -> Description.of(description1)),
                () ->assertThrows(IllegalArgumentException.class, () ->Description.of(description2)),
                () ->assertThrows(IllegalArgumentException.class, () ->Description.of(description3))
        );
    }

}