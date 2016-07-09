/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */
package com.mycompany.unitconverter;

import java.io.IOException;
import java.text.DecimalFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "DollarsServlet", urlPatterns = {"/DollarsServlet"})
public class DollarsServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("weight.jsp");
        rd.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DecimalFormat df = new DecimalFormat("#.00");

        double dollars = 0;
        String myMoney = "";
        String unit = "";

        try {
            myMoney = request.getParameter("myMoney");
            unit = request.getParameter("unit");
            dollars = Double.parseDouble(myMoney);

        } catch (Exception ex) {

            String error = "Please enter a valid dollar amount to convert!";
            request.setAttribute("error", error);
            RequestDispatcher rdError = request.getRequestDispatcher("dollars.jsp");
            rdError.forward(request, response);

        }
        if (unit.equalsIgnoreCase("euros")) {

            double euros = dollars * 0.90;
            myMoney = "$" + df.format(dollars) + " = \u20AC" + df.format(euros);

        } else if (unit.equalsIgnoreCase("pesos")) {

            double pesos = dollars * 18.49;
            myMoney = "$" + df.format(dollars) + " = \u20B1" + df.format(pesos);

        } else if (unit.equalsIgnoreCase("pounds")) {

            double pounds = dollars * 0.68;
            myMoney = "$" + df.format(dollars) + " = \u00A3" + df.format(pounds);

        } else if (unit.equalsIgnoreCase("yen")) {

            double yen = dollars * 111.00;
            myMoney = "$" + df.format(dollars) + " = \u00A5" + df.format(yen);

        }

        request.setAttribute("dollarConvert", myMoney);


        RequestDispatcher rd = request.getRequestDispatcher("dollars.jsp");
        rd.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
