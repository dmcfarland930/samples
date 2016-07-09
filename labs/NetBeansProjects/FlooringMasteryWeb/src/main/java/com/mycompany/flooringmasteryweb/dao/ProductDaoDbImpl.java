/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ProductDaoDbImpl implements ProductDao {

    private static final String SQL_INSERT_PRODUCT = "INSERT INTO product (name, material_cost, labor_cost) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_PRODUCT = "UPDATE product SET name = ?, material_cost = ?, labor_cost = ? WHERE id = ?";
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";
    private static final String SQL_GET_PRODUCT = "SELECT * FROM product WHERE id = ?";
    private static final String SQL_GET_PRODUCT_LIST = "SELECT * FROM product";

    private JdbcTemplate jdbcTemplate;

    public ProductDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Product create(Product product) {

        jdbcTemplate.update(SQL_INSERT_PRODUCT,
                product.getProductType(),
                product.getCostPerSqFt(),
                product.getLaborCostPerSqFt());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        product.setId(id);

        return product;
    }

    @Override
    public void update(Product product) {

        jdbcTemplate.update(SQL_UPDATE_PRODUCT,
                product.getProductType(),
                product.getCostPerSqFt(),
                product.getLaborCostPerSqFt(),
                product.getId());

    }

    @Override
    public void delete(Product product) {

        jdbcTemplate.update(SQL_DELETE_PRODUCT,
                product.getId());

    }

    @Override
    public Product get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_PRODUCT, new ProductMapper(), id);

    }

    @Override
    public List<Product> getProductList() {

        return jdbcTemplate.query(SQL_GET_PRODUCT_LIST, new ProductMapper());

    }

    @Override
    public double calculateTotalCostPerSqFt(double area, String productType) {

        List<Product> products = getProductList();
        double costPerSqFt = 0;
        for (Product product : products) {

            if (productType.equalsIgnoreCase(product.getProductType())) {
                costPerSqFt = area * product.getCostPerSqFt();
            }

        }

        return costPerSqFt;

    }

    @Override
    public double calculateTotalLaborCost(double area, String productType) {

        List<Product> products = getProductList();
        double laborCostPerSqFt = 0;
        
        for (Product product : products) {

            if (productType.equalsIgnoreCase(product.getProductType())) {
                laborCostPerSqFt = area * product.getLaborCostPerSqFt();
            }

        }
        return laborCostPerSqFt;

    }

    @Override
    public double getCostPerSqFt(String productType) {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public double getLaborCostPerSqFt(String productType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCsv(boolean csvXml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTestMode(boolean testMode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateProductType(String productType, boolean edit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int i) throws SQLException {

            Product product = new Product();

            product.setId(rs.getInt("id"));
            product.setProductType(rs.getString("name"));
            product.setCostPerSqFt(rs.getDouble("material_cost"));
            product.setLaborCostPerSqFt(rs.getDouble("labor_cost"));

            return product;

        }

    }

}
