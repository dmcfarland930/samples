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
public class VendingController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    Change changeObject = new Change();
    ChangeDao changeDao = new ChangeDao();
    AdminController ac = new AdminController();
    DecimalFormat df = new DecimalFormat("0.00");

    int idEntry;
    float funds = 0;

    public void runApp() {

        console.readString("\nHello, hungry traveler.");
        console.readString("Enter your money for sustanence.\n");

        menuDisplay();

        console.readString("Good bye!");

    }

    public void menuDisplay() {

        String selection;
        boolean valid;
        boolean run = true;

        while (run == true) {

            ac.showInventory(funds, false);

            selection = console.getString(">>");

            switch (selection) {

                case "+":
                    //add funds
                    funds += fundsInput();
                    break;
                case "-":
                    //return funds
                    changeDao.calculateChange(funds, 0, null, changeObject);
                    viewChange();
                    funds = 0;
                    break;
                case "q":
                    //quit app
                    run = false;
                    break;
                case "DOGMEAT":
                    ac.adminMenuDisplay();
                    break;
                default:
                    //purchase item
                    valid = convertUserEntryToId(selection);
                    if (valid == true) {
                        funds = purchaseItem(idEntry, funds);
                    }
                    break;

            }

        }

    }

    public float purchaseItem(int itemIdEntry, float cash) {

        List<Inventory> itemsOnFile = invDao.decode();
        boolean sufficientFunds = false;
        boolean found = false;

        for (Inventory foundItem : itemsOnFile) {

            int foundId = foundItem.getInvId();
            int foundStock = foundItem.getStock();

            if (foundId == itemIdEntry && foundStock >= 1) {
                float foundPrice = foundItem.getCost();
                ac.showInventory(cash, false);
                {
                    while (!sufficientFunds) {
                        sufficientFunds = changeDao.validateEnoughFunds(cash, foundPrice, foundItem);
                        found = true;
                        if (!sufficientFunds) {
                            console.readString("\nYou need to add more money to buy this item!\n");
                            found = true;
                            break;

                        }
                    }

                    changeDao.calculateChange(cash, foundPrice, foundItem, changeObject);
                    List<Change> change = changeDao.decode();

                    for (Change tX : change) {

                        int invIdTx = tX.getInv().getInvId();

                        if (foundId == invIdTx && sufficientFunds == true) {

                            cash = cash - tX.getInv().getCost();
                            int stock = tX.getInv().getStock() - 1;
                            tX.getInv().setStock(stock);
                            String itemName = tX.getInv().getItemName();
                            foundItem = tX.getInv();
                            invDao.update(foundItem);
                            viewChange();
                            console.readString(itemName + " GET!!\n");
                            funds = 0;
                            break;
                        }
                    }

                }

            } else if (found && foundStock < 1) {
                console.readString("That item is no longer in stock!");
                found = true; //item id is valid but out of stock.
            }

        }
        if (!found) {
            console.readString("\nThat item does not exist!\n");
        }

        return cash;
    }

    public boolean convertUserEntryToId(String userInput) {

        int conversion;
        boolean valid = false;

        try {
            conversion = Integer.parseInt(userInput);
            this.idEntry = conversion;
            valid = true;
        } catch (Exception ex) {
            console.readString("\nThat is an invalid entry!");

        }

        return valid;
    }

    public void viewChange() {

        int quarter = changeObject.getQuarterCount();
        int dime = changeObject.getDimeCount();
        int nickel = changeObject.getNickelCount();
        int penny = changeObject.getPennyCount();
        float changeOut = changeObject.getChangeAmount();

        if (quarter > 0) {

            console.readString("\nYou received " + quarter + " quarter(s)!");

        }

        if (dime > 0) {

            console.readString("You received " + dime + " dime(s)!");

        }

        if (nickel > 0) {

            console.readString("You received " + nickel + " nickel(s)!");

        }

        if (penny > 0) {

            if (penny == 1) {
                console.readString("You received " + penny + "penny!");
            } else if (penny > 1) {
                console.readString("You received " + penny + "pennies!");
            }
        }

        if (changeOut > 0) {
            console.readString("You received $" + df.format(changeOut) + " in change!\n");
        }

    }

    public float fundsInput() {

        float cash;
        cash = console.checkMinMaxFloat("Enter money into to machine: $", 0, Float.MAX_VALUE, "You can't add less than zero!", "That's not a valid entry!");

        console.readString("You added $" + df.format(cash));
        return cash;
    }
}
