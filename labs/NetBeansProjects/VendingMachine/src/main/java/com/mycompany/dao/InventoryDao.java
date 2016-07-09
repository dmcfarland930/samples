/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

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
public class InventoryDao {

    List<Inventory> inventoryList = new ArrayList();
    private int nextId = 1001;

    public InventoryDao() {

        inventoryList = decode();
        for (Inventory item : inventoryList) {

            if (item.getInvId() >= nextId) {
                nextId = item.getInvId() +1;
            }

        }

    }

    public Inventory create(Inventory item) {

        item.setInvId(nextId);
        nextId++;
        inventoryList.add(item);

        encode();

        return item;

    }

    public Inventory get(int id) {

        for (Inventory myItem : inventoryList) {

            int getId = myItem.getInvId();
            if (getId == id) {

                return myItem;
            }
        }
        return null;
    }

    public void update(Inventory item) {

        inventoryList = decode();
        for (Inventory myItem : inventoryList) {

            if (myItem.getInvId() == item.getInvId()) {
                inventoryList.remove(myItem);
                inventoryList.add(item);
                break;
            }
        }
        encode();

    }

    public void delete(Inventory item) {

        Inventory found = null;

        for (Inventory myItem : inventoryList) {

            if (myItem.getInvId() == item.getInvId()) {

                found = myItem;
                break;
            }

        }
        inventoryList.remove(found);
        encode();

    }

    public void encode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("inventoryList.txt"));

            for (Inventory myItem : inventoryList) {

                out.print(myItem.getInvId());
                out.print(TOKEN);
                out.print(myItem.getItemName());
                out.print(TOKEN);
                out.print(myItem.getCost());
                out.print(TOKEN);
                out.print(myItem.getStock());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        Scanner sc = null;
        List<Inventory> listOfItems = new ArrayList();

        try {
            sc = new Scanner(new BufferedReader(new FileReader("inventoryList.txt")));

            while (sc.hasNext()) {

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split("::");

                Inventory myItem = new Inventory();

                int id = Integer.parseInt(stringParts[0]);
                myItem.setInvId(id);
                myItem.setItemName(stringParts[1]);
                float cost = Float.parseFloat(stringParts[2]);
                myItem.setCost(cost);
                int stock = Integer.parseInt(stringParts[3]);
                myItem.setStock(stock);

                listOfItems.add(myItem);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(InventoryDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return listOfItems;

    }

}
