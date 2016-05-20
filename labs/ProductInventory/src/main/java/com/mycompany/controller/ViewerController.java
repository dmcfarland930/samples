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
        boolean isEmpty;

        while (findAgain) {
            console.readString("What type of product would you like to find?");
            Product productSearch = pic.validateProduct();
            if (productSearch.getProductType().equals("0")) {
                return;
            } else {

                isEmpty = viewProductByQuery(productSearch);
                enterIdToViewDetails(isEmpty);
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

        console.readString("ID: " + product.getProductId() + " | " + product.getProductName());

    }

    public void viewInventoryByType() {

        Map<String, List> productMap = invDao.createMapByProductType();

        try {

            Set<String> productTypes = productMap.keySet();
            for (String key : productTypes) {
                console.readString(key + "s: ");
                List<Product> products = productMap.get(key);
                loopAndViewProducts(products);

            }
        } catch (Exception ex) {

            console.readString("No products were found by that type!");

        }

    }

    public boolean viewProductByQuery(Product productSearch) {

        boolean isEmpty = false;
        List<Product> listOfProductsByType = invDao.getProductListByType(productSearch);

        if (!listOfProductsByType.isEmpty()) {
            loopAndViewProducts(listOfProductsByType);
        } else {
            console.readString("There are no products of that type in stock!");
            isEmpty = true;
        }
        return isEmpty;
    }

    public void viewMainMenu() {

        console.readString("--------------------");
        console.readString(" Product Inventory ");
        console.readString("--------------------");
        console.readString(" 1) Find Product");
        console.readString(" 2) View Inventory");
        console.readString(" 3) Add Product");
        console.readString(" 4) Edit Product");
        console.readString(" 5) Edit Stock Alert");
        console.readString(" 6) Remove Product");
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
        console.readString("<!>Current stock for "+product.getProductName()+" is "+product.getStock()+"! Update stock soon<!>");
    }

}
