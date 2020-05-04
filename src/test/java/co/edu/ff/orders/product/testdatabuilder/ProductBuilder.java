package co.edu.ff.orders.product.testdatabuilder;

import java.math.BigDecimal;

import co.edu.ff.orders.product.domain.*;

public class ProductBuilder {

    private long productId;
    private String name;
    private String description;
    private BigDecimal basePrice;
    private BigDecimal taxRate;
    private Integer inventoryQuantity;

    public ProductBuilder(){
        this.productId=1l;
        this.name="Mazda 2";
        this.description= "Negro nebulosa";
        this.basePrice= BigDecimal.TEN;
        this.taxRate=BigDecimal.TEN;
        this.inventoryQuantity=45;
    }

    public Product build(){
        return Product.of(
                ProductId.of(productId),
                Name.of(name),
                Description.of(description),
                BasePrice.of(basePrice),
                TaxRate.of(taxRate),
                ProductStatus.PUBLICADO,
                InventoryQuantity.of(inventoryQuantity));
    }

}
