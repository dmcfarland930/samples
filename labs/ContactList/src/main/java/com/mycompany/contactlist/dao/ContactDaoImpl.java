/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class ContactDaoImpl implements ContactDao {

    List<Contact> data = new ArrayList();
    private int nextId = 0;

    @Override
    public Contact add(Contact contact) {

        contact.setId(nextId++);
        data.add(contact);
        return contact;

    }

    @Override
    public void update(Contact contact) {

        for (Contact myContact : data) {
            if (myContact.getId() == contact.getId()) {
                data.remove(myContact);
                data.add(contact);
                break;
            }

        }

    }

    @Override
    public void remove(Contact contact) {
        
        data.remove(contact);

    }

    @Override
    public Contact get(Integer id) {

        for (Contact contact : data) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;

    }

    @Override
    public List<Contact> list() {

        return new ArrayList(data);

    }

}
