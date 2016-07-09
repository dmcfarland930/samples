/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.addressbookweb.dao;

import com.mycompany.addressbookweb.dto.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class AddressBookDbImpl implements AddressBookDao {

    private static final String SQL_INSERT_ADDRESS = "INSERT INTO address (first_name, last_name, street_number, street_name, city, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE address SET first_name = ?, last_name = ?, street_number = ?, street_name = ?, city = ?, state = ?, zip = ? WHERE id = ?";
    private static final String SQL_DELETE_ADDRESS = "DELETE FROM address WHERE id = ?";
    private static final String SQL_GET_ADDRESS = "SELECT * FROM address WHERE id = ?";
    private static final String SQL_GET_ADDRESS_LIST = "SELECT * FROM address";
    private static final String SQL_GET_NAME_LIST = "SELECT * FROM address WHERE last_name = ?";
    private static final String SQL_GET_CITY_LIST = "SELECT * FROM address WHERE city = ?";
    private static final String SQL_GET_STATE_LIST = "SELECT * FROM address WHERE state = ?";
    private static final String SQL_GET_ZIP_LIST = "SELECT * FROM address WHERE zip = ?";

    private JdbcTemplate jdbcTemplate;

    public AddressBookDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Address create(Address address) {

        jdbcTemplate.update(SQL_INSERT_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getState(),
                address.getZip());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        address.setId(id);

        return address;
    }

    @Override
    public void update(Address address) {

        jdbcTemplate.update(SQL_UPDATE_ADDRESS,
                address.getFirstName(),
                address.getLastName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getId());

    }

    @Override
    public void delete(Integer identifier) {

        jdbcTemplate.update(SQL_DELETE_ADDRESS,
                identifier);

    }

    @Override
    public Address get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_ADDRESS, new AddressMapper(), id);

    }

    @Override
    public List<Address> list() {

        return jdbcTemplate.query(SQL_GET_ADDRESS_LIST, new AddressMapper());

    }

    @Override
    public List<Address> searchByLastName(String lastName) {

        return jdbcTemplate.query(SQL_GET_NAME_LIST, new AddressMapper(), lastName);
    }

    @Override
    public List<Address> searchByCity(String city) {

        return jdbcTemplate.query(SQL_GET_CITY_LIST, new AddressMapper(), city);

    }

    @Override
    public List<Address> searchByState(String state) {

        return jdbcTemplate.query(SQL_GET_STATE_LIST, new AddressMapper(), state);

    }

    @Override
    public List<Address> searchByZip(String zip) {
        return jdbcTemplate.query(SQL_GET_ZIP_LIST, new AddressMapper(), zip);

    }

    private static final class AddressMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int i) throws SQLException {

            Address address = new Address();

            address.setId(rs.getInt("id"));
            address.setFirstName(rs.getString("first_name"));
            address.setLastName(rs.getString("last_name"));
            address.setStreetNumber(rs.getString("street_number"));
            address.setStreetName(rs.getString("street_name"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setZip(rs.getString("zip"));

            return address;

        }

    }

}
