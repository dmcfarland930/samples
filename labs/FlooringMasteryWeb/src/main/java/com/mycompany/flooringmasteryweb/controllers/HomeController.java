/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.controllers;

import com.mycompany.flooringmasteryweb.dao.OrderDao;
import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.TaxesDao;
import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Order;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyy");
    private OrderDao orderDao;
    private TaxesDao taxDao;
    private ProductDao productDao;
    private FlooringData floorData;
    boolean testMode;

    @Inject
    public HomeController(OrderDao oDao, TaxesDao tDao, ProductDao pDao, FlooringData fData) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;
        this.floorData = fData;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        Date date = new Date();
        testMode = testRead();
        String dateFormat = sdf.format(date);
        String dateString = dateFormat.replace("/", "");
        List<Order> orders = orderDao.getOrdersOnDate(dateString);
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("date", dateFormat);
        model.put("orders", orders);
        model.put("products", products);
        model.put("taxes", taxes);
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
        } else {
            String error = "Incorrect Password!";
            model.put("error", error);
            return "return adminlogin";
        }

    }

    public boolean testRead() {

        floorData.testDecode();
        return testMode = floorData.isTestMode();

    }

    public String showTest(boolean testMode) {
        String test = "";
        if (testMode) {
            test = "(TEST)";
        }

        return test;
    }

}
