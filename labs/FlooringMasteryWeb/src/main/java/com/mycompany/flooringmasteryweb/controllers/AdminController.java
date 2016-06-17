/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.controllers;

import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.TaxesDao;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private TaxesDao taxDao;
    private ProductDao productDao;
    boolean testMode;

    @Inject
    public AdminController(TaxesDao tDao, ProductDao pDao) {

        this.taxDao = tDao;
        this.productDao = pDao;
//        this.testMode = testRead();

    }

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public String home(Map model) {

//        testMode = testRead();
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
//        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("taxes", taxes);
        return "adminhome";
    }

    @RequestMapping(value = "/addproducts", method = RequestMethod.GET)
    public String create(@ModelAttribute Product product, Map model) {

//        testMode = testRead();
        List<Product> products = productDao.getProductList();
//        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("product", new Product());

        return "/addproducts";

    }

    @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
    @ResponseBody
    public Product createSubmit(@Valid @RequestBody Product product, Model Map) {

        String lower = product.getProductType().toLowerCase();
        String capProductType = lower.substring(0, 1).toUpperCase() + lower.substring(1);
        product.setProductType(capProductType);

        return productDao.create(product);

    }

    @RequestMapping(value = "/addtaxrates", method = RequestMethod.GET)
    public String create(@ModelAttribute Taxes tax, Map model) {

//        testMode = testRead();
        List<Taxes> taxes = taxDao.getTaxesList();
//        model.put("test", showTest(testMode));
        model.put("taxesList", taxes);
        model.put("taxes", new Taxes());

        return "/addtaxrates";

    }

    @RequestMapping(value = "/addtaxrates", method = RequestMethod.POST)
    @ResponseBody
    public Taxes createSubmit(@Valid @RequestBody Taxes tax, Map model) {

//        testMode = testRead();
        List<Taxes> taxes = taxDao.getTaxesList();

        model.put("stateExists", "That state already exists!");

        String stateUpper = tax.getState().toUpperCase();
        tax.setState(stateUpper);

        return taxDao.create(tax);

    }

    @RequestMapping(value = "/editProduct/{productType}", method = RequestMethod.GET)
    @ResponseBody
    public Product editProduct(@RequestBody Product product) {

        return productDao.get(product.getId());

    }

    @RequestMapping(value = "/editTax/{state}", method = RequestMethod.GET)
    @ResponseBody
    public Taxes editTax(@RequestBody Taxes tax) {

        return taxDao.get(tax.getId());

    }

    @RequestMapping(value = "/productSave/{productType}", method = RequestMethod.PUT)
    @ResponseBody
    public Product editProductSubmit(@Valid @RequestBody Product product) {

//        testMode = testRead();

        List<Product> products = productDao.getProductList();
        productDao.update(product);
        return product;

    }

    @RequestMapping(value = "/taxSave{tax}", method = RequestMethod.PUT)
    @ResponseBody
    public Taxes editTaxSubmit(@Valid @RequestBody Taxes taxRate) {

//        testMode = testRead();

        List<Taxes> taxes = taxDao.getTaxesList();
        taxDao.update(taxRate);
        return taxRate;
    }

    @RequestMapping(value = "deleteProduct/{productType}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProducts(@RequestBody Product product) {

        Product productDel = productDao.get(product.getId());
        productDao.delete(productDel);
    }

    @RequestMapping(value = "deleteTax/{state}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteTaxes(@RequestBody Taxes tax) {

        Taxes taxDel = taxDao.get(tax.getId());
        taxDao.delete(taxDel);
    }

//    @RequestMapping(value = "/showproducts/{productType}", method = RequestMethod.GET)
//    public String showProducts(@PathVariable("productType") String productType, Map model) {
//
//        Product product = productDao.get(productType);
////        List<Product> products = productDao.getProductList();
//        model.put("test", showTest(testMode));
//        model.put("product", product);
////        model.put("products", products);
//
//        return "showproducts";
//
//    }
//
//    @RequestMapping(value = "/showtaxes/{state}", method = RequestMethod.GET)
//    public String showTaxes(@PathVariable("state") String state, Map model) {
//
//        Taxes tax = taxDao.get(state);
////        List<Taxes> taxes = taxDao.getTaxesList();
//        model.put("test", showTest(testMode));
////        model.put("tax", tax);
//        model.put("taxes", tax);
//
//        return "showtaxes";
//
//    }
    public String insertDateFormat(String date) {

        date = date.substring(0, 2) + "/" + date.substring(2, date.length());
        date = date.substring(0, 5) + "/" + date.substring(5, date.length());

        return date;
    }

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

}
