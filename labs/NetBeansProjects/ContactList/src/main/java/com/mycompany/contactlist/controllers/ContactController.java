/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.contactlist.controllers;

import com.mycompany.contactlist.dao.ContactDao;
import com.mycompany.contactlist.dto.Contact;
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Contact create(@Valid @RequestBody Contact contact) {

        return contactDao.add(contact);
        
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer contactId, Map model) {

        Contact contact = contactDao.get(contactId);

        model.put("contact", contact);

        return "edit";
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Contact edit(@Valid @RequestBody Contact contact) {

        
        contactDao.update(contact);
        
        return contact;
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer contactId) {

        Contact contact = contactDao.get(contactId);
        contactDao.remove(contact);
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact show(@PathVariable("id") Integer contactId) {

        Contact contact = contactDao.get(contactId);
        
        return contact;
        
    }
}
