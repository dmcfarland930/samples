/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.addressbookweb.validation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class ValidationErrorContainer {
    
    private List<ValidationError> errors = new ArrayList();

    public List<ValidationError> getErrors() {
        return errors;
    }
    
    
    
    
    public void addErrors(ValidationError error){
        errors.add(error);
        
    }
    
    
}
