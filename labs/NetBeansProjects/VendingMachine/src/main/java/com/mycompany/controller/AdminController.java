/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.ChangeDao;
import com.mycompany.dao.InventoryDao;
import com.mycompany.dto.Change;
import com.mycompany.dto.Inventory;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class AdminController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    ChangeDao changeDao = new ChangeDao();
    Change change = new Change();
    DecimalFormat df = new DecimalFormat("0.00");

    public void adminMenuDisplay() {

        boolean run = true;
        boolean admin = true;
        while (run == true) {
            displayAdminMenu();

            String selection = console.getString("\nEnter option: ");

            switch (selection) {

                case "1":
                    //add item
                    addItem();
                    break;
                case "2":
                    //remove item
                    removeItem();
                    break;
                case "3":
                    //update price
                    updatePrice();
                    break;
                case "4":
                    //view stock;
                    showInventory(0, admin);
                    break;
                case "5":
                    //update stock
                    updateStock();
                    break;
                case "6":
                    //view record
                    showTransRecord();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    console.readString("Invalid choice!");
                    break;

            }
        }

    }

    public void addItem() {

        int stockCap = 0;
        boolean addAgain = true;

        while (addAgain == true) {
            String itemName = console.maxStringLngthChk("\nEnter the name of your item. (Enter 0 to cancel)", "You cannot leave this blank!", "\nYour max character count for this field is 20.", 20);
            if (itemName.equals("0")) {
                addAgain = false;
            } else {
                String itemPrice = console.maxStringLngthChk("Enter the price for your item.", "You cannot leave this blank!", "You cannot enter a price higher than $9999.99", 10);
                String itemStock = console.checkEmptyString("Enter the stock of this item.", "You cannot leave this blank!");

                List<Inventory> stock = invDao.decode();

                for (Inventory items : stock) {

                    stockCap++;

                }

                if (stockCap <= 12) {
                    Inventory newItem = new Inventory();

                    float itemPriceF = Float.parseFloat(itemPrice);
                    int itemStockF = Integer.parseInt(itemStock);

                    newItem.setItemName(itemName);
                    newItem.setCost(itemPriceF);
                    newItem.setStock(itemStockF);

                    console.readString("\n" + itemName + " added to inventory!");
                    invDao.create(newItem);

                    boolean confirm = console.yesCheck("\nAdd another item? [yes/no]\n>", "Enter [yes/no] to proceed.");
                    addAgain = confirm == true;
                } else {
                    console.readString("This machine cannot hold more than 12 items!");
                }
            }
        }

    }

    public void removeItem() {

        boolean removeAgain = true;
        boolean found;

        while (removeAgain == true) {

            showInventory(0, true);
            String itemId = console.checkEmptyString("\nEnter the ID of your item to remove. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                removeAgain = false;
            } else {
                try {

                    found = deleteItemConfirm(itemId);
                    removeAgain = removeAgainReturn(found);

                } catch (Exception ex) {
                    console.readString("\nThat is an invalid ID entry.");
                }

            }
        }
    }

    public void updatePrice() {

        boolean updateAgain = true;
        boolean found;

        showInventory(0, true);

        while (updateAgain == true) {

            String itemId = console.checkEmptyString("\nEnter the ID of your item to update its price. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                updateAgain = false;
            } else {

                try {

                    found = updateConfirm(itemId, "price", "' PRICE CHANGED TO $");

                    updateAgain = updateAgainConfirm(found, "price");

                } catch (Exception ex) {
                    console.readString("That is an invalid ID entry.");
                }

            }
        }
    }

    public void updateStock() {

        boolean updateAgain = true;
        boolean found;

        showInventory(0, true);

        while (updateAgain == true) {
            String itemId = console.checkEmptyString("\nEnter the ID of your item to update its stock. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                updateAgain = false;
            } else {
                try {

                    found = updateConfirm(itemId, "stock", "' STOCK CHANGED TO x ");

                    updateAgain = updateAgainConfirm(found, "item's stock");

                } catch (Exception ex) {
                    console.readString("That is an invalid ID entry.");
                }

            }
        }
    }

    public boolean deleteItemConfirm(String itemId) {

        boolean found = false;
        int entryId = Integer.parseInt(itemId);
        List<Inventory> items = invDao.decode();

        for (Inventory delItem : items) {

            String delItemName = delItem.getItemName();

            int delId = delItem.getInvId();

            if (delId == entryId) {
                found = true;
                boolean confirm = console.yesCheck("\nAre you sure you want to remove " + delItemName + "? [yes/no]", "Enter [yes/no] to proceed.");

                if (confirm == true) {

                    invDao.delete(delItem);
                    String upperName = delItemName.toUpperCase();
                    console.readString("'" + upperName + "' DELETED");
                }

            }
        }
        return found;

    }

    public boolean removeAgainReturn(boolean found) {

        boolean confirm;
        boolean removeAgain = false;

        if (found == true) {
            confirm = console.yesCheck("\nDelete another item? [yes/no]", "Enter [yes/no] to proceed.");
            removeAgain = confirm == true;
        }
        if (!found) {
            console.readString("\nItem not found!");
            removeAgain = true;

        }
        if (removeAgain == true) {
            confirm = console.yesCheck("\nSearch again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
            removeAgain = confirm == true;
        }

        return removeAgain;

    }

    public boolean updateConfirm(String itemId, String updatedProperty, String confirmationMessage) {

        boolean found = false;
        float newItemPrice;
        int newStock;
        int entryId = Integer.parseInt(itemId);

        List<Inventory> items = invDao.decode();

        for (Inventory foundItem : items) {

            if (foundItem.getInvId() == entryId) {

                found = true;
                invDao.get(entryId);
                boolean confirm = console.yesCheck("Change " + updatedProperty + " for " + foundItem.getItemName() + "? [yes/no]\n>", "Enter [yes/no] to proceed.");

                if (confirm == true) {

                    String update = console.checkEmptyString("Enter the new " + updatedProperty + " for your item.", "You cannot leave this blank!");

                    if (updatedProperty.equalsIgnoreCase("price")) {
                        newItemPrice = Float.parseFloat(update);
                        foundItem.setCost(newItemPrice);
                        String upperName = foundItem.getItemName().toUpperCase();

                        console.readString("'" + upperName + confirmationMessage + df.format(newItemPrice));
                    } else {
                        newStock = Integer.parseInt(update);
                        foundItem.setStock(newStock);
                        String upperName = foundItem.getItemName().toUpperCase();

                        console.readString("'" + upperName + confirmationMessage + df.format(newStock));

                    }
                    invDao.update(foundItem);
                }

            }
        }
        return found;
    }

    public boolean updateAgainConfirm(boolean found, String property) {

        boolean confirm;
        boolean updateAgain = false;
        if (found == true) {
            confirm = console.yesCheck("Update another " + property + " [yes/no]", "Enter [yes/no] to proceed.");
            updateAgain = confirm == true;

        }

        if (!found) {
            console.readString("\nItem not found!\n");
            updateAgain = true;
        }

        if (updateAgain == true) {
            confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
            updateAgain = confirm == true;
        }

        return updateAgain;
    }

    public void showInventory(float funds, boolean ad) {

        console.readString("\n+============================================+");
        console.readString("  ID #  | PRICE   | NAME              | STOCK ");
        console.readString("----------------------------------------------");
        List<Inventory> itemsOnFile = invDao.decode();

        getItemsList(itemsOnFile);
        if (!ad) {
            console.readString("----------------------------------------------");
            console.readString("                    FUNDS ENTERED: $" + df.format(funds));
            console.readString("----------------------------------------------");
            console.readString("  TO OPERATE:");
            console.readString("         Enter ID to purchase Item");
            console.readString("         Enter '+' to enter funds");
            console.readString("         Enter '-' to return funds");
            console.readString("         Enter 'q' to quit");
        }
        console.readString("+============================================+");

    }

    public void showTransRecord() {

        List<Change> transactions = changeDao.decode();

        console.readString("+=====================================================================================================+");
        console.readString("  TRANS. ID #  | PRICE   | NAME              | STOCK | AMOUNT ENTERED | AMOUNT SPENT | AMOUNT RETURNED");
        console.readString("------------------------------------------------------------------------------------------------------");
        getTransList(transactions);
        console.readString("+====================================================================================================+");

    }

    public void displayAdminMenu() {
        console.readString("\n ----------------------------------");
        console.readString(" Welcome to the secret admin level!");
        console.readString(" What would you like to do?");
        console.readString("  1) Add item to inventory");
        console.readString("  2) Remove item from inventory");
        console.readString("  3) Update price of item");
        console.readString("  4) View Stock");
        console.readString("  5) Update Stock");
        console.readString("  6) View transaction record");
        console.readString(" ----------------------------------");
        console.readString("  0) Quit");
    }

    public void getItemsList(List<Inventory> inventoryList) {

        for (Inventory itemsSaved : inventoryList) {

            int savedId = itemsSaved.getInvId();
            float savedPrice = itemsSaved.getCost();
            String savedName = itemsSaved.getItemName();
            int savedStock = itemsSaved.getStock();

            showItemsInList(savedId, savedPrice, savedName, savedStock);
        }

    }

    public void getTransList(List<Change> trans) {

        for (Change transOnFile : trans) {

            int savedId = transOnFile.getTransId();
            String savedName = transOnFile.getInv().getItemName();
            float savedPrice = transOnFile.getInv().getCost();
            int savedStock = transOnFile.getInv().getStock();
            float savedIn = transOnFile.getEnteredAmount();
            float savedSpent = transOnFile.getSpentAmount();
            float savedOut = transOnFile.getChangeAmount();

            showTransList(savedId, savedPrice, savedName, savedStock, savedIn, savedSpent, savedOut);
        }

    }

    public void showItemsInList(int itemId, float itemPrice, String itemName, int itemStock) {

        console.padRight("  " + itemId, 8);
        console.padRight("| $" + df.format(itemPrice), 10);
        console.padRight("| " + itemName, 20);
        System.out.print("| " + (itemStock) + "\n");

    }

    public void showTransList(int itemId, float itemPrice, String itemName, int itemStock, float in, float spent, float out) {

        console.padRight("   " + itemId, 15);
        console.padRight("| $" + df.format(itemPrice), 10);
        console.padRight("| " + itemName, 20);
        console.padRight("| " + (itemStock - 1), 8);
        console.padRight("| $" + df.format(in), 17);
        console.padRight("| $" + df.format(spent), 15);
        console.readString("| $" + df.format(out) + "\n");

    }

}
