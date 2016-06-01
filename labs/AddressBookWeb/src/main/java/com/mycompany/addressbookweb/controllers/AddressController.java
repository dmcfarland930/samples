/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.addressbookweb.controllers;

import com.mycompany.addressbookweb.dao.AddressBookDao;
import com.mycompany.addressbookweb.dto.Address;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute Address address, Map model) {

        List<Address> addresses = addressDao.list();
        Address editAddress = new Address();
        for (Address myAddress : addresses) {

            if (Objects.equals(address.getId(), myAddress.getId())) {
                editAddress = myAddress;
                break;
            }

        }

        model.put("address", editAddress);

        return "edit";
    }

    @RequestMapping(value = "address/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Address address) {

        addressDao.update(address);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@ModelAttribute Address address) {

        addressDao.delete(address.getId());
        return "redirect:/";
    }

    @RequestMapping(value = "/namefind", method = RequestMethod.GET)
    public String nameFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "namefind";
    }

    @RequestMapping(value = "address/namefind", method = RequestMethod.POST)
    public String nameFind(@ModelAttribute Address address, Map model) {

        List<Address> addresses = addressDao.searchByLastName(address.getLastName());
        model.put("address.getLastName", address.getLastName());
        model.put("addresses", addresses);
//        return "namefind?id=${address.getLastName}";
        return "namefind";
    }
    
        @RequestMapping(value = "/cityfind", method = RequestMethod.GET)
    public String cityFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "cityfind";
    }

    @RequestMapping(value = "address/cityfind", method = RequestMethod.POST)
    public String cityFind(@ModelAttribute Address address, Map model) {

        List<Address> addresses = addressDao.searchByCity(address.getCity());
        model.put("address.getCity", address.getCity());
        model.put("addresses", addresses);
//        return "cityfind?id=${address.getCity}";
        return "cityfind";
    }

            @RequestMapping(value = "/statefind", method = RequestMethod.GET)
    public String stateFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "statefind";
    }

    @RequestMapping(value = "address/statefind", method = RequestMethod.POST)
    public String stateFind(@ModelAttribute Address address, Map model) {

        List<Address> addresses = addressDao.searchByState(address.getState());
        model.put("address.getState", address.getState());
        model.put("addresses", addresses);
//        return "statefind?id=${address.getstate}";
        return "statefind";
    }
    
            @RequestMapping(value = "/zipfind", method = RequestMethod.GET)
    public String zipFind(Map model) {

        List<Address> addresses = addressDao.list();
        model.put("addresses", addresses);

        return "zipfind";
    }

    @RequestMapping(value = "address/zipfind", method = RequestMethod.POST)
    public String zipFind(@ModelAttribute Address address, Map model) {

        List<Address> addresses = addressDao.searchByZip(address.getZip());
        model.put("address.getZip", address.getZip());
        model.put("addresses", addresses);
//        return "zipFind?id=${address.getZip}";
        return "zipfind";
    }

}
