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
    List<Order> ordersList = new ArrayList();
    boolean testMode = false;

    public void runApp() {

        selectFeature();

    }

    public void displayMainMenu() {

        testMode = testMode();
        orderDao.setTestMode(testMode);

        if (testMode == true) {
            console.readString("YOU ARE IN TEST MODE");
            console.readString("-------------------------------");
        }
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
        String dateString = getDateEntry();

        try {

            if (testMode == true) {
                orderList = orderDao.getOrdersOnDate(dateString);
            } else {
                orderList = fd.orderDecode(dateString);
            }
            for (Order orderOnFile : orderList) {
                displayOrderSummary(orderOnFile);
            }

        } catch (Exception ex) {
            console.readString("No records for that date could be found! Please check your entry!\n");
        }

    }

    public void addOrder() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        String dateString = sdf.format(date);
        String areaString = "";
        boolean save = true;
        boolean changeDate = false;
        Order newOrder = new Order();
        newOrder.setOrderDate(dateString);
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

        newOrder = setNewOrderProperties(newOrder);
        orderDao.create(newOrder, dateString);

        if (newOrder.getCustomerName().equals("0") || newOrder.getState().equals("0") || newOrder.getProductType().equals("0") || areaString.equals("0")) {
            showIncompleteOrder(newOrder);
        } else {
            displayOrderSummary(newOrder);
            changeDate = console.yesCheck("Set the order's date? [yes/no]\n>", "To change date, enter \"yes\", to keep, enter \"no\".");
            if (changeDate == true) {

                dateString = getDateEntry();
                newOrder.setOrderDate(dateString);
                newOrder.setOrderNumber(orderDao.setIdForDate(newOrder, dateString));

                orderDao.update(newOrder, dateString, true);
                displayOrderSummary(newOrder);
            }
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

        DecimalFormat areaF = new DecimalFormat("#.##");
        int orderNum = 0;
        double areaEdit;
        String area;
        String dateString;
        String customerName = "";
        boolean found = false;
        boolean validDate = true;
        boolean quitEdit = false;
        boolean changeDate = false;
        Order editOrder = new Order();
        List<Order> orders = new ArrayList();

        dateString = getDateEntry();
        try {
            if (testMode == true) {
                orders = orderDao.getOrdersOnDate(dateString);
            } else {
                orders = fd.orderDecode(dateString);
            }
        } catch (Exception ex) {
            console.readString("No records for that date could be found! Please check your entry!\n");
            validDate = false;
        }

        if (validDate == true) {
            orderNum = console.getInteger("Please enter your order number to edit.\n>", "That is an ivalid entry!");

            for (Order orderOnList : orders) {

                if (orderOnList.getOrderNumber() == orderNum) {
                    editOrder = orderOnList;
                    found = true;
                    break;
                }

            }

            if (found == true) {

                console.readString("To return to the main menu and discard changes, enter \"0\" at any time.");

                customerName = console.getString("Enter your name. (" + editOrder.getCustomerName() + ")\n>");
                if (customerName.isEmpty()) {
                    customerName = editOrder.getCustomerName();
                    editOrder.setCustomerName(customerName);
                } else if (customerName.equals("0")) {
                    quitEdit = true;
                } else {
                    editOrder.setCustomerName(customerName);
                }

                if (!quitEdit) {

                    editOrder.setState(enterState(true, editOrder));

                    if (editOrder.getState().isEmpty()) {
                        editOrder.setState(editOrder.getState());
                    } else if (editOrder.getState().equals("0")) {
                        quitEdit = true;
                    }

                }
                if (!quitEdit) {

                    editOrder.setProductType(enterProduct(true, editOrder));
                    if (editOrder.getProductType().isEmpty()) {
                        editOrder.setProductType(editOrder.getProductType());
                    } else if (editOrder.getProductType().equals("0")) {
                        quitEdit = true;
                    }
                }
                if (!quitEdit) {
                    area = console.getString("Enter the area of your flooring in sq/ft.(" + areaF.format(editOrder.getArea()) + ")\n>");
                    if (area.isEmpty()) {
                        editOrder.setArea(editOrder.getArea());
                    } else if (area.equals("0")) {
                        editOrder.setArea(editOrder.getArea());
                        quitEdit = true;
                    } else {
                        areaEdit = Double.parseDouble(area);
                        editOrder.setArea(areaEdit);
                    }
                }

                if (!quitEdit) {
                    editOrder = setNewOrderProperties(editOrder);

                    orderDao.update(editOrder, dateString, false);
                    displayOrderSummary(editOrder);

                    changeDate = console.yesCheck("Set the order's date? [yes/no]\n>", "To change date, enter \"yes\", to keep, enter \"no\".");
                    if (changeDate == true) {

                        if (!testMode) {
                            orderDao.delete(editOrder, dateString);
                        }
                        
                        dateString = getDateEntry();
                        editOrder.setOrderDate(dateString);
                        editOrder.setOrderNumber(orderDao.setIdForDate(editOrder, dateString));

                        if (testMode == true) {
                            orderDao.update(editOrder, dateString, true);
                        } else {
                            orderDao.create(editOrder, dateString);
                        }
                        displayOrderSummary(editOrder);
                    }

                    console.readString("\nOrder updated!\n");
                }
            } else if (validDate == true || !found) {
                console.readString("Order not found! Please check your order number carefully!\n");
            }

        }
    }

    public void removeOrder() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
        String dateString = sdf.format(date);
        int orderNum;
        boolean found = false;
        boolean validDate = true;
        boolean delete;
        Order delOrder = new Order();
        List<Order> orderList = new ArrayList();
        dateString = getDateEntry();

        try {

            if (testMode == true) {
                orderList = orderDao.getOrdersOnDate(dateString);
            } else {
                orderList = fd.orderDecode(dateString);
            }

        } catch (Exception ex) {
            console.readString("No records for that date could be found! Please check your entry!\n");
            validDate = false;
        }

        if (validDate == true) {
            orderNum = console.getInteger("Please enter your order number to remove.\n>", "That is an ivalid entry!");

            for (Order orderOnList : orderList) {

                if (orderOnList.getOrderNumber() == orderNum) {
                    delOrder = orderOnList;
                    found = true;
                }

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

        } else if (validDate == true && !found) {
            console.readString("Order not found! Please check your order number carefully!\n");
        }
    }

    public String enterState(boolean edit, Order editOrder) {

        String state = "";
        boolean valid = false;
        while (!valid) {
            if (edit == true) {

                state = console.getString("Enter your state. We serve OH, PA, MI, and IN. (" + editOrder.getState().toUpperCase() + ")\n>");
                valid = taxesDao.validateState(state, true);
                if (state.isEmpty()) {
                    state = editOrder.getState();
                }

            } else {
                state = console.getString("Enter your state. We serve OH, PA, MI, and IN.\n>");
                valid = taxesDao.validateState(state, false);
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
                productType = console.getString("Enter your flooring type. We have Carpet, Laminate, Tile, and Wood. (" + editOrder.getProductType().toUpperCase() + ")\n>");
                valid = productDao.validateProductType(productType, true);
                if (productType.isEmpty()) {
                    productType = editOrder.getProductType();
                }
            } else {
                productType = console.getString("Enter your flooring type. We have Carpet, Laminate, Tile, and Wood.\n>");
                valid = productDao.validateProductType(productType, false);
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
        console.readString(" Date: " + order.getOrderDate());
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

    public String getDateEntry() {

        String dateEntry = console.getString("Please enter the date. (MM/DD/YYYY) \n>");
        String date = dateEntry.replace("/", "");

        return date;

    }

    public boolean testMode() {

        boolean testTrue = false;
        String test = fd.testDecode();
        if (test.equals("1")) {
            testTrue = true;
        }

        return testTrue;
    }

    public void setOrderDate(Order order, String date) {
        boolean changeDate;
        changeDate = console.yesCheck("Set the order's date? [yes/no]\n>", "To change date, enter \"yes\", to keep, enter \"no\".");
        if (changeDate == true) {
            date = getDateEntry();
            orderDao.delete(order, date);
            order.setOrderDate(date);
            orderDao.create(order, date);
            displayOrderSummary(order);
        }
    }

}
