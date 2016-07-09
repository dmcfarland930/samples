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
@WebServlet(name = "WeightServlet", urlPatterns = {"/WeightServlet"})
public class WeightServlet extends HttpServlet {

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

        DecimalFormat df = new DecimalFormat("#.##");

        double weight = 0;
        String myWeight;
        String unit = "";

        try {
            myWeight = request.getParameter("myWeight");
            unit = request.getParameter("unit");
            weight = Double.parseDouble(myWeight);
            
        } catch (Exception ex) {

            String error = "Please enter a valid weight to convert!";
            request.setAttribute("error", error);
            RequestDispatcher rdError = request.getRequestDispatcher("weight.jsp");
            rdError.forward(request, response);

        }
        if (unit.equalsIgnoreCase("pounds")) {

            double weightKgs = weight * 0.45359237 ;
            myWeight = df.format(weight) + " Lbs = " + df.format(weightKgs) + " Kgs";

        } else {

            double weightLbs = weight * 2.2;
            myWeight = df.format(weight) + " Kgs = " + df.format(weightLbs) + " Lbs";

        }

        request.setAttribute("weightConvert", myWeight);

        RequestDispatcher rd = request.getRequestDispatcher("weight.jsp");
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
