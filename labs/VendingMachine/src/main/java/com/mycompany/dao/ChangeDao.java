/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Change;
import com.mycompany.dto.Inventory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class ChangeDao {

    List<Change> transactions = new ArrayList();
    Inventory item = new Inventory();
    Change change = new Change();
    int nextId = 1;

    public void Change() {

        transactions = decode();
        for (Change myTran : transactions) {
            if (myTran.getTransId() == nextId) {
                nextId++;
            }

        }
    }

    public Change create(Change change, Inventory item) {

        change.setTransId(nextId);
        change.setInv(item);
        nextId++;
        transactions.add(change);

        encode();

        return change;

    }

    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("transactionrecord.txt"));

            for (Change myChange : transactions) {

                out.print(myChange.getTransId());
                out.print(TOKEN);
                out.print(myChange.getInv().getInvId());
                out.print(TOKEN);
                out.print(myChange.getInv().getItemName());
                out.print(TOKEN);
                out.print(myChange.getInv().getCost());
                out.print(TOKEN);
                out.print(myChange.getInv().getStock());
                out.print(TOKEN);
                out.print(myChange.getEnteredAmount());
                out.print(TOKEN);
                out.print(myChange.getChangeAmount());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(ChangeDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        List<Change> transList = new ArrayList();
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("transactionrecord.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Change myChange = new Change();
                Inventory myItem = new Inventory();

                int id = Integer.parseInt(stringParts[0]);
                myChange.setTransId(id);
                int idInv = Integer.parseInt(stringParts[1]);
                myItem.setInvId(idInv);
                myItem.setItemName(stringParts[2]);
                float cost = Float.parseFloat(stringParts[3]);
                myItem.setCost(cost);
                int stock = Integer.parseInt(stringParts[4]);
                myItem.setStock(stock);
                float inChange = Float.parseFloat(stringParts[5]);
                myChange.setEnteredAmount(inChange);
                float outChange = Float.parseFloat(stringParts[6]);
                myChange.setEnteredAmount(outChange);

                myChange.setInv(myItem);
                transList.add(myChange);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChangeDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return transList;

    }

    public float addFunds(float funds) {

        float userFunds = change.getEnteredAmount();

        userFunds = userFunds + funds;

        return userFunds;
    }

    public boolean validateEnoughFunds(float funds, float itemPrice, Inventory item) {

        boolean sufficient = false;

        if (funds > itemPrice || funds == itemPrice) {
            calculateChange(funds, itemPrice, item);
            sufficient = true;
        }

        return sufficient;
    }

    public void calculateChange(float funds, float itemPrice, Inventory item) {

        Change newTx = new Change();
        float fundPennies = funds * 100;
        float pricePennyConvert = itemPrice;
        float modulus;
        float outChange;
        float quarters;
        float dimes;
        float nickels;
        float pennies;
        
        if (itemPrice < 1f) {
            pricePennyConvert = itemPrice * 100;
        }

        pennies = fundPennies - pricePennyConvert;
        newTx.setQuarterCount(pennies / 25);
        modulus = pennies % 25;
        if (!(modulus == 0)) {
            newTx.setDimeCount(modulus / 10);
            modulus = modulus % 10;
            if (!(modulus == 0)) {
                newTx.setNickelCount(modulus / 5);
                modulus = modulus % 5;
                if (!(modulus == 0)) {
                    newTx.setPennyCount(modulus);
                }
            }
        }
        
        quarters = (newTx.getQuarterCount() * 25);
        dimes = (newTx.getDimeCount() * 10);
        nickels = (newTx.getNickelCount() * 5);
        pennies = (newTx.getPennyCount());

        outChange = ((quarters + dimes + nickels + pennies)/100);

        newTx.setEnteredAmount(funds);
        newTx.setChangeAmount(outChange);
        newTx.setSpentAmount(funds - outChange);

        create(newTx, item);

    }

}
