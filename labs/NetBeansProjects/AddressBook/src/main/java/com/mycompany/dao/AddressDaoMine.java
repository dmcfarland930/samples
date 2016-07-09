/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.AddressMine;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface AddressDaoMine {

    AddressMine create(AddressMine address);

    List decode();

    void delete(AddressMine address);

    void encode();

    AddressMine get(String lastName);

    AddressMine get(Integer identifier);

    void update(AddressMine address);

    List getByLastName(String lastName);

    List getByCity(String city);

    List getByZip(String zip);

    Map getByState(String state);

}
