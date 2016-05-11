/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.dao.OrderDao;
import com.mycompany.dao.ProductDao;
import com.mycompany.dao.TaxesDao;
import com.mycompany.data.FlooringData;
import com.mycompany.dto.Order;
import com.mycompany.dto.Product;
import com.mycompany.dto.Taxes;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class FlooringController {

    FlooringData fd = new FlooringData();
    OrderDao orderDao = new OrderDao();
    ProductDao productDao = new ProductDao();
    TaxesDao taxesDao = new TaxesDao();
    ConsoleIO console = new ConsoleIO();

    public void runApp() {

        selectFeature();

    }

    public void displayMainMenu() {

        console.readString("Welcome to flooring calculator.");
        console.readString("-------------------------------");
        console.readString(" 1) Display Orders             ");
        console.readString(" 2) Add Order                  ");
        console.readString(" 3) Edit Order                 ");
        console.readString(" 4) Remove Order               ");
        console.readString("-------------------------------");
        console.readString("                        [quit] ");
    }

    public void selectFeature() {

        boolean chooseAgain = true;
        String selection;

        while (chooseAgain == true) {

            displayMainMenu();

            selection = console.getString(">");
            switch (selection) {

                case "1":
                    //displayOrder
                    displayOrders();
                    break;
                case "2":
                    //addOrder
                    addOrder();
                    break;
                case "3":
                    //editOrder
                    editOrder();
                    break;
                case "4":
                    //removeOrder
                    removeOrder();
                    break;
                case "quit":
                case "q":
                    //displayOrder
                    console.readString("Goodbye!");
                    chooseAgain = false;
                    break;
                default:
                    //error
                    console.readString("\nThat is not an option!");

            }

        }

    }

    public void displayOrders() {

        List<Order> orderList;
        String date = console.getString("Please enter the date. (MM/DD/YYYY) \n>");

        try {
            orderList = fd.orderDecode(date);
            for (Order orderOnFile : orderList) {
                displayOrderSummary(orderOnFile);
            }
        } catch (Exception ex) {
            System.out.println("That date could not be found in our records! Please check your entry!");
        }

    }

    public void addOrder() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        String dateString = sdf.format(date);
        String areaString = "";
        boolean save = true;
        Order newOrder = new Order();

        console.readString("To save order and return to the main menu, enter \"0\" at any time.");

        newOrder.setCustomerName(console.getString("Enter your name.\n>"));

        if (!newOrder.getCustomerName().equals("0")) {
            newOrder.setState(enterState(false, newOrder));
        } else {
            newOrder.setState("0");
        }

        if (!newOrder.getState().equals("0")) {
            newOrder.setProductType(enterProduct(false, newOrder));
        } else {
            newOrder.setProductType("0");
        }

        if (!newOrder.getProductType().equals("0")) {
            newOrder.setArea(console.getDouble("Enter the area of your flooring in sq/ft.\n>", "That is not a valid entry."));
        } else {
            newOrder.setArea(0);
            areaString = Double.toString(newOrder.getArea());
        }

        orderDao.create(newOrder, dateString);

        if (newOrder.getCustomerName().equals("0") || newOrder.getState().equals("0") || newOrder.getProductType().equals("0") || areaString.equals("0")) {
            showIncompleteOrder(newOrder);
        } else {
            newOrder = setNewOrderProperties(newOrder);
            displayOrderSummary(newOrder);
            save = console.yesCheck("Submit this order? [yes/no]\n>", "Enter \"yes\" to save, \"no\" to discard.");
        }

        if (save == true) {
            console.readString("Order saved!");
        } else {
            console.readString("Order cancelled!");
            orderDao.delete(newOrder, dateString);
        }
    }

    public void editOrder() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        DecimalFormat areaF = new DecimalFormat("#.##");
        String dateString = sdf.format(date);
        int orderNum;
        double areaEdit = 0;
        String area;
        String areaString = "";
        String customerName;
        boolean found = false;
        boolean save = true;
        Order editOrder = new Order();
        List<Order> orders = fd.orderDecode(dateString);

        //displayOrders();
        orderNum = console.getInteger("Please enter your order number to edit.\n>", "That is an ivalid entry!");

        for (Order orderOnList : orders) {

            if (orderOnList.getOrderNumber() == orderNum) {
                editOrder = orderOnList;
                found = true;
            }

        }

        if (found == true) {

            console.readString("To save order and return to the main menu, enter \"0\" at any time.");

            customerName = console.getString("Enter your name. (" + editOrder.getCustomerName() + ")\n>");
            if (customerName.isEmpty()) {
                customerName = editOrder.getCustomerName();
                editOrder.setCustomerName(customerName);
            } else {
                editOrder.setCustomerName(customerName);
            }

            if (!editOrder.getCustomerName().equals("0")) {
                editOrder.setState(enterState(true, editOrder));
            } else {
                editOrder.setState("0");
            }

            if (!editOrder.getState().equals("0")) {
                editOrder.setProductType(enterProduct(true, editOrder));
            } else {
                editOrder.setProductType("0");
            }

            if (!editOrder.getProductType().equals("0")) {
                area = console.getString("Enter the area of your flooring in sq/ft.(" + areaF.format(editOrder.getArea()) + ")\n>");
                if (area.isEmpty()) {
                    area = Double.toString(editOrder.getArea());
                    areaEdit = Double.parseDouble(area);
                    editOrder.setArea(areaEdit);
                }
            } else {
                editOrder.setArea(0);
                areaString = Double.toString(editOrder.getArea());
            }

            orderDao.update(editOrder, dateString);

            if (editOrder.getCustomerName().equals("0") || editOrder.getState().equals("0") || editOrder.getProductType().equals("0") || areaString.equals("0")) {
                showIncompleteOrder(editOrder);
            } else {
                editOrder = setNewOrderProperties(editOrder);
                displayOrderSummary(editOrder);
                save = console.yesCheck("Submit this order? [yes/no]\n>", "Enter \"yes\" to save, \"no\" to discard.");
            }

            if (save == true) {
                console.readString("Order saved!");
            } else {
                console.readString("Order cancelled!");
                orderDao.delete(editOrder, dateString);
            }

        } else {
            console.readString("Order not found! Please check your order number carefully!");
        }
    }

    public void removeOrder() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        DecimalFormat areaF = new DecimalFormat("#.##");
        String dateString = sdf.format(date);
        int orderNum;
        boolean found = false;
        boolean delete = true;
        Order delOrder = new Order();
        List<Order> orders = fd.orderDecode(dateString);

        //displayOrders();
        orderNum = console.getInteger("Please enter your order number to remove.\n>", "That is an ivalid entry!");

        for (Order orderOnList : orders) {

            if (orderOnList.getOrderNumber() == orderNum) {
                delOrder = orderOnList;
                found = true;
            }

        }

        if (found == true) {

            displayOrderSummary(delOrder);
            
            delete = console.yesCheck("Are you sure you want to remove this order? [yes/no]\n>", "Enter \"yes\" to delete, \"no\" to keep.");

            if (delete == true) {
                console.readString("Order removed!");
                orderDao.delete(delOrder, dateString);
            } else {
                console.readString("Order saved!");
            }

        } else {
            console.readString("Order not found! Please check your order number carefully!");
        }
    }

    public String enterState(boolean edit, Order editOrder) {

        String state = "";
        boolean valid = false;
        while (!valid) {
            if (edit == true) {

                state = console.getString("Enter your state. We serve OH, PA, MI, and IN. (" + editOrder.getState() + ")\n>");

                if (state.isEmpty()) {
                    state = editOrder.getState();
                    valid = taxesDao.validateState(state);
                }

            } else {
                state = console.getString("Enter your state. We serve OH, PA, MI, and IN.\n>");
                valid = taxesDao.validateState(state);
            }
            if (!valid) {
                console.readString("Sorry, we do not serve that state!");
            }
        }
        return state;
    }

    public String enterProduct(boolean edit, Order editOrder) {
        String productType = "";
        boolean valid = false;
        while (!valid) {

            if (edit == true) {
                productType = console.getString("Enter your flooring type. We have Carpet, Laminate, Tile, and Wood. (" + editOrder.getProductType() + ")\n>");
                if (productType.isEmpty()) {
                    productType = editOrder.getProductType();
                    valid = productDao.validateProductType(productType);
                }
            } else {
                productType = console.getString("Enter your flooring type. We have Carpet, Laminate, Tile, and Wood.\n>");
                valid = productDao.validateProductType(productType);
            }
            if (!valid) {
                console.readString("We do not have that product!");
            }
        }
        return productType;
    }

    public void displayOrderSummary(Order order) {

        DecimalFormat money = new DecimalFormat("0.00");
        DecimalFormat area = new DecimalFormat("#.##");
        console.readString("=======================");
        console.readString(" Order Summary  #" + order.getOrderNumber());
        console.readString("=======================");
        console.readString(" Name: " + order.getCustomerName());
        console.readString(" State: " + order.getState().toUpperCase());
        console.readString(" Product: " + order.getProductType().toUpperCase());
        console.readString(" Area: " + area.format(order.getArea()));
        console.readString("-----------------------");
        console.readString(" Product Cost/SqFt: $" + money.format(order.getCostPerSqFt()));
        console.readString(" Labor Cost/SqFt: $" + money.format(order.getLaborCostPerSqFt()));
        console.readString(" Tax Rate: $" + money.format(order.getTaxRate()));
        console.readString("-----------------------");
        console.readString(" Total Product Cost: $" + money.format(order.getMaterialCost()));
        console.readString(" Total Labor Cost: $" + money.format(order.getTotalLaborCost()));
        console.readString(" Tax: $" + money.format(order.getTax()));
        console.readString(" Total: $" + money.format(orderDao.calculateOrderTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTax())));
        console.readString("=======================");

    }

    public Order setNewOrderProperties(Order newOrder) {

        newOrder.setCostPerSqFt(productDao.getCostPerSqFt(newOrder.getProductType()));
        newOrder.setMaterialCost(productDao.calculateTotalCostPerSqFt(newOrder.getArea(), newOrder.getProductType()));
        newOrder.setLaborCostPerSqFt(productDao.getLaborCostPerSqFt(newOrder.getProductType()));
        newOrder.setTotalLaborCost(productDao.calculateTotalLaborCost(newOrder.getArea(), newOrder.getProductType()));
        newOrder.setTaxRate(taxesDao.calculateTaxRate(newOrder.getState()));
        newOrder.setTax(taxesDao.calculateTaxTotal(newOrder.getMaterialCost(), newOrder.getTotalLaborCost(), newOrder.getTaxRate()));
        newOrder.setOrderTotal(orderDao.calculateOrderTotal(newOrder.getTotalLaborCost(), newOrder.getMaterialCost(), newOrder.getTax()));

        return newOrder;
    }

    public void showIncompleteOrder(Order newOrder) {

        String nameString = checkIncompleteName(newOrder);
        String stateString = checkIncompleteState(newOrder);
        String productString = checkIncompleteProduct(newOrder);
        String areaString = checkIncompleteArea(newOrder);

        console.readString("=======================");
        console.readString(" Incomplete Order");
        console.readString("=======================");
        console.readString(" Fields Remaining:");
        console.readString("-----------------------");
        console.readString(" Name: " + nameString);
        console.readString(" State: " + stateString);
        console.readString(" Product: " + productString);
        console.readString(" Area: " + areaString);
        console.readString("-----------------------");
        console.readString(" Order Number: " + newOrder.getOrderNumber());
        console.readString(" To update your order,"
                + "\n enter your product number"
                + "\n through the \"edit\" option");
        console.readString("=======================");
    }

    public String checkIncompleteName(Order newOrder) {
        String nameString;
        if (newOrder.getCustomerName().equals("0")) {
            nameString = "[blank]";
        } else {
            nameString = newOrder.getCustomerName();
        }
        return nameString;
    }

    public String checkIncompleteState(Order newOrder) {
        String stateString;
        if (newOrder.getState().equals("0")) {
            stateString = "[blank]";
        } else {
            stateString = newOrder.getState();
        }
        return stateString;
    }

    public String checkIncompleteProduct(Order newOrder) {
        String productString;
        if (newOrder.getProductType().equals("0")) {
            productString = "[blank]";
        } else {
            productString = newOrder.getProductType();
        }
        return productString;
    }

    public String checkIncompleteArea(Order newOrder) {
        String areaString;
        if (newOrder.getArea() == 0) {
            areaString = "[blank]";
        } else {
            areaString = String.valueOf(newOrder.getArea());

        }
        return areaString;
    }
}
