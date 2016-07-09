package com.thesoftwareguild.interfaces.dao;



import com.thesoftwareguild.interfaces.dto.Address;
import java.util.List;


public interface AddressBookDao {

    public Address create(Address address);
    public void update(Address address);
    public Address get(Integer id);
    public void delete(Integer id);

    public List<Address> list();
    public List<Address> searchByLastName(String lastName);
    public List<Address> searchByCity(String city);
    public List<Address> searchByState(String state);
    public List<Address> searchByZip(String zip);


}