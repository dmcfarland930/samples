/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Order;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrderDao {

    private FlooringData fd = new FlooringData();
    private List<Order> orderList = new ArrayList();
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    private String dateString = sdf.format(date);
    int nextId = 1000;
    boolean testMode;

    public OrderDao() {

        setDatesToOrders();

        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == nextId) {
                nextId++;
            }

        }
    }

    public Order create(Order order, String date) {

        order.setOrderNumber(nextId);
        if(!testMode){
            orderList = fd.orderDecode(date);
        }
        nextId++;
        orderList.add(order);
        if (!testMode) {
            fd.orderEncode(date, orderList);
        }

        return order;
    }

    public Order get(Integer orderNumber) {

        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == orderNumber) {
                return myOrder;
            }
        }
        return null;
    }

    public void update(Order order, String date, boolean changeDate) {

        if (!testMode) {
            orderList = fd.orderDecode(date);
        }
        if(testMode == true && changeDate == true){
            orderList = makeListWithDate(date);
        }
        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == order.getOrderNumber() && myOrder.getOrderDate().equals(order.getOrderDate())) {
                orderList.remove(myOrder);
                orderList.add(order);
                break;
            }

        }
        if (!testMode) {
            fd.orderEncode(date, orderList);
        }
    }

    public void delete(Order order, String date) {

        Order found = null;

        for (Order myOrder : orderList) {

            if (myOrder.getOrderNumber() == order.getOrderNumber()) {

                found = myOrder;
                break;
            }
        }
        orderList.remove(found);

        if (!testMode) {
            fd.orderEncode(date, orderList);
        }
    }

    public List<Order> getList() {

        return new ArrayList(this.orderList);

    }

    public List<Order> getOrdersOnDate(String date) {
        List<Order> myOrderList = new ArrayList<>();
        for (Order myOrder : orderList) {
            String date2 = myOrder.getOrderDate();
            if (date2.equals(date)) {
                myOrderList.add(myOrder);
            }
        }
        return myOrderList;
    }

    public double calculateOrderTotal(double laborTotal, double productTotal, double tax) {

        double total;

        total = laborTotal + productTotal + tax;

        return total;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void setDatesToOrders() {

        File dir = new File("File/Orders");
        File[] directoryListing = dir.listFiles();
        for (File file : directoryListing) {
            String[] fileName = file.getName().split("\\.");
            fileName = fileName[0].split("_");
            dateString = fileName[1];
            List<Order> orderList2 = fd.orderDecode(dateString);
            for (Order orders : orderList2) {

                orderList.add(orders);

            }

        }
    }

    public int setIdForDate(Order order, String date) {
        int newId = 1000;
        for (Order orderWithDate : orderList) {
            if (orderWithDate.getOrderDate().equals(date)) {

                newId = orderWithDate.getOrderNumber();
                if (order.getOrderNumber() == newId) {
                    newId++;
                }

            }
        }
        return newId;
    }

    public List makeListWithDate(String date) {
        List<Order> ordersWithDateEntry = new ArrayList();
        for (Order orderWithDate : orderList) {
            if (orderWithDate.getOrderDate().equals(date)) {
                ordersWithDateEntry.add(orderWithDate);
            }
        }
        return ordersWithDateEntry;
    }
}
