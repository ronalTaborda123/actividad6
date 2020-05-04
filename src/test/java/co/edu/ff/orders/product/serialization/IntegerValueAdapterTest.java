package co.edu.ff.orders.product.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.ff.orders.product.domain.InventoryQuantity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.jupiter.api.Assertions.*;

class IntegerValueAdapterTest {
    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(InventoryQuantity.class, new IntegerValueAdapter<>(InventoryQuantity::of))
                .create();
    }

    @Test
    void serialize() {
        Integer value = 12;
        InventoryQuantity  inventoryQuantity = InventoryQuantity.of(value);

        String actual = gson.toJson(inventoryQuantity);
        String expected = String.format("%s",inventoryQuantity.getValue());
        assertEquals(actual, expected);
    }

    @Test
    void deserialize() {
    }
}