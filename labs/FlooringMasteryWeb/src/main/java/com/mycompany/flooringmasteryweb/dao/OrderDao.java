/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.dto.Order;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    double calculateOrderTotal(double laborTotal, double productTotal, double tax);

    boolean checkFileDateExists(String date);

    Order create(Order order, String date);

    void delete(Order order, String date);

    Order get(Integer orderNumber);

    List<Order> getList();

    List<String> getListOfDates();

    List<Order> getOrdersOnDate(String date);

    List makeListWithDate(String date);

    void setDatesToOrders();

    List decodeListToDisplay(String dateString);

    int setIdForDate(Order order, String date);

    void setTestMode(boolean testMode);

    Order update(Order order, String date, boolean changeDate);

}
