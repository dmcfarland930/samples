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
@WebServlet(name = "Speed", urlPatterns = {"/Speed"})
public class Speed extends HttpServlet {

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

        RequestDispatcher rd = request.getRequestDispatcher("speed.jsp");
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

        double speed = 0;
        String mySpeed;
        String unit = "";

        try {
            mySpeed = request.getParameter("mySpeed");
            unit = request.getParameter("unit");
            speed = Double.parseDouble(mySpeed);
            
        } catch (Exception ex) {

            String error = "Please enter a valid speed to convert!";
            request.setAttribute("error", error);
            RequestDispatcher rdError = request.getRequestDispatcher("speed.jsp");
            rdError.forward(request, response);

        }
        if (unit.equalsIgnoreCase("mph")) {

            double kph = speed * 1.609344;
            mySpeed = df.format(speed) + " Mph = " + df.format(kph) + " Kph";

        } else {

            double mph = speed / 1.609344;
            mySpeed = df.format(speed) + " Kph = " + df.format(mph) + " Mph";

        }

        request.setAttribute("speedConvert", mySpeed);

        RequestDispatcher rd = request.getRequestDispatcher("speed.jsp");
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
