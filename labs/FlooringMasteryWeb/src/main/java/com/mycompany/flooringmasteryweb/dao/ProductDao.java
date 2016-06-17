/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.dto.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProductDao {

    double calculateTotalCostPerSqFt(double area, String productType);

    double calculateTotalLaborCost(double area, String productType);

    Product create(Product product);

    Product get(Integer id);
    
    void update(Product product);
    
    void delete(Product product);
    
    List<Product> getProductList();

    double getCostPerSqFt(String productType);

    double getLaborCostPerSqFt(String productType);

    void setCsv(boolean csvXml);

    void setTestMode(boolean testMode);
    
    boolean validateProductType(String productType, boolean edit);


}
