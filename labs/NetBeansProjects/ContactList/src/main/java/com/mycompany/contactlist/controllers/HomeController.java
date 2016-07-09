/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.contactlist.controllers;

import com.mycompany.contactlist.dao.ContactDao;
import com.mycompany.contactlist.dto.Contact;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private ContactDao contactDao;

    @Inject
    public HomeController(ContactDao dao) {
        this.contactDao = dao;

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Contact> contacts = contactDao.list();
        
        model.put("contacts", contacts);
        model.put("contact", new Contact());
        
        return "home";
    }


}
