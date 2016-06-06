/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.controllers;

import com.mycompany.flooringmasteryweb.dao.ProductDao;
import com.mycompany.flooringmasteryweb.dao.TaxesDao;
import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Product;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    private FlooringData floorData;
    boolean testMode;

    @Inject
    public AdminController(TaxesDao tDao, ProductDao pDao, FlooringData fData) {

        this.taxDao = tDao;
        this.productDao = pDao;
        this.floorData = fData;
        this.testMode = testRead();

    }

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public String home(Map model) {

        testMode = testRead();
        List<Product> products = productDao.getProductList();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("taxes", taxes);
        return "adminhome";
    }

    @RequestMapping(value = "/addproducts", method = RequestMethod.GET)
    public String create(@ModelAttribute Product product, Map model) {

        testMode = testRead();
        List<Product> products = productDao.getProductList();
        model.put("test", showTest(testMode));
        model.put("products", products);
        model.put("product", new Product());

        return "/addproducts";

    }

    @RequestMapping(value = "/addproducts", method = RequestMethod.POST)
    public String createSubmit(@Valid @ModelAttribute Product product, BindingResult bindingResult, Map model) {

        testMode = testRead();
        List<Product> products = productDao.getProductList();
        model.put("test", showTest(testMode));
        model.put("products", products);
        
        String lower = product.getProductType().toLowerCase();
        String capProductType = lower.substring(0, 1).toUpperCase() + lower.substring(1);
        product.setProductType(capProductType);
        
        if (bindingResult.hasErrors()) {

            model.put("product", product);
            return "/addproducts";

        }

        productDao.create(product);
        model.put("id", product.getProductType());
        return "redirect:../admin/showproducts/{id}";

    }

    @RequestMapping(value = "/addtaxrates", method = RequestMethod.GET)
    public String create(@ModelAttribute Taxes tax, Map model) {

        testMode = testRead();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("taxesList", taxes);
        model.put("taxes", new Taxes());

        return "/addtaxrates";

    }

    @RequestMapping(value = "/addtaxrates", method = RequestMethod.POST)
    public String createSubmit(@Valid @ModelAttribute Taxes tax, BindingResult bindingResult, Map model) {

        testMode = testRead();
        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("taxesList", taxes);
        String stateUpper = tax.getState().toUpperCase();
        tax.setState(stateUpper);

        if (bindingResult.hasErrors()) {

            model.put("taxes", tax);
            return "/addtaxrates";

        }

        taxDao.create(tax);
        model.put("id", tax.getState());
        return "redirect:../admin/showtaxes/{id}";

    }

    @RequestMapping(value = "/editproduct/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") String productType, Map model) {

        Product product = productDao.get(productType);

        model.put("test", showTest(testMode));
        model.put("productShow", product);
        model.put("product", product);

        return "/editproduct";
    }

    @RequestMapping(value = "/edittaxrate/{id}", method = RequestMethod.GET)
    public String editTax(@PathVariable("id") String taxRate, Map model) {

        Taxes taxes = taxDao.get(taxRate);

        model.put("test", showTest(testMode));
        model.put("taxesShow", taxes);
        model.put("taxes", taxes);

        return "/edittaxrate";
    }

    @RequestMapping(value = "/editproduct/{id}", method = RequestMethod.POST)
    public String editProductSubmit(@Valid @ModelAttribute Product product, BindingResult bindingResult, @PathVariable("id") String productType, Map model) {

        testMode = testRead();

        if (bindingResult.hasErrors()) {
            model.put("productShow", productDao.get(productType));
            model.put("products", product);
            return "/editproduct";

        }

        List<Product> products = productDao.getProductList();
        model.put("test", showTest(testMode));
        model.put("products", products);
        productDao.update(product, productType);
        model.put("id", product.getProductType());
        return "redirect:../showproducts/{id}";
    }

    @RequestMapping(value = "/edittaxrate/{id}", method = RequestMethod.POST)
    public String editTaxSubmit(@Valid @ModelAttribute Taxes taxRate, BindingResult bindingResult, @PathVariable("id") String tax, Map model) {

        testMode = testRead();

        if (bindingResult.hasErrors()) {
            model.put("taxesShow", taxDao.get(tax));
            model.put("taxes", taxRate);
            return "/edittaxrate";

        }

        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
        model.put("taxes", taxes);
        taxDao.update(taxRate, tax);
        model.put("id", taxRate.getState());
        return "redirect:../showtaxes/{id}";
    }

    @RequestMapping(value = "/deleteproducts/{id}", method = RequestMethod.GET)
    public String deleteProducts(@PathVariable("id") String productType) {

        Product product = productDao.get(productType);

        productDao.delete(product);
        return "redirect:/./admin/adminhome";
    }

    @RequestMapping(value = "/deletetaxes/{id}", method = RequestMethod.GET)
    public String deleteTaxes(@PathVariable("id") String state) {

        Taxes tax = taxDao.get(state);

        taxDao.delete(tax);
        return "redirect:/./admin/adminhome";
    }

    @RequestMapping(value = "/showproducts/{productType}", method = RequestMethod.GET)
    public String showProducts(@PathVariable("productType") String productType, Map model) {

        Product product = productDao.get(productType);
//        List<Product> products = productDao.getProductList();
        model.put("test", showTest(testMode));
        model.put("product", product);
//        model.put("products", products);

        return "showproducts";

    }

    @RequestMapping(value = "/showtaxes/{state}", method = RequestMethod.GET)
    public String showTaxes(@PathVariable("state") String state, Map model) {

        Taxes tax = taxDao.get(state);
//        List<Taxes> taxes = taxDao.getTaxesList();
        model.put("test", showTest(testMode));
//        model.put("tax", tax);
        model.put("taxes", tax);

        return "showtaxes";

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
