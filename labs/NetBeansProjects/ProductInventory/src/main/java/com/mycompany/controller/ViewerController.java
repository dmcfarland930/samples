/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Product;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author apprentice
 */
public class ViewerController {

    ConsoleIO console = new ConsoleIO();
    ProductInventoryController pic = new ProductInventoryController();
    InventoryDao invDao = new InventoryDao();
    DecimalFormat df = new DecimalFormat("0.00");

    public void findAndViewProduct() {

        boolean findAgain = true;

        while (findAgain) {
            console.readString("\nWhat type of product would you like to find?\n");
            Product productSearch = pic.validateProduct();
            if (productSearch.getProductType().equals("0")) {
                return;
            } else {

                viewProductByQuery(productSearch);
                askToViewProductDetails();
                findAgain = pic.loopAgainCheck("Find");

            }
        }

    }

    public void viewInventory() {

        viewInventoryByType();
        askToViewProductDetails();

    }

    public void loopAndViewProducts(List<Product> products) {
        products
                .stream()
                .forEach((product) -> {
                    viewNameAndId(product);
                });
    }

    public void viewNameAndId(Product product) {

        console.readString("ID: " + product.getProductId() + " | Name: " + product.getProductName());

    }

    public void viewInventoryByType() {

        Map<String, List> productMap = invDao.createMapByProductType();

        try {

            Set<String> productTypes = productMap.keySet();
            console.readStringSameLine("\n");
            productTypes.stream().map((key) -> {
                if (!key.equalsIgnoreCase("pants")) {
                    console.readString(key + "s: ");
                } else {
                    console.readString(key + ": ");
                }
                return key;
            }).map((key) -> productMap.get(key)).forEach((products) -> {
                loopAndViewProducts(products);
            });
        } catch (Exception ex) {

            console.readString("No products were found by that type!");

        }

    }

    public boolean viewProductByQuery(Product productSearch) {

        boolean isEmpty = false;
        List<Product> listOfProductsByType = invDao.getProductListByType(productSearch);

        if (!listOfProductsByType.isEmpty()) {

            if (productSearch.getProductType().equalsIgnoreCase("pants")) {
                console.readString("\n" + productSearch.getProductType() + ":");
            } else {
                console.readString("\n" + productSearch.getProductType() + "s:");
            }
            loopAndViewProducts(listOfProductsByType);
        } else {

            if (productSearch.getProductType().equalsIgnoreCase("pants")) {
                console.readString("\nThere are no " + productSearch.getProductType().toLowerCase() + " in stock!");
            } else {
                console.readString("\nThere are no " + productSearch.getProductType().toLowerCase() + "s in stock!");
            }
            isEmpty = true;
        }
        return isEmpty;
    }

    public void viewProductValue() {

        boolean findAgain = true;

        while (findAgain) {
            console.readString("\nEnter a product type to find its value.\n");
            Product productSearch = pic.validateProduct();
            if (productSearch.getProductType().equals("0")) {
                return;
            } else {

                viewProductByQuery(productSearch);
                askForIdToViewValue();
                findAgain = pic.loopAgainCheck("Find");

            }
        }

    }

    public void askForIdToViewValue() {

        int id = console.getInteger("\nEnter the Product ID to view its value.\n>", "That is an invalid Product ID.");

        Product foundProduct = invDao.get(id);
        double value = invDao.calculateProductValues(foundProduct);
        console.readString("\nYou have $"+df.format(value)+" worth of inventory for "+foundProduct.getProductName()+".");
        pic.enterKeyToProceed();

    }
    
    public void viewInventoryValue(){
        
        double value = invDao.calculateInventoryValue();
        console.readString("\nYour inventory is worth $"+df.format(value)+".");
        pic.enterKeyToProceed();
        
    }

    public void viewMainMenu() {

        console.readString("\n--------------------");
        console.readString(" Product Inventory ");
        console.readString("--------------------");
        console.readString(" 1) Find Product");
        console.readString(" 2) View Inventory");
        console.readString(" 3) Add Product");
        console.readString(" 4) Edit Product");
        console.readString(" 5) Edit Stock Alert");
        console.readString(" 6) Remove Product");
        console.readString(" 7) View Product Worth");
        console.readString(" 8) View Inventory Worth");
        console.readString(" ------------------ ");
        console.readString("           [quit]");
        console.readString("--------------------");

    }

    public void viewEditMenu() {

        console.readString("--------------------");
        console.readString(" Editing Product... ");
        console.readString("--------------------");
        console.readString(" 1) Edit Name");
        console.readString(" 2) Edit Size");
        console.readString(" 3) Edit Price");
        console.readString(" 4) Edit Stock");
        console.readString(" 5) View Details");
        console.readString(" ------------------ ");
        console.readString("           [quit]");
        console.readString("--------------------");

    }

    public void askToViewProductDetails() {

        boolean confirm;
        confirm = console.yesCheck("\nWould you like to view a product's details? [yes/no]\n>",
                "Enter [yes/no] to proceed.");
        if (confirm) {
            enterIdToViewDetails(false);
        }

    }

    public void enterIdToViewDetails(boolean isEmpty) {

        if (!isEmpty) {
            int id = console.getInteger("Enter the Product ID to view its details.\n>", "That is an invalid Product ID.");

            Product foundProduct = invDao.get(id);
            viewProductDetails(foundProduct);
            pic.enterKeyToProceed();
        }

    }

    public void viewProductDetails(Product product) {

        console.readString("\n+----------------------------+");
        console.readString("  ID: " + product.getProductId()
                + "\n+----------------------------+"
                + "\n   Type: " + product.getProductType()
                + "\n   Name: " + product.getProductName()
                + "\n   Size: " + product.getSize()
                + "\n  Price: $" + df.format(product.getPrice())
                + "\n  Stock: " + product.getStock()
                + "\n+----------------------------+");

    }

    void viewWarning(Product product) {
        console.readString("<!>Current stock for " + product.getProductName() + " is " + product.getStock() + "! Update stock soon<!>");
    }

}
