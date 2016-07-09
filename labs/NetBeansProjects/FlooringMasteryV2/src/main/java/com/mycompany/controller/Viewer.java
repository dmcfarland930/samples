/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.controller;

import com.mycompany.consoleio.ConsoleIO;
import com.mycompany.dao.OrderDao;
import com.mycompany.data.FlooringData;
import com.mycompany.dto.Order;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class Viewer {

    ConsoleIO console = new ConsoleIO();
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");

    public void displayOrderSummary(Order order) {

        FlooringData fd = new FlooringData();
        OrderDao orderDao = new OrderDao(fd);
        DecimalFormat money = new DecimalFormat("0.00");
        DecimalFormat area = new DecimalFormat("#.##");
        console.readString("===============================");
        console.readString(" Order Summary  #" + order.getOrderNumber());
        console.readStringSameLine(" Date: ");
        insertDateFormat(order.getOrderDate());
        console.readString("===============================");
        console.readString(" Name: " + order.getCustomerName());
        console.readString(" State: " + order.getState().toUpperCase());
        console.readString(" Product: " + order.getProductType().toUpperCase());
        console.readString(" Area: " + area.format(order.getArea()));
        console.readString("-------------------------------");
        console.readString(" Product Cost/SqFt: $" + money.format(order.getCostPerSqFt()));
        console.readString(" Labor Cost/SqFt: $" + money.format(order.getLaborCostPerSqFt()));
        console.readString(" Tax Rate: " + money.format(order.getTaxRate()) + "%");
        console.readString("-------------------------------");
        console.readString(" Total Product Cost: $" + money.format(order.getMaterialCost()));
        console.readString(" Total Labor Cost: $" + money.format(order.getTotalLaborCost()));
        console.readString(" Tax: $" + money.format(order.getTax()));
        console.readString(" Total: $" + money.format(orderDao.calculateOrderTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTax())));
        console.readString("===============================");

    }

    public void insertDateFormat(String date) {

        date = date.substring(0, 2) + "/" + date.substring(2, date.length());
        date = date.substring(0, 5) + "/" + date.substring(5, date.length());

        console.readString(date);
    }

}
