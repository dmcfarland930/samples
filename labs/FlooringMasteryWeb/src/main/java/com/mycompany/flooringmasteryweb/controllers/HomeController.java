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

    @Inject
    public HomeController(OrderDao oDao, TaxesDao tDao, ProductDao pDao, FlooringData fData) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {
        Date date = new Date();
        String dateFormat = sdf.format(date);
        String dateString = dateFormat.replace("/", "");
        List<Order> orders = orderDao.getOrdersOnDate(dateString);
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("date", dateFormat);
        model.put("orders", orders);
        model.put("products", products);
        model.put("taxes", taxes);
        return "home";
    }

}
