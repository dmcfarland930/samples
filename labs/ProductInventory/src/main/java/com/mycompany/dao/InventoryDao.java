/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Coat;
import com.mycompany.dto.Pants;
import com.mycompany.dto.Product;
import com.mycompany.dto.Shirt;
import com.mycompany.dto.Shoe;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class InventoryDao {

    private List<Product> productList = new ArrayList();
    private int nextId = 1000;
    private int stockWarningNumber;

    public InventoryDao() {

        stockWarningNumber = decodeStockWarning();
        productList = decodeInventory();

    }

    public Product create(Product product) {

        product.setProductId(nextId);
        nextId++;
        productList.add(product);

        encodeInventory();

        return product;

    }

    public Product get(String productName) {

        return productList
                .stream()
                .filter(a -> a.getProductName().equals(productName))
                .collect(Collectors.toList()).get(0);

    }

    public Product get(int id) {

        productList = decodeInventory();
        return productList
                .stream()
                .filter(a -> a.getProductId() == id)
                .collect(Collectors.toList()).get(0);
    }

    public void update(Product product) {

        List<Product> modifiedDvdList = decodeInventory();
        productList = modifiedDvdList
                .stream()
                .filter(a -> a.getProductId() != product.getProductId())
                .collect(Collectors.toList());

        productList.add(product);

        encodeInventory();

    }

    public void delete(Product product) {

        List<Product> modifiedInventoryList = decodeInventory();
        productList = modifiedInventoryList
                .stream()
                .filter(a -> a.getProductId() != product.getProductId())
                .collect(Collectors.toList());

        encodeInventory();

    }

    public Map createMapByProductType() {

        productList = decodeInventory();
        return productList
                .stream()
                .collect(Collectors.groupingBy(p -> p.getProductType()));

    }

    public double calculateProductValues(Product product) {

        double productValue;

        productValue = product.getPrice() * product.getStock();

        return productValue;

    }

    public double calculateInventoryValue() {

        double inventoryValue = 0;
        inventoryValue = productList
                .stream()
                .map((product) -> product.getPrice() * product.getStock())
                .reduce(inventoryValue, (accumulator, _item) -> accumulator + _item);

        return inventoryValue;
    }

    public void setStockWarningNumber(int stockWarningNumber) {
        this.stockWarningNumber = stockWarningNumber;
        encodeStockWarning();
    }
    
    public int getStockWarningNumber() {
        return stockWarningNumber = decodeStockWarning();
    }


    public void encodeInventory() {

        final String TOKEN = "::";

        try {
            PrintWriter out = new PrintWriter(new FileWriter("productList.txt"));

            productList
                    .stream()
                    .forEach((Product myProduct) -> {

                        out.print(myProduct.getProductType());
                        out.print(TOKEN);
                        out.print(myProduct.getProductId());
                        out.print(TOKEN);
                        out.print(myProduct.getProductName());
                        out.print(TOKEN);
                        out.print(myProduct.getPrice());
                        out.print(TOKEN);
                        out.print(myProduct.getSize());
                        out.print(TOKEN);
                        out.print(myProduct.getStock());
                        out.println();

                    });
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List decodeInventory() {

        Scanner sc = null;
        List<Product> products = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("productList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split("::");
                Product myProduct = getProductByType(stringParts[0]);
                myProduct.setProductType(myProduct.getProductType());

                int id = Integer.parseInt(stringParts[1]);
                if (id == nextId) {
                    nextId++;
                }

                myProduct.setProductId(id);
                myProduct.setProductName(stringParts[2]);
                double price = Double.parseDouble(stringParts[3]);
                myProduct.setPrice(price);
                myProduct.setSize(stringParts[4]);
                int stock = Integer.parseInt(stringParts[5]);
                myProduct.setStock(stock);

                products.add(myProduct);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return products;
    }

    public List<Product> getProductListByType(Product productQuery) {

        productList = decodeInventory();
        return productList
                .stream()
                .filter(p -> p.getProductType().equals(productQuery.getProductType()))
                .collect(Collectors.toList());

    }

    public Product getProductByType(String productFromFile) {

        String productFromFileIgnoreCase = productFromFile.toLowerCase();

        switch (productFromFileIgnoreCase) {
            case "shirt":
                Product shirt = new Shirt();
                shirt.setProductType("Shirt");
                return shirt;
            case "pants":
                Product pants = new Pants();
                pants.setProductType("Pants");
                return pants;
            case "coat":
                Product coat = new Coat();
                coat.setProductType("Coat");
                return coat;
            case "shoe":
                Product shoe = new Shoe();
                shoe.setProductType("Shoe");
                return shoe;
            case "0":
                Product goBack = new Shirt();
                goBack.setProductType("0");
                return goBack;
            default:
                break;

        }

        return null;
    }

    public void encodeStockWarning() {

        try {
            PrintWriter out = new PrintWriter(new FileWriter("stockWarning.txt"));

            out.print(stockWarningNumber);
            out.flush();
            out.close();

        } catch (IOException ex) {

            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Integer decodeStockWarning() {

        Scanner sc = null;
        int stock = 0;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("stockWarning.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split("::");
                stock = Integer.parseInt(stringParts[0]);
                

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return stock;
    }

}
