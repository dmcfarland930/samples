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

    List <Change> transactions = new ArrayList();
    Inventory item = new Inventory();
    Change change = new Change();
    int nextId = 1;

    public void Change() {

        transactions = decode();
        for (Change myTran : transactions){
            if(myTran.getTransId() == nextId){
                nextId++;
            }
            
        }
    }

    
    public Change create(Change change, Inventory item){
        
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
            out = new PrintWriter(new FileWriter("inventoryList.txt"));

            for (Change myChange : transactions) {

                out.print(myChange.getTransId());
                out.print(TOKEN);
                out.print(myChange.getInv().getInvId());
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

        List <Change> transList = new ArrayList();
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("dvdList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Change myChange = new Change();
                Inventory myItem = new Inventory();

                int id = Integer.parseInt(stringParts[0]);
                myChange.setTransId(id);
                myItem.setItemName(stringParts[1]);
                float cost = Float.parseFloat(stringParts[2]);
                myItem.setCost(cost);
                int stock = Integer.parseInt(stringParts[3]);
                myItem.setStock(stock);
                float inChange = Float.parseFloat(stringParts[4]);
                myChange.setEnteredAmount(inChange);
                float outChange = Float.parseFloat(stringParts[5]);
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
}
