/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.dto.Taxes;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface TaxesDaoInterface {

    double calculateTaxRate(String state);

    double calculateTaxTotal(double totalLabor, double totalProduct, double taxRate);

    Taxes create(Taxes taxes);

    void delete(Taxes taxes);

    Taxes get(String state);

    void setCsv(boolean csvXml);

    void setTestMode(boolean testMode);

    void update(Taxes taxes);

    boolean validateState(String state, boolean edit);
    
    List<Taxes> getTaxesList();

}
