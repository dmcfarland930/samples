/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;

import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class TaxesDaoDbImpl implements TaxesDao {

    private static final String SQL_INSERT_TAX = "INSERT INTO taxes (state, rate) VALUES (?, ?)";
    private static final String SQL_UPDATE_TAX = "UPDATE taxes SET state = ?, rate = ??";
    private static final String SQL_DELETE_TAX = "DELETE FROM taxes WHERE id = ?";
    private static final String SQL_GET_TAX = "SELECT * FROM taxes WHERE id = ?";
    private static final String SQL_GET_TAX_LIST = "SELECT * FROM taxes";

    private JdbcTemplate jdbcTemplate;

    public TaxesDaoDbImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Taxes create(Taxes taxes) {

        jdbcTemplate.update(SQL_INSERT_TAX,
                taxes.getState(),
                taxes.getTaxRate());

        Integer id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        taxes.setId(id);

        return taxes;
    }

    @Override
    public void update(Taxes taxes) {

        jdbcTemplate.update(SQL_UPDATE_TAX,
                taxes.getState(),
                taxes.getTaxRate(),
                taxes.getId());

    }

    @Override
    public void delete(Taxes taxes) {

        jdbcTemplate.update(SQL_DELETE_TAX,
                taxes.getId());

    }

    @Override
    public Taxes get(Integer id) {

        return jdbcTemplate.queryForObject(SQL_GET_TAX, new TaxesMapper(), id);

    }

    @Override
    public List<Taxes> getTaxesList() {

        return jdbcTemplate.query(SQL_GET_TAX_LIST, new TaxesDaoDbImpl.TaxesMapper());

    }

    @Override
    public double calculateTaxRate(String state) {
        double taxRate = 1;
        List<Taxes> taxList = getTaxesList();

        for (Taxes tax : taxList) {

            if (state.equalsIgnoreCase(tax.getState())) {

                taxRate *= tax.getTaxRate();

            }

        }

        return taxRate;
    }

    @Override
    public double calculateTaxTotal(double totalLabor, double totalProduct, double taxRate) {
        double taxTotal;

        double laborTax = totalLabor * (taxRate / 100);
        double productTax = totalProduct * (taxRate / 100);
        taxTotal = laborTax + productTax;

        return taxTotal;
    }

    @Override
    public void setCsv(boolean csvXml) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTestMode(boolean testMode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateState(String state, boolean edit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static final class TaxesMapper implements RowMapper<Taxes> {

        @Override
        public Taxes mapRow(ResultSet rs, int i) throws SQLException {

            Taxes taxes = new Taxes();

            taxes.setId(rs.getInt("id"));
            taxes.setState(rs.getString("state"));
            taxes.setTaxRate(rs.getDouble("rate"));

            return taxes;

        }

    }

}
