/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.data;

import com.mycompany.controller.FlooringController;
import com.mycompany.dao.OrderDao;
import com.mycompany.dao.TaxesDao;
import com.mycompany.dto.Order;
import com.mycompany.dto.Taxes;
import com.mycompany.dto.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class FlooringData {

    List<Taxes> taxesList = new ArrayList();
    List<Product> productList = new ArrayList();
    boolean fileDir = new File("File" + File.separator + "Orders" + File.separator).mkdirs();
    File newFile;

    public FlooringData() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");

        try {

            newFile = new File("File" + File.separator + "Orders" + File.separator + "Orders_" + sdf.format(date) + ".txt");

            newFile.createNewFile();

            try {
                if (!newFile.exists()) {
                    PrintWriter out = new PrintWriter(new FileWriter("File" + File.separator + "Orders" + File.separator + "Orders_" + sdf.format(date) + ".txt"));
                    out.println("OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total");
                    out.flush();
                    out.close();
                }
            } catch (IOException ex) {

                Logger.getLogger(FlooringData.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {

            Logger.getLogger(FlooringData.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void orderEncode(String dateEntry, List<Order> orderList) {

        DecimalFormat df = new DecimalFormat("0.00");
        final String TOKEN = ",";
        String customerName;
        PrintWriter out = null;

        try {

            out = new PrintWriter(new FileWriter("File" + File.separator + "Orders" + File.separator + "Orders_" + dateEntry + ".txt"));
            out.print("OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total");
            out.println();

            for (Order myOrder : orderList) {
                out.print(myOrder.getOrderNumber());
                out.print(TOKEN);
                customerName = escapeComma(myOrder);
                out.print(customerName);
                out.print(TOKEN);
                out.print(myOrder.getState());
                out.print(TOKEN);
                out.print(df.format(myOrder.getTaxRate()));
                out.print(TOKEN);
                out.print(myOrder.getProductType());
                out.print(TOKEN);
                out.print(df.format(myOrder.getArea()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getCostPerSqFt()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getLaborCostPerSqFt()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getMaterialCost()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getTotalLaborCost()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getTax()));
                out.print(TOKEN);
                out.print(df.format(myOrder.getOrderTotal()));
                out.println();

            }
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List orderDecode(String dateEntry) {

        Scanner sc = null;
        String customerName;
        int i;
        List<Order> orders = new ArrayList();
        try {
            sc = new Scanner(new BufferedReader(new FileReader("File" + File.separator + "Orders" + File.separator + "Orders_" + dateEntry + ".txt")));

            int lineNumber = 0;

            while (sc.hasNext()) {

                lineNumber++;

                if (lineNumber == 1) {
                    sc.nextLine();
                    continue;
                }

                Order myOrder = new Order();
                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(",");
                int id = Integer.parseInt(stringParts[0]);
                myOrder.setOrderNumber(id);
                if (stringParts.length == 13) {
                    customerName = stringParts[1] + stringParts[2];
                    customerName = customerName.replace("\\", ",");
                    myOrder.setCustomerName(customerName);
                    i = 3;
                } else {
                    myOrder.setCustomerName(stringParts[1]);
                    myOrder.setCustomerName(removeQuotes(myOrder));
                    i = 2;
                }
                myOrder.setState(stringParts[i]);
                i++;
                double taxRate = Double.parseDouble(stringParts[i]);
                myOrder.setTaxRate(taxRate);
                i++;
                myOrder.setProductType(stringParts[i]);
                i++;
                double area = Double.parseDouble(stringParts[i]);
                myOrder.setArea(area);
                i++;
                double costPerSqFt = Double.parseDouble(stringParts[i]);
                myOrder.setCostPerSqFt(costPerSqFt);
                i++;
                double laborCostPerSqFt = Double.parseDouble(stringParts[i]);
                myOrder.setLaborCostPerSqFt(laborCostPerSqFt);
                i++;
                double materialCost = Double.parseDouble(stringParts[i]);
                myOrder.setMaterialCost(materialCost);
                i++;
                double totalLaborCost = Double.parseDouble(stringParts[i]);
                myOrder.setTotalLaborCost(totalLaborCost);
                i++;
                double tax = Double.parseDouble(stringParts[i]);
                myOrder.setTax(tax);
                i++;
                double orderTotal = Double.parseDouble(stringParts[i]);
                myOrder.setOrderTotal(orderTotal);

                orders.add(myOrder);
            }

        } catch (FileNotFoundException | NumberFormatException ex) {

        } finally {
            sc.close();
        }

        return orders;
    }

    public void taxEncode() {

        final String TOKEN = "::";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("File" + File.separator + "taxesList.txt"));

            for (Taxes myTaxes : taxesList) {

                out.print(myTaxes.getState());
                out.print(TOKEN);
                out.print(myTaxes.getTaxRate());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List taxDecode() {

        Scanner sc = null;
        List<Taxes> taxes = new ArrayList();

        int lineNumber = 0;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("File" + File.separator + "taxesList.txt")));

            while (sc.hasNext()) {

                lineNumber++;

                if (lineNumber == 1) {
                    sc.nextLine();
                    continue;
                }

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split(",");

                Taxes myTaxes = new Taxes();

                myTaxes.setState(stringParts[0]);
                double taxRate = Double.parseDouble(stringParts[1]);
                myTaxes.setTaxRate(taxRate);

                taxes.add(myTaxes);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return taxes;
    }

    public void productEncode() {

        final String TOKEN = ",";
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter("File" + File.separator + "productList.txt"));

            for (Product myProduct : productList) {

                out.print(myProduct.getProductType());
                out.print(TOKEN);
                out.print(myProduct.getCostPerSqFt());
                out.print(TOKEN);
                out.print(myProduct.getLaborCostPerSqFt());
                out.println();

            }
            out.flush();

        } catch (IOException ex) {

            Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List productDecode() {

        Scanner sc = null;
        List<Product> products = new ArrayList();

        int lineNumber = 0;

        try {
            sc = new Scanner(new BufferedReader(new FileReader("File" + File.separator + "productList.txt")));

            while (sc.hasNext()) {

                lineNumber++;

                if (lineNumber == 1) {
                    sc.nextLine();
                    continue;
                }

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split(",");

                Product myProduct = new Product();

                myProduct.setProductType(stringParts[0]);
                double costPerSqFt = Double.parseDouble(stringParts[1]);
                myProduct.setCostPerSqFt(costPerSqFt);
                double laborPerSqFt = Double.parseDouble(stringParts[2]);
                myProduct.setLaborCostPerSqFt(laborPerSqFt);

                products.add(myProduct);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return products;
    }

    public String testDecode() {

        Scanner sc = null;
        int lineNumber = 0;
        String testString = "";

        try {
            sc = new Scanner(new BufferedReader(new FileReader("File" + File.separator + "config.txt")));

            while (sc.hasNext()) {
                lineNumber++;

                if (lineNumber == 1) {
                    sc.nextLine();
                    continue;
                }

                String currentLine = sc.nextLine();
                String[] stringParts = currentLine.split(",");

                testString = stringParts[0];

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TaxesDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            sc.close();
        }

        return testString;
    }

    public String removeQuotes(Order order) {
        String customerName = order.getCustomerName();
        if (customerName.contains("\"")) {
            customerName = customerName.replace("\"", "");
        }
        return customerName;
    }

    public String escapeComma(Order order) {

        String customerName = order.getCustomerName();
        if (customerName.contains(",")) {

            customerName = customerName.replace(",", "\\,");
        }
        return customerName;
    }

}
