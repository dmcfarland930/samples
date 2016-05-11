/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.dao;

import com.mycompany.data.FlooringData;
import com.mycompany.dto.Product;
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
    List<Taxes> taxesList = new ArrayList();
    String state;

    public TaxesDao() {

        taxesList = fd.taxDecode();

    }

    public Taxes create(Taxes taxes) {

        taxes.setState(state);
        taxesList.add(taxes);

        fd.taxEncode();

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

            }

        }
        fd.taxEncode();

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

        fd.taxEncode();

    }

    public double calculateTaxRate(String state) {

        double taxRate = 1;
        List<Taxes> taxList = fd.taxDecode();

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
        List<Taxes> taxList = fd.taxDecode();

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
}
