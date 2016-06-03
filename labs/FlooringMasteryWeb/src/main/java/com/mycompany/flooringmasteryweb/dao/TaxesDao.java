/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxesDao {

    double calculateTaxRate(String state);

    double calculateTaxTotal(double totalLabor, double totalProduct, double taxRate);

    Taxes create(Taxes taxes);

    void delete(Taxes taxes);

    Taxes get(String state);

    void setCsv(boolean csvXml);

    void setTestMode(boolean testMode);

    void update(Taxes taxes, String state);

    boolean validateState(String state, boolean edit);
    
    List<Taxes> getTaxesList();

}
