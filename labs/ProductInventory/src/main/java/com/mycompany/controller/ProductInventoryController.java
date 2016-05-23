/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Product;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ProductInventoryController {

    InventoryDao invDao = new InventoryDao();
    ConsoleIO console = new ConsoleIO();
    boolean run = true;

    public void run() {
        controllerSwitch();
        console.readString("\nGoodbye!");
    }

    public void controllerSwitch() {

        NewProductController npc = new NewProductController();
        EditorController ec = new EditorController();
        ViewerController vc = new ViewerController();
        RemoveProductController rpc = new RemoveProductController();

        while (run) {

            vc.viewMainMenu();
            checkLowStock();
            String selection = console.getString(">");

            switch (selection) {

                case "1":
                    vc.findAndViewProduct();
                    break;
                case "2":
                    vc.viewInventory();
                    break;
                case "3":
                    npc.addProduct();
                    break;
                case "4":
                    ec.editProduct();
                    break;
                case "5":
                    ec.editStockWarningNumber();
                    break;
                case "6":
                    rpc.removeProductFromInventory();
                    break;
                case "7":
                    vc.viewProductValue();
                    break;
                case "8":
                    vc.viewInventoryValue();
                    break;
                case "quit":
                case "q":
                    run = false;
                    break;
                default:
                    console.readString("That is an invalid entry!");
                    break;

            }

        }

    }

    public Product validateProduct() {

        boolean valid = false;

        while (!valid) {
            String productType = console.checkEmptyString("Options: Shirt, Pants, "
                    + "Coat, Shoe\n(Enter 0 to Quit)", "You cannot leave this field empty!");
            Product newProduct = invDao.getProductByType(productType);
            if (newProduct != null) {
                return newProduct;
            } else {
                console.readString("That is an invalid product type!");
            }
        }
        return null;
    }

    public boolean loopAgainCheck(String loopString) {

        boolean confirm = console.yesCheck("\n" + loopString + " another product? [yes/no]\n>",
                "Enter [yes/no] to proceed.");
        boolean loopAgain = confirm == true;

        return loopAgain;
    }

    public void checkLowStock() {
        ViewerController vc = new ViewerController();
        List<Product> products = invDao.decodeInventory();

        products
                .stream()
                .filter((product) -> (product.getStock() < invDao.getStockWarningNumber()))
                .forEach((product) -> {
                    vc.viewWarning(product);
                });

    }

    public void enterKeyToProceed() {
        console.getString("\nEnter any key to proceed.\n>");
    }
}
