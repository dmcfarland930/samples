/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.controllers;

import com.mycompany.flooringmasteryweb.dao.OrderDao;
import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.TaxesDao;
import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    private OrderDao orderDao;
    private TaxesDao taxDao;
    private ProductDao productDao;
    boolean testMode;

    @Inject
    public HomeController(OrderDao oDao, TaxesDao tDao, ProductDao pDao) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        Date date = new Date();
//        testMode = readTest();
        String dateFormat = sdf.format(date);
        List<Order> orders = orderDao.getOrdersOnDate(dateFormat);
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
//        model.put("test", showTest(testMode));
        String dateFormat2 = insertDateFormat(dateFormat);
        model.put("date", dateFormat2);

        if (orders.isEmpty()) {
            model.put("noOrders", "No orders placed today!!");
        } else {
            for(Order order : orders){
                order.setOrderDate(dateFormat2);
            }
            model.put("orders", orders);
        }

        model.put("products", products);
        model.put("taxes", taxes);
        model.put("orderCommand", new OrderCommand());
        return "home";
    }

    @RequestMapping(value = "/adminlogin", method = RequestMethod.GET)
    public String adminLogin(Map model) {

        return "adminlogin";
    }

    @RequestMapping(value = "adminlogin", method = RequestMethod.POST)
    public String adminLoginSubmit(@RequestParam("password") String password, Map model) {

        if (password.equals("DOGMEAT")) {
            return "redirect:admin/adminhome";
        } else if (password.isEmpty()) {
            String error = "Please enter a password.";
            model.put("hasError", "has-error");
            model.put("error", error);
            return "adminlogin";
        } else {
            String error = "Incorrect Password!";
            model.put("hasError", "has-error");
            model.put("error", error);
            return "adminlogin";
        }

    }

    public String insertDateFormat(String date) {

        date = date.substring(0, 2) + "/" + date.substring(2, date.length());
        date = date.substring(0, 5) + "/" + date.substring(5, date.length());

        return date;
    }

//    public boolean readTest() {
//
//        floorData.testDecode();
//        return testMode = floorData.isTestMode();
//
//    }
//
//    public String showTest(boolean testMode) {
//        String test = "";
//        if (testMode) {
//            test = "(TEST)";
//        }
//
//        return test;
//    }
}
