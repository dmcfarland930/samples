/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.addressbookweb.controllers;

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/address")
public class AddressController {

    private AddressBookDao addressDao;

    @Inject
    public AddressController(AddressBookDao dao) {

        this.addressDao = dao;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute Address address, BindingResult bindingResult, Map model) {

        if (bindingResult.hasErrors()) {

            List<Address> addresses = addressDao.list();
            model.put("addresses", addresses);
            return "home";

        }

        addressDao.create(address);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer addressId, Map model) {

        Address address = addressDao.get(addressId);

        model.put("addresses", address);
        model.put("addressShow", address);
        model.put("address", new Address());

        return "edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSubmit(@Valid @ModelAttribute Address address, BindingResult bindingResult, @PathVariable("id") Integer addressId, Map model) {

        if (bindingResult.hasErrors()) {
            Address addressShow = addressDao.get(addressId);
            model.put("addressShow", addressShow);
            model.put("addresses", address);
            return "edit";

        }

        addressDao.update(address);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer addressId) {

        Address address = addressDao.get(addressId);
        addressDao.delete(address.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer addressId, Map model) {

        Address address = addressDao.get(addressId);
        model.put("address", address);
        return "show";

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String show(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);
        model.put("address", new Address());

        return "search";

    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("entry") String entry,
            @RequestParam("selection") String selection, Map model) {

        List<Address> address;

        switch (selection) {

            case "lastName":
                address = addressDao.searchByLastName(entry);
                model.put("addresses", address);
                model.put("address", new Address());
                return "search";

            case "city":
                address = addressDao.searchByCity(entry);
                model.put("addresses", address);
                model.put("address", new Address());
                return "search";

            case "state":
                address = addressDao.searchByState(entry);
                model.put("addresses", address);
                model.put("address", new Address());
                return "search";

            case "zip":
                address = addressDao.searchByZip(entry);
                model.put("addresses", address);
                model.put("address", new Address());
                return "search";

            default:
                return "search";
        }
    }
}
