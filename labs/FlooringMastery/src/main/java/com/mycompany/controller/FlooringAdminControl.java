/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.ProductDao;
import com.mycompany.dao.TaxesDao;
import com.mycompany.data.FlooringData;
import com.mycompany.dto.Product;
import com.mycompany.dto.Taxes;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringAdminControl {

    ConsoleIO console = new ConsoleIO();
    ProductDao productDao = new ProductDao();
    TaxesDao taxDao = new TaxesDao();
    FlooringData fd = new FlooringData();
    DecimalFormat df = new DecimalFormat("0.00");

    public void displayAdminControls() {

        boolean run = true;
        while (run == true) {

            displayAdminMenu();

            String selection = console.getString(">");

            switch (selection) {

                case "1":
                    //create product
                    addProduct();
                    break;
                case "2":
                    //create tax rate
                    addTax();
                    break;
                case "3":
                    //view product list
                    viewProductList();
                    break;
                case "4":
                    //view tax rates
                    viewTaxList();
                    break;
                case "5":
                    //update product
                    updateProduct();
                    break;
                case "6":
                    //update tax rate
                    updateTax();
                    break;
                case "7":
                    //remove product
                    removeProduct();
                    break;
                case "8":
                    //remove tax
                    removeTax();
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

    public void addProduct() {

        boolean valid = false;
        String productType = "";
        Product newProduct = new Product();
        while (!valid) {
            productType = console.getString("Enter the name for your product.\n>");
            valid = checkProductExist(productType);
        }
        String lower = productType.toLowerCase();
        String capProductType = lower.substring(0, 1).toUpperCase() + lower.substring(1);
        newProduct.setProductType(capProductType);
        newProduct.setCostPerSqFt(console.getDouble("Enter the Cost/SqFt. for this product.\n>$", "That is not a valid price!"));
        newProduct.setLaborCostPerSqFt(console.getDouble("Enter the Labor Cost/SqFt. for this product.\n>$", "That is not a valid price!"));
        productDao.create(newProduct);

        console.readString("\n" + newProduct.getProductType() + " added to your product list!\n");

    }

    public void addTax() {

        Taxes newTax = new Taxes();
        boolean valid = false;
        boolean valid2 = false;
        String stateAbrv = "";
        while (!valid && !valid2) {
            stateAbrv = console.getString("\nEnter a two character state abbreviation to add to list (Ex. NY).\n>");
            valid = checkStateAbrv(stateAbrv);
            if (valid == true) {
                valid2 = checkTaxExist(stateAbrv);
            }
        }
        String stateCap = stateAbrv.toUpperCase();
        newTax.setState(stateCap);
        newTax.setTaxRate(console.getDouble("Enter the tax rate for " + newTax.getState() + "\n>", "That is an invalid entry!"));
        taxDao.create(newTax);

        console.readString("\n" + newTax.getState() + " added to your state tax rate list!\n");

    }

    public void removeProduct() {

        boolean confirm;
        String choice;
        Product delProduct = new Product();
        List<Product> productList = fd.productDecode();

        choice = console.getString("Enter the name of the product you wish to remove.\n>");

        for (Product productOnList : productList) {

            if (choice.equals(productOnList.getProductType())) {

                delProduct = productOnList;

            }

        }

        confirm = console.yesCheck("Are you sure you want to remove " + delProduct.getProductType() + "? [yes/no]\n>", "Enter [yes] to remove " + delProduct.getProductType() + ", enter [no] to keep.");
        if (confirm == true) {
            productDao.delete(delProduct);
            console.readString("\n" + delProduct.getProductType() + " removed!\n");
        } else {
            console.readString("\nRemove product cancelled!\n");
        }

    }

    public void removeTax() {

        boolean confirm;
        String choice;
        Taxes delTax = new Taxes();
        List<Taxes> taxesList = fd.taxDecode();

        choice = console.getString("Enter the state abbreviation of the tax rate you wish to remove.\n>");

        for (Taxes taxOnList : taxesList) {

            if (choice.equals(taxOnList.getState())) {

                delTax = taxOnList;

            }

        }

        confirm = console.yesCheck("Are you sure you want to remove " + delTax.getState() + "'s tax rate? [yes/no]\n>", "Enter [yes] to remove " + delTax.getState() + ", enter [no] to keep.");
        if (confirm == true) {
            taxDao.delete(delTax);
            console.readString("\n" + delTax.getState() + " removed!\n");
        } else {
            console.readString("\nRemove tax rate cancelled!\n");
        }

    }

    public void updateProduct() {
        boolean valid = false;
        String choice;
        String productName;
        Product editProduct = new Product();
        List<Product> productList = fd.productDecode();

        productName = console.getString("Enter the name of the product you wish to edit.\n>");

        for (Product productOnList : productList) {

            if (productName.equalsIgnoreCase(productOnList.getProductType())) {

                editProduct = productOnList;

            }

        }

        while (!valid) {
            choice = console.getString(">");
            updateProductSwitch(choice, editProduct);
        }

    }

    public void showUpdateProductMenu() {

        console.readString("===============================");
        console.readString(" What would you like to update?");
        console.readString("-------------------------------");
        console.readString(" 1) Update Product Name");
        console.readString(" 2) Update Cost/SqFt.");
        console.readString(" 3) Update Labor Cost/SqFt.");
        console.readString(" 4) Update All");
        console.readString(" 5) Go Back");
        console.readString("===============================");

    }

    public void updateProductEntries(String choice, Product product) {

        boolean valid = false;
        String productType = "";
        if (choice.equals("1") || choice.equals("4")) {
            while (!valid) {
                productType = console.getString("Enter the name for your product.\n>");
                valid = checkProductExist(productType);
            }
            String lower = productType.toLowerCase();
            String capProductType = lower.substring(0, 1).toUpperCase() + lower.substring(1);
            product.setProductType(capProductType);
        }
        if (choice.equals("2") || choice.equals("4")) {
            product.setCostPerSqFt(console.getDouble("Enter the new Cost/SqFt. for this product.\n>$", "That is not a valid price!"));
        }
        if (choice.equals("3") || choice.equals("4")) {
            product.setLaborCostPerSqFt(console.getDouble("Enter the new Labor Cost/SqFt. for this product!\n>$", "That is not a valid price!"));
        }
        productDao.update(product);
    }

    public boolean updateProductSwitch(String choice, Product product) {
        boolean valid = false;

        switch (choice) {

            case "1":
            case "2":
            case "3":
            case "4":
                updateProductEntries(choice, product);
                valid = true;
                break;
            case "5":
                //quit
                valid = true;
                break;
            default:
                //invalid
                console.readString("That is an invalid option!");
                break;

        }
        return valid;
    }

    public void updateTax() {
        boolean valid = false;
        String choice;
        String stateAbrv;
        Taxes editTax = new Taxes();
        List<Taxes> taxList = fd.taxDecode();

        stateAbrv = console.getString("Enter the state abbreviation of the tax rate you wish to edit.\n>");

        for (Taxes taxOnList : taxList) {

            if (stateAbrv.equalsIgnoreCase(taxOnList.getState())) {

                editTax = taxOnList;

            }

        }

        while (!valid) {
            choice = console.getString(">");
            updateTaxSwitch(choice, editTax);
        }

    }

    public void showUpdateTaxMenu() {

        console.readString("===============================");
        console.readString(" What would you like to update?");
        console.readString("-------------------------------");
        console.readString(" 1) Update State Abbreviation");
        console.readString(" 2) Update Tax Rate");
        console.readString(" 3) Update All");
        console.readString(" 4) Go Back");
        console.readString("===============================");

    }

    public void updateTaxEntries(String choice, Taxes tax) {
        boolean valid = false;
        boolean valid2 = false;
        String stateAbrv = "";
        if (choice.equals("1") || choice.equals("3")) {
            while (!valid && !valid2) {
                valid = checkStateAbrv(stateAbrv);
                if (valid == true) {
                    valid2 = checkTaxExist(stateAbrv);
                }
            }
            String stateCap = stateAbrv.toUpperCase();
            tax.setState(stateCap);
        }
        if (choice.equals("2") || choice.equals("3")) {
            tax.setTaxRate(console.getDouble("Enter the new tax rate for " + tax.getState() + ".\n>$", "That is not a valid tax rate!"));
        }
        taxDao.update(tax);
    }

    public boolean updateTaxSwitch(String choice, Taxes tax) {
        boolean valid = false;

        switch (choice) {

            case "1":
            case "2":
            case "3":
                updateTaxEntries(choice, tax);
                valid = true;
                break;
            case "4":
                //quit
                valid = true;
                break;
            default:
                //invalid
                console.readString("That is an invalid option!");
                break;

        }
        return valid;
    }

    public void viewProductList() {

        List<Product> productList = fd.productDecode();
        console.readString("-------------------------------------------------");
        console.readString(" Product       | Cost/SqFt.    | Labor Cost/SqFt.");
        console.readString("-------------------------------------------------");
        for (Product product : productList) {
            console.padRight(" " + product.getProductType(), 15);
            console.padRight("| $" + df.format(product.getCostPerSqFt()), 16);
            console.readStringSameLine("| $" + df.format(product.getLaborCostPerSqFt()) + "\n");
        }
        console.readString("-------------------------------------------------");
    }

    public void viewTaxList() {
        List<Taxes> taxList = fd.taxDecode();
        console.readString("-----------------");
        console.readString(" State | Tax Rate");
        console.readString("-----------------");
        for (Taxes tax : taxList) {
            console.padRight("  " + tax.getState(), 7);
            console.readStringSameLine("| " + df.format(tax.getTaxRate()) + "%\n");
        }
        console.readString("-----------------");
    }

    public void displayAdminMenu() {

        console.readString("===============================");
        console.readString(" Welcome to admin mode!");
        console.readString("-------------------------------");
        console.readString(" 1) Add Product");
        console.readString(" 2) Add Tax Rate");
        console.readString(" 3) View Product List");
        console.readString(" 4) View Tax List");
        console.readString(" 5) Update Product");
        console.readString(" 6) Update Tax Rate");
        console.readString(" 7) Remove Product");
        console.readString(" 8) Remove Tax Rate");
        console.readString("-------------------------------");
        console.readString(" 0) Go Back");
        console.readString("===============================");
    }

    public boolean checkStateAbrv(String state) {

        boolean valid = false;
        if (state.length() > 3 || state.length() < 2) {

            console.readString("Your state must two characters!");

        } else {
            valid = true;
        }
        return valid;
    }

    public boolean checkProductExist(String productType) {

        boolean valid = false;
        List<Product> productList = fd.productDecode();
        for (Product productOnList : productList) {
            if (productType.equalsIgnoreCase(productOnList.getProductType())) {
                console.readString("That product already exists!");
                valid = false;
                break;
            } else {
                valid = true;
            }
        }
        return valid;
    }

    public boolean checkTaxExist(String state) {

        boolean valid = false;
        List<Taxes> taxList = fd.taxDecode();
        for (Taxes taxOnList : taxList) {
            if (state.equalsIgnoreCase(taxOnList.getState())) {
                console.readString("A tax rate for that state already exists!");
                valid = false;
                break;
            } else {
                valid = true;
            }
        }
        return valid;
    }
}
