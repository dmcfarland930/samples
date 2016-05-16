/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Taxes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class TaxesDao {

    boolean isTest;
    FlooringData fd = new FlooringData();
    TaxesXmlDao tXml = new TaxesXmlDao();
    List<Taxes> taxesList = new ArrayList();
    String state;
    boolean testMode;
    boolean csv;

    public TaxesDao() {

        if (csv == true) {
            taxesList = fd.taxDecode();
        } else {
            taxesList = tXml.read();
        }

    }

    public Taxes create(Taxes taxes) {

        taxesList.add(taxes);

        if (!testMode) {
            fd.taxEncode(taxesList);
        }
        return taxes;

    }

    public Taxes get(String state) {

        for (Taxes myTaxes : taxesList) {
            if (myTaxes.getState().equals(state)) {
                return myTaxes;
            }
        }
        return null;
    }

    public void update(Taxes taxes) {

        for (Taxes myTaxes : taxesList) {
            if (myTaxes.getState().equals(taxes.getState())) {
                taxesList.remove(myTaxes);
                taxesList.add(taxes);
                break;

            }

        }
        if (!testMode) {
            fd.taxEncode(taxesList);
        }
    }

    public void delete(Taxes taxes) {

        Taxes found = null;

        for (Taxes myTaxes : taxesList) {

            if (myTaxes.getState().equals(taxes.getState())) {

                found = myTaxes;
                break;
            }
        }
        taxesList.remove(found);

        if (!testMode) {
            fd.taxEncode(taxesList);
        }

    }

    public double calculateTaxRate(String state) {

        double taxRate = 1;
        List<Taxes> taxList;
        if (csv == true) {
            taxList = fd.taxDecode();
        } else {
            taxList = tXml.read();
        }
        
        for (Taxes tax : taxList) {

            if (state.equalsIgnoreCase(tax.getState())) {

                taxRate *= tax.getTaxRate();

            }

        }

        return taxRate;

    }

    public double calculateTaxTotal(double totalLabor, double totalProduct, double taxRate) {

        double taxTotal;

        double laborTax = totalLabor * (taxRate / 100);
        double productTax = totalProduct * (taxRate / 100);
        taxTotal = laborTax + productTax;

        return taxTotal;
    }

    public boolean validateState(String state, boolean edit) {

        boolean valid = false;
        
        List<Taxes> taxList;
        if (csv == true) {
            taxList = fd.taxDecode();
        } else {
            taxList = tXml.read();
        }

        for (Taxes tax : taxList) {
            if (edit == true) {
                if (state.equalsIgnoreCase(tax.getState()) || state.equals("0") || state.isEmpty()) {
                    valid = true;
                }

            } else if (state.equalsIgnoreCase(tax.getState()) || state.equals("0")) {
                valid = true;
            }

        }
        return valid;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void setCsv(boolean csvXml) {
        this.csv = csvXml;
    }

}
