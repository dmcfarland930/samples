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
@RequestMapping(value = "/address")
public class AddressController {

    private AddressBookDao addressDao;

    @Inject
    public AddressController(AddressBookDao dao) {

        this.addressDao = dao;

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Address create(@RequestBody Address address) {

        return addressDao.create(address);

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer addressId, Map model) {

        Address address = addressDao.get(addressId);

        model.put("addresses", address);
        model.put("addressShow", address);
        model.put("address", new Address());

        return "edit";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Address edit(@RequestBody Address address) {


        addressDao.update(address);
        return address;
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer addressId) {

        Address address = addressDao.get(addressId);
        addressDao.delete(address.getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address show(@PathVariable("id") Integer addressId) {

        Address address = addressDao.get(addressId);
        return address;

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
