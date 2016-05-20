/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Product;

/**
 *
 * @author apprentice
 */
public class NewProductController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    ProductInventoryController pic = new ProductInventoryController();

    public void addProduct() {

        boolean addAgain = true;
        ViewerController vc = new ViewerController();

        while (addAgain) {
            console.readString("What type of product would you like to add?");
            Product newProduct = pic.validateProduct();
            if (newProduct.getProductType().equals("0")) {
                return;
            } else {
                newProduct = setNewProductProperties(newProduct);
                invDao.create(newProduct);
                vc.viewProductDetails(newProduct);
                confirmNewProduct(newProduct);
                addAgain = pic.loopAgainCheck("Add");
            }
        }
    }

    public void confirmNewProduct(Product newProduct) {
        boolean addProduct
                = console.yesCheck("\nAdd this product to inventory? [yes/no]\n>",
                        "Enter [yes/no] to proceed.");
        if (addProduct) {
            console.readString("Addition confirmed!");
        } else {
            console.readString("Addition cancelled!");
            invDao.delete(newProduct);
        }
    }

    public Product setNewProductProperties(Product newProduct) {
        newProduct.setProductName(console.checkEmptyString("Enter the name of your product:",
                "You cannot leave this field blank!"));
        newProduct.setSize(console.checkEmptyString("Enter the size of your product:",
                "You cannot leave this field blank!"));
        String priceString = console.checkEmptyString("Enter the price of your product:",
                "You cannot leave this field blank!");
        newProduct.setPrice(Double.parseDouble(priceString));
        String stockString = console.checkEmptyString("Enter your product stock:",
                "You cannot leave this field blank!");
        newProduct.setStock(Integer.parseInt(stockString));
        return newProduct;
    }

}
