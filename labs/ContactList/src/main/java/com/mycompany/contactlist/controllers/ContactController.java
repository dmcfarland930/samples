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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    private ContactDao contactDao;

    @Inject
    public ContactController(ContactDao dao) {

        this.contactDao = dao;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Contact contact) {

        contactDao.add(contact);
        return "redirect:/";

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute Contact contact, Map model) {

        List<Contact> contacts = contactDao.list();
        Contact editContact = new Contact();
        for (Contact myContact : contacts) {

            if (myContact.getId() == contact.getId()) {
                editContact = myContact;
                break;
            }

        }

        model.put("contact", editContact);

        return "edit";
    }

    @RequestMapping(value = "contact/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute Contact contact) {

        contactDao.update(contact);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@ModelAttribute Contact contact) {

        contactDao.remove(contact.getId());
        return "redirect:/";
    }

}
