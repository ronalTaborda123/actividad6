package co.edu.ff.orders.user.serialization;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.ff.orders.user.domain.Username;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static org.junit.jupiter.api.Assertions.*;

class StringValueAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Username.class, new StringValueAdapter<>(Username::of))
                .create();
    }

    @Test
    void deserialize() {
    }

    @Test
    void serialize() {
        // organizar
        String usernameString = "Username1234";
        Username username = Username.of(usernameString);

        //actuar
        String actual = gson.toJson(username);

        //comprueban
        String expected = String.format("\"%s\"", username.getValue());
        assertEquals(actual, expected);
    }

}