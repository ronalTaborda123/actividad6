package co.edu.ff.orders.product.domain;

import java.lang.reflect.Executable;
import java.math.BigDecimal;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {


    @Test
    @DisplayName("valueOf retorna en mismo valor ingresado para el nombre")
    void valueOfSameValue(){
        String name = "Carlos cuello";
        Name nameInstance= Name.of(name);
        assertEquals(nameInstance.valueOf(),name);
    }

    @Test
    @DisplayName("Debe crear nombres validos")
    void isShouldPass(){
        String name = "Calos cuello";
        String name2 = "Calos cardenas";

        assertAll(
                () ->assertNotNull(name),
                () ->assertNotNull(name2)
        );
    }

    @Test
    @DisplayName("No debe crear name validos")
    void isShouldNotPass(){
        String name1 = null;
        String name2 = "";
        String name3 = RandomStringUtils.randomAlphanumeric(101);

        assertAll(
                () ->assertThrows(NullPointerException.class,() -> Name.of(name1)),
                () ->assertThrows(IllegalArgumentException.class, () ->Name.of(name2)),
                () ->assertThrows(IllegalArgumentException.class, () ->Name.of(name3))
        );
    }

}