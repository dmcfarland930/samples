/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Order;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class OrderDao {

    private boolean isTest;
    private FlooringData fd = new FlooringData();
    private List<Order> orderList = new ArrayList();
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    private String dateString = sdf.format(date);
    int nextId = 1000;

    public OrderDao() {

        orderList = fd.orderDecode(dateString);

        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == nextId) {
                nextId++;
            }

        }
    }

    public Order create(Order order, String date) {

        order.setOrderNumber(nextId);
        nextId++;
        orderList.add(order);
        fd.orderEncode(date, orderList);

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

    public void update(Order order, String date) {

        for (Order myOrder : orderList) {
            if (myOrder.getOrderNumber() == order.getOrderNumber()) {
                orderList.remove(myOrder);
                orderList.add(order);
                break;
            }

        }
        fd.orderEncode(date, orderList);

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

        fd.orderEncode(date, orderList);

    }

    public List<Order> getList() {

        return new ArrayList(this.orderList);

    }

    public double calculateOrderTotal(double laborTotal, double productTotal, double tax) {

        double total;

        total = laborTotal + productTotal + tax;

        return total;
    }
}
