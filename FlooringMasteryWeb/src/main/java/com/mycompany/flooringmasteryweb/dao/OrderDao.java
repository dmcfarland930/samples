/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.dto.Order;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface OrderDao {

    double calculateOrderTotal(double laborTotal, double productTotal, double tax);

    Order create(Order order);
    
    Order get(Integer orderNumber);
    
    void update(Order order);

    void delete(Order order);

    List<Order> getList();

//    List<String> getListOfDates();
//
    List<Order> getOrdersOnDate(String date);
//
//    List makeListWithDate(String date);
//
//    void setDatesToOrders();
//
//    int setIdForDate(Order order, String date);
//
//    void setTestMode(boolean testMode);
//    
//    boolean checkFileDateExists(String date);


}
