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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.exceptions.ProductDoesNotExistsException;
import co.edu.ff.orders.product.serialization.BigDecimalSerializable;
import co.edu.ff.orders.product.services.ProductServices;
import co.edu.ff.orders.user.services.UserServices;
import com.google.gson.Gson;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    void createProductFailure() throws Exception {
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Name.of("Mazda 2"),
                Description.of("Negro nebulosa"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(45)
        );
        ProductOperationFailure of = ProductOperationFailure.of(ProductDoesNotExistsException.of(1l));
        when(service.createProduct(productOperationRequest)).thenReturn(of);
        MockHttpServletRequestBuilder servletRequestBuilder = post("/api/v1/product");
        this.mockMvc.perform(servletRequestBuilder).andDo(print()).andExpect(status().is4xxClientError());

    }

    @Test
    void createProduct() throws Exception {
        ProductOperationRequest productOperationRequest = ProductOperationRequest.of(
                Name.of("Mazda 2"),
                Description.of("Negro nebulosa"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(45)
        );
        Product product=Product.of(
                ProductId.of(1l),
                Name.of("Mazda 2"),
                Description.of("Negro nebulosa"),
                BasePrice.of(BigDecimal.ONE),
                TaxRate.of(BigDecimal.ONE),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(45));

        ProductOperationSuccess of = ProductOperationSuccess.of(product);
        String productJsonRequest= this.gson.toJson(productOperationRequest);
        String productJson= this.gson.toJson(of);
        when(service.createProduct(productOperationRequest)).thenReturn(of);
        MockHttpServletRequestBuilder servletRequestBuilder = MockMvcRequestBuilders.post("/api/v1/product");
        this.mockMvc.perform(post("/api/v1/product").contentType(MediaType.APPLICATION_JSON).
                content(productJsonRequest)
                .accept(MediaType.APPLICATION_JSON)).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(content().json(productJson));
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
                andExpect(status().isOk()).
                andExpect(content().json(productJson));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}