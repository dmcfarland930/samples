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
public class RemoveProductController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    ProductInventoryController pic = new ProductInventoryController();

    public void removeProductFromInventory() {

        boolean isEmpty;
        boolean removeAgain = true;
        ViewerController vc = new ViewerController();

        while (removeAgain) {
            console.readString("What type of product would you like to remove?");
            Product delProduct = pic.validateProduct();
            if (delProduct.getProductType().equals("0")) {
                return;
            } else {
                isEmpty = vc.viewProductByQuery(delProduct);
                if (!isEmpty) {
                delProduct = enterProductIdToRemove();
                vc.viewProductDetails(delProduct);
                confirmDeleteProduct(delProduct);
                }
                removeAgain = pic.loopAgainCheck("Remove");
            }
        }

    }

    public Product enterProductIdToRemove() {

        int id = console.getInteger("Enter the Product ID to remove.\n>",
                "That is an invalid Product ID.");
        Product delProduct = invDao.get(id);
        return delProduct;
    }

    public void confirmDeleteProduct(Product delProduct) {

        boolean delete
                = console.yesCheck("\nRemove this product to inventory? [yes/no]\n>",
                        "Enter [yes/no] to proceed.");
        if (delete) {
            console.readString(delProduct.getProductName().toUpperCase()+" DELETED!");
            invDao.delete(delProduct);
        } else {
            console.readString("Removal cancelled!");
        }

    }

}
