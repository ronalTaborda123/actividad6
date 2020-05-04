package co.edu.ff.orders.product.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.ff.orders.product.domain.ProductStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.jupiter.api.Assertions.*;

class EnumValueAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(ProductStatus.class, new EnumValueAdapter())
                .create();
    }


    @Test
    void deserialize() {
        String value = "BORRADOR";
        ProductStatus  productStatus= ProductStatus.BORRADOR;
        String actual = gson.toJson(productStatus);

        //comprueban
        String expected = String.format("\"%s\"", productStatus.name());
        assertEquals(actual, expected);
    }
}