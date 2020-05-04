package co.edu.ff.orders.product.repositories;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.exceptions.ProductDoesNotExistsException;
import co.edu.ff.orders.user.domain.ProductMapper;


public class SqlProductRepository  implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public SqlProductRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Override
    public ProductOperation insertOne(ProductOperationRequest productOperationRequest) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productOperationRequest.getName().getValue());
        parameters.put("description", productOperationRequest.getDescription().getValue());
        parameters.put("base_price", productOperationRequest.getBaseprice().getValue());
        parameters.put("tax_rate", productOperationRequest.getTaxrate().getValue());
        parameters.put("status", productOperationRequest.getProductstatus().toString());
        parameters.put("inventory_quantity",productOperationRequest.getInventoryquantity().getValue());
        Number number=0;

        number = simpleJdbcInsert.execute(parameters);
        String SQL = "SELECT * FROM PRODUCTS ORDER BY ID DESC LIMIT 1";
        Product product= jdbcTemplate.queryForObject(SQL,rowMapper);

        return ProductOperationSuccess.of(productCreate(ProductId.of(product.getProductId().getValue()),productOperationRequest));
    }

    private final Product productCreate(ProductId productId,ProductOperationRequest productOperationRequest){

        return Product.of(productId,productOperationRequest.getName(),
                productOperationRequest.getDescription(),
                productOperationRequest.getBaseprice(),
                productOperationRequest.getTaxrate(),
                productOperationRequest.getProductstatus(),
                productOperationRequest.getInventoryquantity());
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        String SQL = "SELECT ID, NAME, DESCRIPTION,BASE_PRICE ,TAX_RATE ,STATUS ,INVENTORY_QUANTITY FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {id.getValue()};
        try{
            Product product= jdbcTemplate.queryForObject(SQL,objects,rowMapper);
            return Optional.ofNullable(product);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
         }
    }


    @Override
    public List<Product> findAll() {
        String SQL = "SELECT ID, NAME, DESCRIPTION,BASE_PRICE ,TAX_RATE ,STATUS ,INVENTORY_QUANTITY FROM PRODUCTS ";
        try{
            List<Product> product= jdbcTemplate.query(SQL,new ProductMapper());
            return product;
        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public ProductOperation updateOne(ProductId productId, ProductOperationRequest productOperationRequest) {
        String SQL = "UPDATE PRODUCTS SET NAME = ?,DESCRIPTION = ?, BASE_PRICE = ?,TAX_RATE = ?,STATUS=? ,INVENTORY_QUANTITY= ? WHERE ID=?  ";
        PreparedStatementCreator psc = connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL, Statement.NO_GENERATED_KEYS);
            ps.setString(1, productOperationRequest.getName().getValue());
            ps.setString(2, productOperationRequest.getDescription().getValue());
            ps.setBigDecimal(3, productOperationRequest.getBaseprice().getValue());
            ps.setBigDecimal(4, productOperationRequest.getTaxrate().getValue());
            ps.setString(5, productOperationRequest.getProductstatus().toString());
            ps.setInt(6, productOperationRequest.getInventoryquantity().getValue());
            ps.setLong(7,productId.getValue());
            return  ps;
        };
        int value = jdbcTemplate.update(psc);
        if(value != 0){
             return ProductOperationSuccess.of(productCreate(productId,productOperationRequest));
        }
        ProductDoesNotExistsException productDoesNotExistsException = ProductDoesNotExistsException.of(productId.getValue());
        return ProductOperationFailure.of(productDoesNotExistsException);
    }

    @Override
    public ProductOperation deleteOne(ProductId productId) {
        String SQL = "DELETE FROM PRODUCTS WHERE ID = ?";
        Object[] objects = {productId.getValue()};
        int value= jdbcTemplate.update(SQL,objects);
        if(value==0) {
            ProductDoesNotExistsException productDoesNotExistsException = ProductDoesNotExistsException.of(productId.getValue());
            return ProductOperationFailure.of(productDoesNotExistsException);
        }
        return null;
    }

    @Override
    public Optional<Product> findByName(Name name) {
        String sql = "SELECT ID, NAME, DESCRIPTION, BASE_PRICE , TAX_RATE  ,INVENTORY_QUANTITY FROM PRODUCTS WHERE  NAME = ?";
        Object[] objects = {name.getValue()};
        try{
            Product product= jdbcTemplate.queryForObject(sql,objects,rowMapper);
            return Optional.ofNullable(product);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    private final RowMapper<Product> rowMapper = (resultSet, i) -> {
        ProductId id1 = ProductId.of(resultSet.getLong("ID"));
        Name name = Name.of(resultSet.getString("NAME"));
        Description description = Description.of(resultSet.getString("DESCRIPTION"));
        BasePrice basePrice = BasePrice.of(resultSet.getBigDecimal("BASE_PRICE"));
        TaxRate taxRate = TaxRate.of(resultSet.getBigDecimal("TAX_RATE"));
        ProductStatus productStatus = ProductStatus.valueOf(resultSet.getString("STATUS"));
        InventoryQuantity inventoryQuantity = InventoryQuantity.of(resultSet.getInt("INVENTORY_QUANTITY"));

        return Product.of(id1, name, description,basePrice,taxRate,productStatus,inventoryQuantity);
    };
}
