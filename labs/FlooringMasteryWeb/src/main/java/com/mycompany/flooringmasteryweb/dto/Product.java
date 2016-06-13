/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dto;

import com.mycompany.flooringmasteryweb.validation.ValidProductName;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Product {

    private String id;
    @NotEmpty(message = "You cannot leave this field blank!")
    @ValidProductName
    private String productType;
    @NotNull(message="Please enter a cost of materials!")
    private Double costPerSqFt;
    @NotNull(message="Please enter a cost of labor!")
    private Double laborCostPerSqFt;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Double getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(Double costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public Double getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(Double laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
