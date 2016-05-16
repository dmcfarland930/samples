/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ProductDao {

    boolean isTest;
    FlooringData fd = new FlooringData();
    ProductXmlDao pXml = new ProductXmlDao();
    List<Product> productList = new ArrayList();
    String productType;
    boolean testMode;
    boolean csv;

    public ProductDao() {

        if (csv == true) {
            productList = fd.productDecode();

        } else {
            productList = pXml.read();
        }

    }

    public Product create(Product product) {

        productList.add(product);

        if (!testMode) {
            fd.productEncode(productList);
        }
        return product;

    }

    public Product get(String productType) {

        for (Product myProduct : productList) {
            if (myProduct.getProductType().equals(productType)) {
                return myProduct;
            }
        }
        return null;
    }

    public void update(Product product) {

        for (Product myProduct : productList) {
            if (myProduct.getProductType().equals(product.getProductType())) {
                productList.remove(myProduct);
                productList.add(product);
                break;

            }

        }
        if (!testMode) {
            fd.productEncode(productList);
        }

    }

    public void delete(Product product) {

        Product found = null;

        for (Product myProduct : productList) {

            if (myProduct.getProductType().equals(product.getProductType())) {

                found = myProduct;
                break;
            }
        }
        productList.remove(found);

        if (!testMode) {
            fd.productEncode(productList);
        }
    }

    public double getCostPerSqFt(String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.read();
        }
        double productCost = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                productCost = productOnFile.getCostPerSqFt();
            }

        }
        return productCost;
    }

    public double getLaborCostPerSqFt(String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.read();
        }
        double laborCost = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                laborCost = productOnFile.getLaborCostPerSqFt();
            }

        }
        return laborCost;
    }

    public double calculateTotalCostPerSqFt(double area, String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.read();
        }
        double costPerSqFt = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                costPerSqFt = area * productOnFile.getCostPerSqFt();
            }

        }

        return costPerSqFt;
    }

    public double calculateTotalLaborCost(double area, String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.read();
        }
        double laborCostPerSqFt = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                laborCostPerSqFt = area * productOnFile.getLaborCostPerSqFt();
            }

        }
        return laborCostPerSqFt;

    }

    public boolean validateProductType(String productType, boolean edit) {

        boolean valid = false;
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.read();
        }
        
        for (Product productOnFile : productListOnFile) {

            if (edit == true) {
                if (productType.equalsIgnoreCase(productOnFile.getProductType()) || productType.equals("0") || productType.isEmpty()) {
                    valid = true;
                }
            } else if (productType.equalsIgnoreCase(productOnFile.getProductType()) || productType.equals("0")) {
                valid = true;
            }

        }
        return valid;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void setCsv(boolean csvXml) {
        this.csv = csvXml;
    }

}
