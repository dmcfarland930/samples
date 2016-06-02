/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.controllers.Viewer;
import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Order;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrderDaoImpl implements OrderDao {

    private FlooringData fd;
    private List<Order> orderList = new ArrayList();
    private List<String> listOfAllDates = new ArrayList();
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    private String dateString = sdf.format(date);
    int nextId = 1000;
    boolean testMode;

    public OrderDaoImpl(FlooringData fData) {

        this.fd = fData;
        setDatesToOrders();
        orderList
                .stream()
                .forEach((_item) -> {
                    nextId++;
                });
        testMode = fd.isTestMode();
    }

    @Override
    public Order create(Order order, String date) {

        boolean exists;
        exists = checkFileDateExists(date);

        order.setOrderNumber(nextId);
        listOfAllDates.add(date);
        if (!testMode) {
            if (!exists) {
                fd.orderEncode(date, orderList);
            }
            orderList = fd.orderDecode(date);
        }
        nextId++;
        orderList.add(order);
        if (!testMode) {
            fd.orderEncode(date, orderList);
        }

        return order;
    }

    @Override
    public Order get(Integer orderNumber) {

        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == orderNumber) {
                return myOrder;
            }
        }
        return null;
    }

    @Override
    public Order update(Order order, String date, boolean changeDate) {

        if (!testMode) {
            orderList = fd.orderDecode(date);
        }
        if (testMode == true && changeDate == true) {
            orderList = makeListWithDate(date);
        }
        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == order.getOrderNumber() && myOrder.getOrderDate().equals(order.getOrderDate())) {
                orderList.remove(myOrder);
                orderList.add(order);
                if (!testMode) {
                    fd.orderEncode(date, orderList);
                }
                return myOrder;
            }

        }
        return null;
    }

    @Override
    public void delete(Order order, String date) {

        Order found = null;

        if (!testMode) {
            orderList = fd.orderDecode(date);
        }
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

    @Override
    public List<Order> getList() {

        return new ArrayList(this.orderList);

    }

    @Override
    public List<String> getListOfDates() {

        Collections.sort(listOfAllDates);
        return new ArrayList(this.listOfAllDates);

    }

    @Override
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

    @Override
    public double calculateOrderTotal(double laborTotal, double productTotal, double tax) {

        double total;

        total = laborTotal + productTotal + tax;

        return total;
    }

    @Override
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @Override
    public void setDatesToOrders() {

        File dir = new File("File/Orders");
        File[] directoryListing = dir.listFiles();
        for (File file : directoryListing) {
            String[] fileName = file.getName().split("\\.");
            fileName = fileName[0].split("_");
            dateString = fileName[1];
            listOfAllDates.add(dateString);
            List<Order> orderList2 = fd.orderDecode(dateString);
            for (Order orders : orderList2) {

                orderList.add(orders);

            }

        }
    }

    @Override
    public boolean checkFileDateExists(String date) {

        boolean exists = false;
        for (String dates : listOfAllDates) {

            if (date.equals(dates)) {
                exists = true;
            }

        }
        return exists;
    }

    @Override
    public int setIdForDate(Order order, String date) {
        int newId = 1000;
        for (Order orderWithDate : orderList) {
            //if (orderWithDate.getOrderDate().equals(date)) {

            newId = orderWithDate.getOrderNumber();
            if (order.getOrderNumber() == newId) {
                newId++;
            }

            //}
        }
        return newId;
    }

    @Override
    public List makeListWithDate(String date) {
        List<Order> ordersWithDateEntry = new ArrayList();
        for (Order orderWithDate : orderList) {
            if (orderWithDate.getOrderDate().equals(date)) {
                ordersWithDateEntry.add(orderWithDate);
            }
        }
        return ordersWithDateEntry;
    }

    @Override
    public List decodeListToDisplay(String dateString) {
        Viewer view = new Viewer();

        if (testMode == true) {
            orderList = getOrdersOnDate(dateString);
        } else {
            orderList = fd.orderDecode(dateString);
        }

        orderList
                .stream()
                .forEach((orderOnFile) -> {
                    view.displayOrderSummary(orderOnFile);
                });

        return orderList;

    }

}
