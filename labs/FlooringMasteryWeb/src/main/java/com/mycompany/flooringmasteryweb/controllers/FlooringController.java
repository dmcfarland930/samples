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
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
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
    boolean testMode;

    @Inject
    public FlooringController(OrderDao oDao, TaxesDao tDao, ProductDao pDao) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;
//        this.testMode = testRead();

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Order create(@Valid @RequestBody OrderCommand orderCommand) {

        Order order = setOrderData(orderCommand);
        return orderDao.create(order);

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer orderId, Map model) {

        Order order = orderDao.get(orderId);
        String date = insertDateFormat(order.getOrderDate());
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();

        model.put("orderCommand", new OrderCommand());
//        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("taxes", taxes);
        model.put("date", date);
        model.put("order", order);

        return "edit";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Order editSubmit(@Valid @RequestBody OrderCommand orderCommand) {

        Order order = setOrderData(orderCommand);
        order.setOrderNumber(orderCommand.getOrderNumber());
        orderDao.update(order);

        return order;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer orderId) {

        Order order = orderDao.get(orderId);
        //String date = insertDateFormat(order.getOrderDate());
        orderDao.delete(order);

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String show(Map model) {

        Date date = new Date();

        String dateString = sdf.format(date).replace("/", "");

        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
//        List<Order> orders = orderDao.getOrdersOnDate(dateString);
//        model.put("test", showTest(testMode));
        model.put("date", sdf.format(date));
        model.put("products", products);
        model.put("taxes", taxes);
//        if (orders.isEmpty()) {
//            model.put("noOrders", "No orders were found for this date!");
//        } else {
//            model.put("orders", orders);
//        }

        return "search";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("date") String date, Map model) {

        String dateString = date.replace("/", "");
//        Date orderDate = new Date();
//        try {
//            orderDate = sdf.parse(dateString);
//        } catch (ParseException ex) {
//            Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        List<Order> orders = orderDao.getOrdersOnDate(dateString);

        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("products", products);
        model.put("taxes", taxes);
//        model.put("test", showTest(testMode));
        model.put("date", date);
        if (orders.isEmpty()) {
            model.put("noOrders", "No orders were found for this date!");
        } else {
            model.put("orders", orders);
        }
        return "search";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order show(@PathVariable("id") Integer id, Map model) {

        Order order = orderDao.get(id);

        try {
            order.setDate(sdf.parse(sdf.format(order.getDate())));
        } catch (ParseException ex) {
            Logger.getLogger(FlooringController.class.getName()).log(Level.SEVERE, null, ex);
        }

        order = setShowData(order);
        return order;

    }

    public String insertDateFormat(String date) {

        date = date.substring(0, 2) + "/" + date.substring(2, date.length());
        date = date.substring(0, 5) + "/" + date.substring(5, date.length());

        return date;
    }
//
//    public boolean testRead() {
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

    public Order setOrderData(OrderCommand orderCommand) {

        Order order = new Order();
        order.setProduct(productDao.get(orderCommand.getProductId()));
        order.setTaxes(taxDao.get(orderCommand.getTaxesId()));
        order.setCustomerName(orderCommand.getCustomerName());
        order.setState(taxDao.get(orderCommand.getTaxesId()).getState());
        order.setArea(orderCommand.getArea());
        order.setProductType(productDao.get(orderCommand.getProductId()).getProductType());
        order.setCostPerSqFt(productDao.get(orderCommand.getProductId()).getCostPerSqFt());
        order.setMaterialCost(productDao.calculateTotalCostPerSqFt(orderCommand.getArea(), order.getProductType()));
        order.setLaborCostPerSqFt(productDao.get(orderCommand.getProductId()).getLaborCostPerSqFt());
        order.setTotalLaborCost(productDao.calculateTotalLaborCost(orderCommand.getArea(), order.getProductType()));
        order.setTaxRate(taxDao.calculateTaxRate(order.getState()));
        order.setTax(taxDao.calculateTaxTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTaxRate()));
        order.setOrderTotal(orderDao.calculateOrderTotal(order.getTotalLaborCost(), order.getMaterialCost(), order.getTax()));
        order.setDate(orderCommand.getDate());

        return order;
    }

    public Order setShowData(Order order) {

        order.setState(order.getTaxes().getState());
        order.setArea(order.getArea());
        order.setProductType(order.getProduct().getProductType());
        order.setCostPerSqFt(order.getProduct().getCostPerSqFt());
        order.setMaterialCost(productDao.calculateTotalCostPerSqFt(order.getArea(), order.getProductType()));
        order.setLaborCostPerSqFt(order.getProduct().getLaborCostPerSqFt());
        order.setTotalLaborCost(productDao.calculateTotalLaborCost(order.getArea(), order.getProductType()));
        order.setTaxRate(taxDao.calculateTaxRate(order.getState()));
        order.setTax(taxDao.calculateTaxTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTaxRate()));
        order.setOrderTotal(orderDao.calculateOrderTotal(order.getTotalLaborCost(), order.getMaterialCost(), order.getTax()));
        order.setDate(order.getDate());

        return order;
    }
}
