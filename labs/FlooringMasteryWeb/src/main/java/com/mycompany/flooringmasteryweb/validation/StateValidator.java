/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.validation;


import com.mycompany.flooringmasteryweb.dao.TaxesDao;
import com.mycompany.flooringmasteryweb.dto.Taxes;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author apprentice
 */
public class StateValidator implements ConstraintValidator<ValidState, String> {

    @Autowired
    private TaxesDao taxDao;

    private ValidState validState;
    
    @Override
    public void initialize(ValidState validState) {
        this.validState = validState;
    }

    @Override
    public boolean isValid(String state, ConstraintValidatorContext context) {

        List<Taxes> taxes = taxDao.getTaxesList();
        boolean productDoesNotExist = true;
        
        for (Taxes taxesOnList : taxes) {

            if (taxesOnList.getState().equalsIgnoreCase(state)) {
                productDoesNotExist = false;
            }

        }

        return productDoesNotExist;
    }
    
    public void setTaxDao(TaxesDao taxDao){
        
        this.taxDao = taxDao;
    }

    
}
