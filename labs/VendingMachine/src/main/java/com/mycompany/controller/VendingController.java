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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class VendingController {

    ConsoleIO console = new ConsoleIO();
    InventoryDao invDao = new InventoryDao();
    ChangeDao changeDao = new ChangeDao();
    AdminController ac = new AdminController();
    DecimalFormat df = new DecimalFormat("0.00");

    int idEntry;
    float funds = 0;
    List<Inventory> purchasedGoods = new ArrayList();

    public void runApp() {

        menuDisplay();

        System.out.println("Good bye!");
        invDao.encode();
        changeDao.encode();

    }

    public void menuDisplay() {

        String selection;
        boolean valid;
        boolean run = true;

        while (run == true) {

            System.out.println("\nHello, hungry traveler.");
            System.out.println("Enter your money for sustanence.");

            ac.showInventory(funds, false);

            selection = console.checkEmptyString("", "You have to put something in!");

            switch (selection) {

                case "-":
                    //return funds
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
            
            if (foundId == itemIdEntry && foundStock > 1 ) {
                float foundPrice = foundItem.getCost();
                ac.showInventory(cash, false);
                {
                    while (!sufficientFunds) {
                        cash = funds + console.checkMinMaxFloat("How much would you like to add to your funds?: $", 0, Float.MAX_VALUE, "You can't add less than zero!", "That's not a valid entry!");
                        funds += cash;
                        sufficientFunds = changeDao.validateEnoughFunds(cash, foundPrice, foundItem);
                        if (!sufficientFunds) {
                            System.out.println("You need to add more money to buy this item!");

                        }
                    }

                    List<Change> change = changeDao.decode();
                    for (Change tX : change) {

                        int invIdTx = tX.getInv().getInvId();

                        if (foundId == invIdTx) {

                            cash = cash - tX.getInv().getCost();
                            int stock = tX.getInv().getStock() - 1;
                            tX.getInv().setStock(stock);
                            String itemName = tX.getInv().getItemName();
                            foundItem = tX.getInv();
                            invDao.update(foundItem);
                            System.out.println(itemName + " received!");
                            found = true;
                            break;
                        }
                    }

                }

            }else if (found && foundStock < 1){
                System.out.println("That item is out of stock! Sorry!");
                found = true; //item id is valid but out of stock.
            }

        }
        if (!found) {
            System.out.println("\nThat item does not exist!\n");
        }

        return cash;
    }

    public boolean convertUserEntryToId(String userInput) {

        int conversion = 0;
        boolean valid = false;

        try {
            conversion = Integer.parseInt(userInput);
            this.idEntry = conversion;
            valid = true;
        } catch (Exception ex) {
            System.out.println("That is an invalid entry!");

        }

        return valid;
    }

//            
//            if(!inPrice.equals("q")){
//                inPriceF = changeDao.addFunds(inPriceF);
//                change.setEnteredAmount(inPriceF);
//                System.out.println("Your funds: $" + df.format(inPriceF));
//                //select item method
//            }
}
