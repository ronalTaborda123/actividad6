package co.edu.ff.orders.product.serialization;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.ff.orders.product.domain.BasePrice;
import co.edu.ff.orders.product.domain.InventoryQuantity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.jupiter.api.Assertions.*;

class BigDecimalValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(BasePrice.class, new BigDecimalValueAdapter<>(BasePrice::of))
                .create();
    }


    @Test
    void serialize() {
        BigDecimal value = new BigDecimal(1);
        BasePrice  basePrice = BasePrice.of(value);

        String actual = gson.toJson(basePrice);
        String expected = String.format("%s",basePrice.getValue());
        assertEquals(actual, expected);
    }

    @Test
    void deserialize() {
    }
}