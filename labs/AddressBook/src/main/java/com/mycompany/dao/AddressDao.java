/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Address;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface AddressDao {

    Address create(Address address);

    List decode();

    void delete(Address address);

    void encode();

    Address get(String lastName);

    Address get(Integer identifier);

    void update(Address address);

    List getByLastName(String lastName);

    List getByCity(String city);

    List getByZip(String zip);

    Map getByState(String state);

}
