/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.flooringmasteryweb.dto;

import java.util.Date;

/**
 *
 * @author apprentice
 */
public class Order {
    
    private int orderNumber;
    private String customerName;
    private String state;
    private double taxRate;
    private String productType;
    private double area;
    private double costPerSqFt;
    private double laborCostPerSqFt;
    private double materialCost;
    private double totalLaborCost;
    private double tax;
    private double orderTotal;
    private String orderDate;
    private Date date;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(double costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public double getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }

    public void setLaborCostPerSqFt(double laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }

    public double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(double materialCost) {
        this.materialCost = materialCost;
    }

    public double getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(double totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    
}
