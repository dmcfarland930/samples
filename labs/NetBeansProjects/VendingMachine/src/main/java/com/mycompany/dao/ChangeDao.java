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
public final class ChangeDao {

    List<Change> transactions = new ArrayList();
    Inventory item = new Inventory();
    Change change = new Change();
    int nextId = 1;

    public ChangeDao() {

        transactions = decode();
        for (Change myTran : transactions) {
            if (myTran.getTransId() >= nextId) {
                nextId = myTran.getTransId() + 1;
            }

        }
    }

    public Change create(Change change, Inventory item) {

        change.setTransId(nextId);
        change.setInv(item);
        nextId += 1;
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
                out.print(myChange.getSpentAmount());
                out.print(TOKEN);
                out.print(myChange.getChangeAmount());
                out.print(TOKEN);
                out.print(myChange.getQuarterCount());
                out.print(TOKEN);
                out.print(myChange.getDimeCount());
                out.print(TOKEN);
                out.print(myChange.getNickelCount());
                out.print(TOKEN);
                out.print(myChange.getPennyCount());
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
                float spentChange = Float.parseFloat(stringParts[6]);
                myChange.setSpentAmount(spentChange);
                float outChange = Float.parseFloat(stringParts[7]);
                myChange.setChangeAmount(outChange);
                
                int quarterC = Integer.parseInt(stringParts[8]);
                myChange.setQuarterCount(quarterC);
                int dimeC = Integer.parseInt(stringParts[9]);
                myChange.setDimeCount(dimeC);
                int nickelC = Integer.parseInt(stringParts[10]);
                myChange.setNickelCount(nickelC);
                int pennyC = Integer.parseInt(stringParts[11]);
                myChange.setPennyCount(pennyC);
                
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



    public boolean validateEnoughFunds(float funds, float itemPrice, Inventory item) {

        boolean sufficient = false;

        if (funds > itemPrice || funds == itemPrice) {
            sufficient = true;
        }

        return sufficient;
    }

    public void calculateChange(float funds, float itemPrice, Inventory item, Change object) {

        if (item == null){
            Inventory changeReturn = new Inventory();
            changeReturn.setItemName("Change return");
            item = changeReturn;
        }
        
        int modulus;
        float spent;
        int pennies;

        float pricePennyConvert = itemPrice * 100f;

        float fundPennies = funds * 100f;

        float fltPennies = fundPennies - pricePennyConvert;

        pennies = (int) fltPennies;
        
        

        object.setQuarterCount(pennies / 25);
        modulus = pennies % 25;
        if (!(modulus == 0)) {
            object.setDimeCount(modulus / 10);
            modulus = modulus % 10;
            if (!(modulus == 0)) {
                object.setNickelCount(modulus / 5);
                modulus = modulus % 5;
                if (!(modulus == 0)) {
                    object.setPennyCount(modulus);
                }
            }
        }

        spent = (pricePennyConvert / 100);

        object.setEnteredAmount(funds);
        object.setChangeAmount(funds - (spent));
        object.setSpentAmount(spent);


        create(object, item);

    }

}
