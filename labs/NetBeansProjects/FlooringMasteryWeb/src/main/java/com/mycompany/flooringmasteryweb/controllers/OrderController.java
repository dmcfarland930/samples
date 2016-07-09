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
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice - Daniel McFarland
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private OrderDao orderDao;
    private TaxesDao taxDao;
    private ProductDao productDao;

    @Inject
    //inject dao beans for use with flooring controller
    public OrderController(OrderDao oDao, TaxesDao tDao, ProductDao pDao) {
        this.orderDao = oDao;
        this.taxDao = tDao;
        this.productDao = pDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    //take the return object and turn it into a Json response object
    //create an order to be placed into database
    //@Valid checks for errors that might have occurred when command object was created. Validation occurs on the server side.
    public Order create(@Valid @RequestBody OrderCommand orderCommand) {

        //call method to set properties for order from command object
        Order order = setOrderData(orderCommand);

        //send order object to dao to be created and inserted into database
        //order object is returned to carry out ajax request
        return orderDao.create(order);

    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    //update an order when edit-order-button is clicked on editOrder modal
    public Order editSubmit(@Valid @RequestBody OrderCommand orderCommand) {

        //call method to set properties for order from command object
        Order order = setOrderData(orderCommand);

        /*set an order number to the newly created order object so the orderDao
        can communicate with the database to update the correct order*/
        order.setOrderNumber(orderCommand.getOrderNumber());

        //send order object to orderDao to be updated on the database
        orderDao.update(order);

        //order object is returned to carry out ajax request
        return order;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    //delete an order from database when delete-link is clicked on a table
    public void delete(@PathVariable("id") Integer orderId) {

        /*select an order from database where id matched orderId, provided by
        the @PathVariable annotation*/
        Order order = orderDao.get(orderId);

        //send order to orderDao to be deleted from the database
        orderDao.delete(order);
    }

    @RequestMapping(value = "vieworders", method = RequestMethod.GET)
    //populate view.jsp so the user can see orders in a filterable table
    public String view(Map model) {

        Date orderDateObj;
        //get a list of all orders from orderDao, provided by the database
        List<Order> orders = orderDao.getList();

        /*an enhanced for loop for setting the orderDateString property to each order
        in the order List. The orderDateString property (String) is used for display purposes only.
        This list will be used to be placed in model and populate the view table*/
        for (Order order : orders) {

            /*get the date property from an order and set it to the orderDateObj variable.
            the date property is a Date object*/
            orderDateObj = order.getDate();

            /*format orderDateObj using SimpleDateFormat("MM/dd/yyyy") and set it
            to the order's orderDateString property*/
            order.setOrderDateString(sdf.format(orderDateObj));
        }

        /*get a list of products and taxes from their respective daos. The objects
        are provided by the database.*/
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();

        /*Place the product and taxes list into the model to populate drop down
        fields in the orderEditModal*/
        model.put("products", products);
        model.put("taxes", taxes);

        //populates the view table with orders from list
        model.put("orders", orders);

        //sends user to the view.jsp
        return "vieworders";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    /*returns an order object to populate a modal through an ajax request
    and displays order details*/
    public Order show(@PathVariable("id") Integer id) throws ParseException {

        /*select an order from database where id matched orderId, provided by
        the @PathVariable annotation*/
        Order order = orderDao.get(id);

        //set missing properties (these missing properties include product and tax info)
        order = setShowData(order);

        //return order to perform ajax request and populate showOrderModal
        return order;
    }

    //set order object properties from orderCommand object
    public Order setOrderData(OrderCommand orderCommand) {

        //create new order object
        Order order = new Order();

        //set product and taxes ids for database communication
        order.setProduct(productDao.get(orderCommand.getProductId()));
        order.setTaxes(taxDao.get(orderCommand.getTaxesId()));

        order.setCustomerName(orderCommand.getCustomerName());
        order.setArea(orderCommand.getArea());

        order.setState(taxDao.get(orderCommand.getTaxesId()).getState());
        order.setTaxRate(taxDao.calculateTaxRate(order.getState()));

        order.setProductType(productDao.get(orderCommand.getProductId()).getProductType());
        order.setCostPerSqFt(productDao.get(orderCommand.getProductId()).getCostPerSqFt());
        order.setLaborCostPerSqFt(productDao.get(orderCommand.getProductId()).getLaborCostPerSqFt());

        //calculates total cost of materials, needs area and productType
        order.setMaterialCost(productDao.calculateTotalCostPerSqFt(orderCommand.getArea(), order.getProductType()));
        
        //calculates total cost of labor needs area and productType
        order.setTotalLaborCost(productDao.calculateTotalLaborCost(orderCommand.getArea(), order.getProductType()));
        
        //calculates tax toal, total cost of materials, total coast of labor, and tax rate
        order.setTax(taxDao.calculateTaxTotal(order.getMaterialCost(), order.getTotalLaborCost(), order.getTaxRate()));
        
        order.setOrderTotal(orderDao.calculateOrderTotal(order.getTotalLaborCost(), order.getMaterialCost(), order.getTax()));

        order.setDate(orderCommand.getDate());

        //returns order object with set properties
        return order;
    }

    //set order properties for showOrderModal
    public Order setShowData(Order order) {

        order.setOrderDateString(sdf.format(order.getDate()));

        //sets the state property of order to later display a String of the tax rate's state
        order.setState(order.getTaxes().getState());

        //retrives the tax rate from taxDao based on its state (provided by database)
        order.setTaxRate(taxDao.calculateTaxRate(order.getState()));

        //sets the productType property of order to later display a String of the product's type
        order.setProductType(order.getProduct().getProductType());

        //retrives the cost per square foot from productDao based on the product (provided by database)
        order.setCostPerSqFt(order.getProduct().getCostPerSqFt());

        //retrives the labor cost per square foot from productDao based on the product (provided by database)
        order.setLaborCostPerSqFt(order.getProduct().getLaborCostPerSqFt());

        //returns order back to show method
        return order;
    }
}
