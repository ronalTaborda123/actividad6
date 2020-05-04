package co.edu.ff.orders.product.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.serialization.BigDecimalSerializable;
import co.edu.ff.orders.product.services.ProductServices;
import co.edu.ff.orders.user.services.UserServices;
import com.google.gson.Gson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
class ProductControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    ProductServices service;

    @MockBean
    UserServices userservice;

    @Test
    void createProduct() {

    }

    @Test
    void getProductById() {
    }

    @Test
    void getListProductEmpty() throws Exception {
        when(service.findall()).thenReturn(Arrays.asList());
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product");
        this.mockMvc.perform(servletRequestBuilder).andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    void getListProduct() throws Exception {
        Product product= Product.of(
                ProductId.of(1l),
                Name.of("Mazda 2"),
                Description.of("Negro nebulosa"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(45));

        String productJson= this.gson.toJson(Arrays.asList(product));
        when(service.findall()).thenReturn(Arrays.asList(product));
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.get("/api/v1/product");
        this.mockMvc.perform(servletRequestBuilder).
                andDo(print()).
                andExpect(status().is2xxSuccessful()).
                andExpect(content().json(productJson));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}