/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.controllers;

import com.mycompany.flooringmasteryweb.dao.AddressBookDao;
import com.mycompany.flooringmasteryweb.dto.Address;
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

}
