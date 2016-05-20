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
public class EditorController {

    ConsoleIO console = new ConsoleIO();
    ProductInventoryController pic = new ProductInventoryController();
    InventoryDao invDao = new InventoryDao();

    public void editProduct() {

        ViewerController vc = new ViewerController();
        boolean editAgain = true;
        boolean isEmpty;

        while (editAgain) {
            console.readString("What type of product would you like to edit?");
            Product editProduct = pic.validateProduct();
            if (editProduct.getProductType().equals("0")) {
                return;
            } else {

                isEmpty = vc.viewProductByQuery(editProduct);
                if (!isEmpty) {
                    enterProductIdToEdit();
                }
                editAgain = pic.loopAgainCheck("Edit");

            }
        }
    }

    public void enterProductIdToEdit() {

        int id = console.getInteger("Enter the Product ID to edit.\n>",
                "That is an invalid Product ID.");
        Product editProduct = invDao.get(id);
        editSwitch(editProduct);

    }

    public void editSwitch(Product editProduct) {

        ViewerController vc = new ViewerController();
        boolean keepEditing = true;

        while (keepEditing) {
            vc.viewEditMenu();
            String selection = console.getString(">");
            switch (selection) {
                case "1":
                    editProduct = editName(editProduct);
                    break;
                case "2":
                    editProduct = editSize(editProduct);
                    break;
                case "3":
                    editProduct = editPrice(editProduct);
                    break;
                case "4":
                    editProduct = editStock(editProduct);
                    break;
                case "5":
                    vc.viewProductDetails(editProduct);
                    pic.enterKeyToProceed();
                    break;
                case "q":
                case "quit":
                    saveEditChanges(editProduct);
                    keepEditing = false;
                    break;
                default:
                    console.readString("That is an invalid choice!");
                    break;
            }

        }

    }

    public Product editName(Product editProduct) {
        editProduct.setProductName(console.getString("Enter the name of your product. "
                + "(Currently: " + editProduct.getProductName() + ")\n>"));
        console.readString("\nName updated to \"" + editProduct.getProductName() + "\"");
        pic.enterKeyToProceed();
        return editProduct;
    }

    public Product editSize(Product editProduct) {
        editProduct.setSize(console.getString("Enter the size of your product. "
                + "(Currently: " + editProduct.getSize() + ")\n>"));
        console.readString("\nSize updated to \"" + editProduct.getSize() + "\"");
        pic.enterKeyToProceed();
        return editProduct;
    }

    public Product editPrice(Product editProduct) {
        editProduct.setPrice(console.getDouble("Enter the price of your product. "
                + "(Currently: " + editProduct.getPrice() + ")\n>", "That is an invalid price!"));
        console.readString("\nPrice updated to \"$" + editProduct.getPrice() + "\"");
        pic.enterKeyToProceed();
        return editProduct;
    }

    public Product editStock(Product editProduct) {

        boolean validStock = false;

        while (!validStock) {
            editProduct.setStock(console.getInteger("Update the stock of your product. "
                    + "(Currently: " + editProduct.getStock() + ")\n>", "That is an invalid entry!"));
            if (editProduct.getStock() <= invDao.getStockWarningNumber()) {
                console.readString("\nYour stock is below " + invDao.getStockWarningNumber() + "!");
                console.readString("You will need to update your stock soon!");
                console.readString("\nCurrent stock of "+editProduct.getProductName()+": "+editProduct.getStock()+".\n");
                validStock = true;
            } else if (editProduct.getStock() < 0) {
                console.readString("Invalid entry! You cannot remove more products than you have!");
            } else {
                console.readString("\nCurrent stock of "+editProduct.getProductName()+": "+editProduct.getStock()+".");
                validStock = true;
            }
        }
        pic.enterKeyToProceed();
        return editProduct;
    }

    public void editStockWarningNumber() {

        console.readString("You will be alerted when stock reaches " + invDao.getStockWarningNumber() + ".");
        boolean confirm = console.yesCheck("Would you like to change when you will be alerted? [yes/no]\n>",
                "Enter [yes/no] to proceed.");
        if (confirm) {
            int warningNumber = console.getInteger("Enter the amount for when you will be alerted of a"
                    + "low stock.\n>", "That is not a valid entry!");
            invDao.setStockWarningNumber(warningNumber);
        }
    }

    private void saveEditChanges(Product editProduct) {

        ViewerController vc = new ViewerController();
        vc.viewProductDetails(editProduct);
        boolean confirm = console.yesCheck("Save this product? [yes/no]\n>",
                "Enter [yes/no] to proceed.");
        if (confirm) {
            invDao.update(editProduct);
            console.readString("Save confirmed!");
        } else {
            console.readString("Save cancelled!");
        }

    }

}
