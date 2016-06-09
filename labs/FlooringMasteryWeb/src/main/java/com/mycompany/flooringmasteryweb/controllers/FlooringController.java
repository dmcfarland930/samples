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
import com.mycompany.flooringmasteryweb.dto.OrderCommand;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/order")
public class FlooringController {

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private OrderDao orderDao;
    private TaxesDao taxDao;
    private ProductDao productDao;
    private FlooringData floorData;
    boolean testMode;

    @Inject
    public FlooringController(OrderDao oDao, TaxesDao tDao, ProductDao pDao, FlooringData fData) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;
        this.floorData = fData;
        this.testMode = testRead();

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Order create(@RequestBody OrderCommand orderCommand) {

//        if (bindingResult.hasErrors()) {
//
//            Date date = new Date();
//            testMode = testRead();
//            String dateFormat = sdf.format(date);
//            String dateString = dateFormat.replace("/", "");
//            List<Order> orders = orderDao.getOrdersOnDate(dateString);
//            List<Product> products = productDao.getProductList();
//            List<Taxes> taxes = taxDao.getTaxesList();
//            model.put("test", showTest(testMode));
//            model.put("date", dateFormat);
//            model.put("orders", orders);
//            model.put("products", products);
//            model.put("taxes", taxes);
//            model.put("orderCommand", orderCommand);
//            return "home";
//        }
        Order order = new Order();
        order.setCustomerName(orderCommand.getCustomerName());
        order.setState(orderCommand.getState());
        order.setArea(orderCommand.getArea());
        order.setProductType(orderCommand.getProductType());
        order.setCostPerSqFt(productDao.getCostPerSqFt(orderCommand.getProductType()));
        order.setMaterialCost(productDao.calculateTotalCostPerSqFt(orderCommand.getArea(), orderCommand.getProductType()));
        order.setLaborCostPerSqFt(productDao.getLaborCostPerSqFt(orderCommand.getProductType()));
        order.setTotalLaborCost(productDao.calculateTotalLaborCost(orderCommand.getArea(), orderCommand.getProductType()));
        order.setTaxRate(taxDao.calculateTaxRate(orderCommand.getState()));
        order.setTax(taxDao.calculateTaxTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTaxRate()));
        order.setOrderTotal(orderDao.calculateOrderTotal(order.getTotalLaborCost(), order.getMaterialCost(), order.getTax()));
        order.setOrderDate(sdf.format(orderCommand.getDate()));

        return orderDao.create(order, order.getOrderDate().replace("/", ""));

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer orderId, Map model) {

        Order order = orderDao.get(orderId);
        String date = insertDateFormat(order.getOrderDate());
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();

        model.put("orderCommand", new OrderCommand());
        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("taxes", taxes);
        model.put("date", date);
        model.put("order", order);

        return "edit";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Order editSubmit(@RequestBody Order orderCommand) {

//        if (bindingResult.hasErrors()) {
//
//            Order order = orderDao.get(orderId);
//            String date = insertDateFormat(order.getOrderDate());
//            List<Product> products = productDao.getProductList();
//            List<Taxes> taxes = taxDao.getTaxesList();
//
//            model.put("orderCommand", orderCommand);
//            model.put("test", showTest(testMode));
//            model.put("products", products);
//            model.put("taxes", taxes);
//            model.put("date", date);
//            model.put("order", order);
//
//        }
        Order order = new Order();
        order.setOrderNumber(orderCommand.getOrderNumber());
        order.setCustomerName(orderCommand.getCustomerName());
        order.setState(orderCommand.getState());
        order.setArea(orderCommand.getArea());
        order.setProductType(orderCommand.getProductType());
        order.setCostPerSqFt(productDao.getCostPerSqFt(orderCommand.getProductType()));
        order.setMaterialCost(productDao.calculateTotalCostPerSqFt(orderCommand.getArea(), orderCommand.getProductType()));
        order.setLaborCostPerSqFt(productDao.getLaborCostPerSqFt(orderCommand.getProductType()));
        order.setTotalLaborCost(productDao.calculateTotalLaborCost(orderCommand.getArea(), orderCommand.getProductType()));
        order.setTaxRate(taxDao.calculateTaxRate(orderCommand.getState()));
        order.setTax(taxDao.calculateTaxTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTaxRate()));
        order.setOrderTotal(orderDao.calculateOrderTotal(order.getTotalLaborCost(), order.getMaterialCost(), order.getTax()));
        order.setOrderDate(orderCommand.getOrderDate());

        String dateString = order.getOrderDate().replace("/", "");
        order.setOrderDate(dateString);
        orderDao.update(order, dateString, false);
        String dateFormat = insertDateFormat(order.getOrderDate());
        order.setOrderDate(dateFormat);

        return order;
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer orderId) {

        Order order = orderDao.get(orderId);
        //String date = insertDateFormat(order.getOrderDate());
        orderDao.delete(order, order.getOrderDate());

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String show(Map model) {

        Date date = new Date();

        String dateString = sdf.format(date).replace("/", "");

        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        List<Order> orders = orderDao.getOrdersOnDate(dateString);
        model.put("test", showTest(testMode));
        model.put("date", sdf.format(date));
        model.put("products", products);
        model.put("taxes", taxes);
        if (orders.isEmpty()) {
            model.put("noOrders", "No orders were found for this date!");
        } else {
            model.put("orders", orders);
        }

        return "search";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("date") String date, Map model) {

        String dateString = date.replace("/", "");
        List<Order> orders = orderDao.getOrdersOnDate(dateString);

        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("date", date);
        model.put("products", products);
        model.put("taxes", taxes);
        if (orders.isEmpty()) {
            model.put("noOrders", "No orders were found for this date!");
        } else {
            model.put("orders", orders);
        }
        return "search";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order show(@PathVariable("id") Integer id) {

        Order order = orderDao.get(id);
        String dateString = insertDateFormat(order.getOrderDate());
        order.setOrderDate(dateString);

        return order;

    }

    public String insertDateFormat(String date) {

        date = date.substring(0, 2) + "/" + date.substring(2, date.length());
        date = date.substring(0, 5) + "/" + date.substring(5, date.length());

        return date;
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
