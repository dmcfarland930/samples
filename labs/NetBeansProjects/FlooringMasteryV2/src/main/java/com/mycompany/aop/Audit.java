/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.aop;

import com.mycompany.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author apprentice
 */
public class Audit {

    boolean fileDir = new File("File" + File.separator + "Audits" + File.separator).mkdirs();
    File newFile;
    private String auditActivity;
    private Date date = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
    private String dateString;
    private List<String> logs = new ArrayList();

    public Audit() {

        try {

            newFile = new File("File" + File.separator + "Audits" + File.separator + "Audits_" + sdf.format(this.date) + ".txt");

            newFile.createNewFile();

            try {
                if (!newFile.exists()) {
                    PrintWriter out = new PrintWriter(new FileWriter("File" + File.separator + "Audits" + File.separator + "Audit_" + sdf.format(this.date) + ".txt"));
                    out.print("Action, OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total, Date of Action");
                    out.flush();
                    out.close();
                }
            } catch (IOException ex) {

                Logger.getLogger(Audit.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException ex) {

            Logger.getLogger(Audit.class.getName()).log(Level.SEVERE, null, ex);

        }
        decode();
    }

        
    public void logUpdate(Object retVal) {

        
        Order order = (Order) retVal;
        Calendar c = Calendar.getInstance();
        String str = "Update (Original Order)" + "," + order.getOrderNumber() + "," + order.getCustomerName() + "," + order.getState() + "," + order.getTaxRate()
                + "," + order.getProductType() + "," + order.getArea() + "," + order.getCostPerSqFt() + "," + order.getLaborCostPerSqFt() + ","
                + order.getMaterialCost() + "," + order.getTotalLaborCost() + "," + order.getTax() + "," + order.getOrderTotal() + "," + c.getTime();
        this.logs.add(str);
        encode(this.logs);

    }
    
    public void logSave(JoinPoint joinPoint) {

        Object[] orders = joinPoint.getArgs();
        Order order = (Order) orders[0];
        String name = joinPoint.getSignature().getName();
        String lower = name.toLowerCase();
        String capName = lower.substring(0, 1).toUpperCase() + lower.substring(1);
        Calendar c = Calendar.getInstance();
        String str = capName + "," + order.getOrderNumber() + "," + order.getCustomerName() + "," + order.getState() + "," + order.getTaxRate()
                + "," + order.getProductType() + "," + order.getArea() + "," + order.getCostPerSqFt() + "," + order.getLaborCostPerSqFt() + ","
                + order.getMaterialCost() + "," + order.getTotalLaborCost() + "," + order.getTax() + "," + order.getOrderTotal() + "," + c.getTime();
        this.logs.add(str);
        encode(this.logs);

    }

    public void encode(List<String> logs) {

        PrintWriter out = null;

        try {

            out = new PrintWriter(new FileWriter("File" + File.separator + "Audits" + File.separator + "Audits_" + sdf.format(this.date) + ".txt"));
            out.print("Action, OrderNumber, CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total, Date of Action");
            out.println();

            for (String auditString : logs) {
                out.print(auditString);
                out.println();
            }
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Audit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }

    }

    public List decode() {

        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader("File" + File.separator + "Audits" + File.separator + "Audits_" + sdf.format(this.date) + ".txt")));

            int lineNumber = 0;

            while (sc.hasNext()) {

                lineNumber++;

                if (lineNumber == 1) {
                    sc.nextLine();
                    continue;
                }

                String currentLine = sc.nextLine();
                this.logs.add(currentLine);

//                if (stringParts.length > 12) {
//                    customerName = stringParts[1] + stringParts[2];
//                    customerName = customerName.replace("\\", ",");
//                    myOrder.setCustomerName(customerName);
//                    i = 3;
//                } else {
//                    myOrder.setCustomerName(stringParts[1]);
//                    myOrder.setCustomerName(removeQuotes(myOrder));
//                    i = 2;
//                }
            }

        } catch (FileNotFoundException | NumberFormatException ex) {

        } 
        return this.logs;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getAuditActivity() {
        return auditActivity;
    }

    public void setAuditActivity(String auditActivity) {
        this.auditActivity = auditActivity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
