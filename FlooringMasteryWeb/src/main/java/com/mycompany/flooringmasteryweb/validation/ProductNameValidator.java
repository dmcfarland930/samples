/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.validation;


import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dto.Product;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author apprentice
 */
public class ProductNameValidator implements ConstraintValidator<ValidProductName, String> {

    @Autowired
    private ProductDao productDao;

    private ValidProductName validProductName;
    
    @Override
    public void initialize(ValidProductName validName) {
        this.validProductName = validName;
    }

    @Override
    public boolean isValid(String product, ConstraintValidatorContext context) {

        List<Product> products = productDao.getProductList();
        boolean productDoesNotExist = true;
        
        for (Product productOnList : products) {

            if (productOnList.getProductType().equalsIgnoreCase(product)) {
                productDoesNotExist = false;
            }

        }

        return productDoesNotExist;
    }
    
    public void setProductDao(ProductDao productDao){
        
        this.productDao = productDao;
    }

    
}
