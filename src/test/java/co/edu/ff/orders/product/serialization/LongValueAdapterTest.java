package co.edu.ff.orders.product.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import co.edu.ff.orders.product.domain.ProductId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.jupiter.api.Assertions.*;

class LongValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(ProductId.class, new LongValueAdapter<>(ProductId::of))
                .create();
    }

    @Test
    void serialize() {
        Long value = 1l;
        ProductId  productId = ProductId.of(value);

        String actual = gson.toJson(productId);
        String expected = String.format("%s",productId.getValue());
        assertEquals(actual, expected);
    }

    @Test
    void deserialize() {
    }

}