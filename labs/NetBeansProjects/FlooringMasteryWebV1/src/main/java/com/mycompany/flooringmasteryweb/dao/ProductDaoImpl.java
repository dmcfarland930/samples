/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ProductDaoImpl implements ProductDao {

    boolean isTest;
    FlooringData fd;
    ProductXmlDao pXml;
    List<Product> productList = new ArrayList();
    String productType;
    boolean testMode;
    boolean csv;

    public ProductDaoImpl(FlooringData fData, ProductXmlDao pXmlDao) {

        this.fd = fData;
        this.pXml = pXmlDao;
        testMode = fd.isTestMode();
        csv = fd.isCsv();
        if (csv == true) {
            productList = fd.productDecode();

        } else {
            productList = pXml.readProducts();
        }

    }

    @Override
    public Product create(Product product) {

        productList.add(product);

        if (!testMode) {
            fd.productEncode(productList);
        }
        return product;

    }

    @Override
    public Product get(String productType) {

        for (Product myProduct : productList) {
            if (myProduct.getProductType().equals(productType)) {
                return myProduct;
            }
        }
        return null;
    }

    @Override
    public void update(Product product, String productType) {

        for (Product myProduct : productList) {
            if (myProduct.getProductType().equals(productType)) {
                productList.remove(myProduct);
                productList.add(product);
                break;

            }

        }
        if (!testMode) {
            fd.productEncode(productList);
        }

    }

    @Override
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

    @Override
    public double getCostPerSqFt(String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.readProducts();
        }
        double productCost = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                productCost = productOnFile.getCostPerSqFt();
            }

        }
        return productCost;
    }

    @Override
    public double getLaborCostPerSqFt(String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.readProducts();
        }
        double laborCost = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                laborCost = productOnFile.getLaborCostPerSqFt();
            }

        }
        return laborCost;
    }

    @Override
    public double calculateTotalCostPerSqFt(double area, String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.readProducts();
        }
        double costPerSqFt = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                costPerSqFt = area * productOnFile.getCostPerSqFt();
            }

        }

        return costPerSqFt;
    }

    @Override
    public double calculateTotalLaborCost(double area, String productType) {
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.readProducts();
        }
        double laborCostPerSqFt = 0;
        for (Product productOnFile : productListOnFile) {

            if (productType.equalsIgnoreCase(productOnFile.getProductType())) {
                laborCostPerSqFt = area * productOnFile.getLaborCostPerSqFt();
            }

        }
        return laborCostPerSqFt;

    }

    @Override
    public boolean validateProductType(String productType, boolean edit) {

        boolean valid = false;
        List<Product> productListOnFile;

        if (csv == true) {
            productListOnFile = fd.productDecode();

        } else {
            productListOnFile = pXml.readProducts();
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

    @Override
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @Override
    public void setCsv(boolean csvXml) {
        this.csv = csvXml;
    }

    public List<Product> getProductList() {
        return productList;
    }

    

}
