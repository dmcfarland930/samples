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
            System.out.println("\nWelcome to the secret admin level!");
            System.out.println("What would you like to do?");
            System.out.println("  1) Add item to inventory");
            System.out.println("  2) Remove item from inventory");
            System.out.println("  3) Update price of item");
            System.out.println("  4) View Stock");
            System.out.println("  5) Update Stock");
            System.out.println("  6) View transaction record");
            System.out.println(" ----------------------------------");
            System.out.println("  0) Quit");

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
                    transRecord();
                    break;
                case "0":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;

            }
        }

    }

    public void addItem() {

        boolean addAgain = true;

        while (addAgain == true) {
            String itemName = console.checkEmptyString("Enter the name of your item. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemName.equals("0")) {
                addAgain = false;
            } else {
                String itemPrice = console.checkEmptyString("Enter the price for your item.", "You cannot leave this blank!");
                String itemStock = console.checkEmptyString("Enter the stock of this item.", "You cannot leave this blank!");

                Inventory newItem = new Inventory();

                float itemPriceF = Float.parseFloat(itemPrice);
                int itemStockF = Integer.parseInt(itemStock);

                newItem.setItemName(itemName);
                newItem.setCost(itemPriceF);
                newItem.setStock(itemStockF);

                System.out.println("\n" + itemName + " added to inventory!");
                invDao.create(newItem);

                boolean confirm = console.yesCheck("\nAdd another item? [yes/no]\n>", "Enter [yes/no] to proceed.");
                addAgain = confirm == true;
            }
        }

    }

    public void removeItem() {

        boolean removeAgain = true;
        boolean found;
        showInventory(0, true);

        while (removeAgain == true) {
            found = false;
            String itemId = console.checkEmptyString("\nEnter the ID of your item to remove. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                removeAgain = false;
            } else {
                try {
                    int entryId = Integer.parseInt(itemId);
                    List<Inventory> items = invDao.decode();

                    for (Inventory delItem : items) {

                        String delItemName = delItem.getItemName();

                        int delId = delItem.getInvId();

                        if (delId == entryId) {
                            found = true;
                            boolean confirm = console.yesCheck("Are you sure you want to remove " + delItemName + "? [yes/no]", "Enter [yes/no] to proceed.");

                            if (confirm == true) {

                                invDao.delete(delItem);
                                String upperName = delItemName.toUpperCase();
                                System.out.println("'" + upperName + "' DELETED");
                                confirm = console.yesCheck("Delete another item? [yes/no]", "Enter [yes/no] to proceed.");
                                removeAgain = confirm == true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("\nItem not found!\n");
                    }
                    if (removeAgain == true) {
                        boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        removeAgain = confirm == true;
                    }
                } catch (Exception ex) {
                    System.out.println("That is an invalid ID entry.");
                }

            }
        }
    }

    public void updatePrice() {

        boolean updateAgain = true;
        boolean found;

        showInventory(0, true);

        while (updateAgain == true) {
            found = false;
            String itemId = console.checkEmptyString("\nEnter the ID of your item to update its price. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                updateAgain = false;
            } else {
                try {
                    int entryId = Integer.parseInt(itemId);

                    List<Inventory> items = invDao.decode();

                    for (Inventory foundItem : items) {

                        String fItemName = foundItem.getItemName();
                        int fId = foundItem.getInvId();
                        int savedStock = foundItem.getStock();

                        if (fId == entryId) {

                            found = true;
                            boolean confirm = console.yesCheck("Change price for " + fItemName + "? [yes/no]", "Enter [yes/no] to proceed.");

                            if (confirm == true) {

                                String itemPrice = console.checkEmptyString("Enter the new price for your item.", "You cannot leave this blank!");
                                float newItemPrice = Float.parseFloat(itemPrice);

                                foundItem.setCost(newItemPrice);
                                foundItem.setItemName(fItemName);
                                foundItem.setInvId(fId);
                                foundItem.setStock(savedStock);
                                invDao.update(foundItem);

                                String upperName = fItemName.toUpperCase();
                                System.out.println("'" + upperName + "' PRICE CHANGED TO $" + df.format(newItemPrice));
                                confirm = console.yesCheck("Update another price? [yes/no]", "Enter [yes/no] to proceed.");
                                updateAgain = confirm == true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("\nItem not found!\n");
                    }
                    if (updateAgain == true) {
                        boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        updateAgain = confirm == true;
                    }
                } catch (Exception ex) {
                    System.out.println("That is an invalid ID entry.");
                }

            }
        }
    }

    public void updateStock() {

        boolean updateAgain = true;
        boolean found;
        showInventory(0, true);

        while (updateAgain == true) {
            found = false;
            String itemId = console.checkEmptyString("\nEnter the ID of your item to update its stock. (Enter 0 to cancel)", "You cannot leave this blank!");
            if (itemId.equals("0")) {
                updateAgain = false;
            } else {
                try {
                    int entryId = Integer.parseInt(itemId);

                    List<Inventory> items = invDao.decode();

                    for (Inventory foundItem : items) {

                        String fItemName = foundItem.getItemName();
                        int fId = foundItem.getInvId();
                        float fPrice = foundItem.getCost();

                        if (fId == entryId) {
                            found = true;
                            boolean confirm = console.yesCheck("Change stock for " + fItemName + "? [yes/no]", "Enter [yes/no] to proceed.");

                            if (confirm == true) {

                                String itemStock = console.checkEmptyString("Enter the new stock for your item.", "You cannot leave this blank!");
                                int newItemStock = Integer.parseInt(itemStock);

                                foundItem.setCost(fPrice);
                                foundItem.setStock(newItemStock);
                                foundItem.setInvId(fId);
                                foundItem.setItemName(fItemName);
                                invDao.update(foundItem);

                                String upperName = fItemName.toUpperCase();
                                System.out.println("'" + upperName + "' STOCK CHANGED TO x " + newItemStock);
                                confirm = console.yesCheck("Update another item's stock? [yes/no]", "Enter [yes/no] to proceed.");
                                updateAgain = confirm == true;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("\nItem not found!\n");
                    }
                    if (updateAgain == true) {
                        boolean confirm = console.yesCheck("Search again? [yes/no]\n>", "Enter [yes/no] to proceed.\n>");
                        updateAgain = confirm == true;
                    }
                } catch (Exception ex) {
                    System.out.println("That is an invalid ID entry.");
                }

            }
        }
    }

    public void showInventory(float funds, boolean ad) {

        System.out.println("+============================================+");
        System.out.println("  ID # | PRICE | NAME               | STOCK ");
        System.out.println("----------------------------------------------");
        List<Inventory> itemsOnFile = invDao.decode();

        getItemsList(itemsOnFile);
        if (!ad) {
            System.out.println("----------------------------------------------");
            System.out.println("                        CURRENT FUNDS: $" + df.format(funds));
            System.out.println("----------------------------------------------");
            System.out.println("  TO OPERATE:");
            System.out.println("         Enter ID to purchase Item");
            System.out.println("         Enter '-' to return funds");
            System.out.println("         Enter 'q' to quit");
        }
        System.out.println("+============================================+");

    }

    public void transRecord() {

        List<Change> transactions = changeDao.decode();

        System.out.println("+===========================================================+");
        System.out.println("  ID # | PRICE | NAME               | STOCK | PAID | GIVEN  |");
        System.out.println("-------------------------------------------------------------");
        getTransList(transactions);
        System.out.println("+===========================================================+");

    }

    public void getTransList(List<Change> trans) {

        for (Change transOnFile : trans) {

            int savedId = transOnFile.getInv().getInvId();
            String savedName = transOnFile.getInv().getItemName();
            float savedPrice = transOnFile.getInv().getCost();
            int savedStock = transOnFile.getInv().getStock();
            float savedIn = transOnFile.getEnteredAmount();
            float savedOut = transOnFile.getChangeAmount();

            showTransDisplay(savedId, savedPrice, savedName, savedStock, savedIn, savedOut);
        }

    }

    public void showTransDisplay(int itemId, float itemPrice, String itemName, int itemStock, float in, float out) {

        System.out.println("  " + itemId + " | $" + df.format(itemPrice) + " | " + itemName + "            | " + itemStock + " | $" + in + " | $" + out);

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

    public void showItemsInList(int itemId, float itemPrice, String itemName, int itemStock) {

        System.out.println("  " + itemId + " | $" + df.format(itemPrice) + " | " + itemName + "            | " + itemStock);

    }

}
