/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */


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
@WebServlet(name = "Temp", urlPatterns = {"/Temp"})
public class Temp extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("temp.jsp");
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

        double temp = 0;
        String myTemp;
        String unit = "";

        try {
            myTemp = request.getParameter("myTemp");
            unit = request.getParameter("unit");
            temp = Double.parseDouble(myTemp);
            
        } catch (Exception ex) {

            String error = "Please enter a valid temperature to convert!";
            request.setAttribute("error", error);
            RequestDispatcher rdError = request.getRequestDispatcher("temp.jsp");
            rdError.forward(request, response);

        }
        if (unit.equalsIgnoreCase("celcius")) {

            double tempF = (temp * 1.8) + 32;
            myTemp = df.format(temp) + " \u00b0C = " + df.format(tempF) + " \u00b0F";

        } else {

            double tempC = (temp - 32) * 0.55555;
            myTemp = df.format(temp) + " \u00b0F = " + df.format(tempC) + " \u00b0C";

        }

        request.setAttribute("tempConvert", myTemp);

        RequestDispatcher rd = request.getRequestDispatcher("temp.jsp");
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
