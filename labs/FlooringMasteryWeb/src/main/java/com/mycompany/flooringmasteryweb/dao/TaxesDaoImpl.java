/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dao;


import com.mycompany.flooringmasteryweb.data.FlooringData;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class TaxesDaoImpl implements TaxesDao {

    boolean isTest;
    FlooringData fd;
    TaxesXmlDao tXml;
    List<Taxes> taxesList = new ArrayList();
    String state;
    boolean testMode;
    boolean csv;

    public TaxesDaoImpl(FlooringData fData, TaxesXmlDao tXmlDao) {

        this.fd = fData;
        this.tXml = tXmlDao;
        testMode = fd.isTestMode();
        csv = fd.isCsv();
        if (csv == true) {
            taxesList = fd.taxDecode();
        } else {
            taxesList = tXml.readTaxes();
        }

    }

    @Override
    public Taxes create(Taxes taxes) {

        taxesList.add(taxes);

        if (!testMode) {
            fd.taxEncode(taxesList);
        }
        return taxes;

    }

    @Override
    public Taxes get(String state) {

        for (Taxes myTaxes : taxesList) {
            if (myTaxes.getState().equals(state)) {
                return myTaxes;
            }
        }
        return null;
    }

    @Override
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

    @Override
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

    @Override
    public double calculateTaxRate(String state) {

        double taxRate = 1;
        List<Taxes> taxList;
        if (csv == true) {
            taxList = fd.taxDecode();
        } else {
            taxList = tXml.readTaxes();
        }

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
    public boolean validateState(String state, boolean edit) {

        boolean valid = false;

        List<Taxes> taxList;
        if (csv == true) {
            taxList = fd.taxDecode();
        } else {
            taxList = tXml.readTaxes();
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

    @Override
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    @Override
    public void setCsv(boolean csvXml) {
        this.csv = csvXml;
    }

    @Override
    public List<Taxes> getTaxesList() {
        return taxesList;
    }

}
