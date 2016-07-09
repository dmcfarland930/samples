/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ContactDao {

    public Contact add(Contact contact);

    public void update(Contact contact);

    public void remove(Contact contact);

    public Contact get(Integer id);

    public List<Contact> list();
    
}
