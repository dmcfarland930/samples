/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */


import java.io.IOException;
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
@WebServlet(name = "Converter", urlPatterns = {"/Converter"})
public class Converter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("converter.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String choice = request.getParameter("button");

        boolean temp = false;
        boolean weight = false;
        boolean speed = false;
        boolean dollars = false;

        if (choice.equalsIgnoreCase("temperature")) {

            temp = true;

        } else if (choice.equalsIgnoreCase("weight")) {

            weight = true;

        } else if (choice.equalsIgnoreCase("dollars")) {

            dollars = true;

        } else if (choice.equalsIgnoreCase("speed")) {

            speed = true;
        }

        if (temp) {

            RequestDispatcher rd = request.getRequestDispatcher("temp.jsp");
            rd.forward(request, response);

        } else if (weight) {
            RequestDispatcher rd = request.getRequestDispatcher("weight.jsp");
            rd.forward(request, response);

        } else if (speed) {
            RequestDispatcher rd = request.getRequestDispatcher("speed.jsp");
            rd.forward(request, response);

        } else if (dollars) {
            RequestDispatcher rd = request.getRequestDispatcher("dollars.jsp");
            rd.forward(request, response);

        }else{
            
            RequestDispatcher rd = request.getRequestDispatcher("converter.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
