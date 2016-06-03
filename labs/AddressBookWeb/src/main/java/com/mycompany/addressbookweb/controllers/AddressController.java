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
import org.springframework.stereotype.Controller;
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
    public String create(@ModelAttribute Address address) {

        addressDao.create(address);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer addressId, Map model) {

        Address address = addressDao.get(addressId);

        model.put("address", address);

        return "edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editSubmit(@ModelAttribute Address address) {

        addressDao.update(address);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer addressId) {

        Address address = addressDao.get(addressId);
        addressDao.delete(address.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/namefind", method = RequestMethod.GET)
    public String nameFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "namefind";
    }

    @RequestMapping(value = "namefind", method = RequestMethod.POST)
    public String nameFindSubmit(@RequestParam("lastName") String lastName, Map model) {

        List<Address> addresses = addressDao.searchByLastName(lastName);
        model.put("addresses", addresses);
        return "namefind";
    }

    @RequestMapping(value = "/cityfind", method = RequestMethod.GET)
    public String cityFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "cityfind";
    }

    @RequestMapping(value = "cityfind", method = RequestMethod.POST)
    public String cityFindSubmit(@RequestParam("city") String city, Map model) {

        List<Address> addresses = addressDao.searchByCity(city);
        model.put("addresses", addresses);

        return "cityfind";
    }

    @RequestMapping(value = "/statefind", method = RequestMethod.GET)
    public String stateFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "statefind";
    }

    @RequestMapping(value = "statefind", method = RequestMethod.POST)
    public String stateFind(@RequestParam("state") String state, Map model) {

        List<Address> addresses = addressDao.searchByState(state);
        model.put("addresses", addresses);
        return "statefind";
    }

    @RequestMapping(value = "/zipfind", method = RequestMethod.GET)
    public String zipFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "zipfind";
    }

    @RequestMapping(value = "zipfind", method = RequestMethod.POST)
    public String zipFind(@RequestParam("zip") String zip, Map model) {

        List<Address> addresses = addressDao.searchByZip(zip);
        model.put("addresses", addresses);
        return "zipfind";
    }

    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer addressId, Map model) {

        Address address = addressDao.get(addressId);
        model.put("address", address);
        return "show";

    }
}
