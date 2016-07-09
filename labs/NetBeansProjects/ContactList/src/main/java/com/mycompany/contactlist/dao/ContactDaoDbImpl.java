/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.contactlist.dao;

import com.mycompany.contactlist.dto.Contact;
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
public class ContactDaoDbImpl implements ContactDao {

    private static final String SQL_INSERT_CONTACT = "INSERT INTO contact (first_name, last_name, company, email, phone) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_CONTACT = "UPDATE contact set first_name = ?, last_name = ?, company = ?, email = ?, phone = ? WHERE id = ?";
    private static final String SQL_DELETE_CONTACT = "DELETE FROM contact WHERE id = ?";
    private static final String SQL_GET_CONTACT = "SELECT * FROM contact WHERE id = ?";
    private static final String SQL_GET_CONTACT_LIST = "SELECT * FROM contact";

    private JdbcTemplate jdbcTemplate;

    public ContactDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Contact add(Contact contact) {

        jdbcTemplate.update(SQL_INSERT_CONTACT,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getCompany(),
                contact.getEmail(),
                contact.getPhone());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        contact.setId(id);

        return contact;
    }

    @Override
    public void update(Contact contact) {

        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getCompany(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getId());

    }

    @Override
    public void remove(Contact contact) {

        jdbcTemplate.update(SQL_DELETE_CONTACT,
                contact.getId());

    }

    @Override
    public Contact get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_CONTACT, new ContactMapper(), id);

    }

    @Override
    public List<Contact> list() {

        return jdbcTemplate.query(SQL_GET_CONTACT_LIST, new ContactMapper());

    }

    private static final class ContactMapper implements RowMapper<Contact> {

        @Override
        public Contact mapRow(ResultSet rs, int i) throws SQLException {

            Contact contact = new Contact();

            contact.setId(rs.getInt("id"));
            contact.setFirstName(rs.getString("first_name"));
            contact.setLastName(rs.getString("last_name"));
            contact.setCompany(rs.getString("company"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));

            return contact;

        }

    }

}
