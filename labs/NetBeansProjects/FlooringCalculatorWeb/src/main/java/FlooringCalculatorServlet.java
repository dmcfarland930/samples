/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.flooringcalculatorweb.FlooringCalculator;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns = {"/FlooringCalculatorServlet"})
public class FlooringCalculatorServlet extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfMoney = new DecimalFormat("0.00");
    FlooringCalculator fC = new FlooringCalculator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        RequestDispatcher rd = request.getRequestDispatcher("flooringcalc.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double widthTile = 0;
        double lengthTile = 0;
        double costTile = 0;
        double widthRoom = 0;
        double lengthRoom = 0;
        String myWidthTile = request.getParameter("myWidthTile");
        String myLengthTile = request.getParameter("myLengthTile");
        String myCostTile = request.getParameter("myCostTile");
        String myWidthRoom = request.getParameter("myWidthRoom");
        String myLengthRoom = request.getParameter("myLengthRoom");

        try {

            widthTile = Double.parseDouble(myWidthTile);
            lengthTile = Double.parseDouble(myLengthTile);
            costTile = Double.parseDouble(myCostTile);
            widthRoom = Double.parseDouble(myWidthRoom);
            lengthRoom = Double.parseDouble(myLengthRoom);

        } catch (Exception ex) {

            String error = "You must fill each field!";
            request.setAttribute("error", error);
            RequestDispatcher rdHome = request.getRequestDispatcher("flooringcalc.jsp");
            rdHome.forward(request, response);

        }

        fC.run(widthTile, lengthTile, costTile, widthRoom, lengthRoom);

        String tileCost = dfMoney.format(fC.getTileTotal());
        String laborCost = dfMoney.format(fC.getCostLabor());
        String grandTotal = dfMoney.format(fC.getFinalTotal());
        request.setAttribute("areaMessage", fC.getArea());
        request.setAttribute("costPerTile", fC.getCostPerTile());
        request.setAttribute("timeMessage", fC.getTime());
        request.setAttribute("costOfTiles", tileCost);
        request.setAttribute("costOfLabor", laborCost);
        request.setAttribute("grandTotal", grandTotal);

        //remember to create logic for orders that take less than an hour
        //set order parameters
        RequestDispatcher rd = request.getRequestDispatcher("fcresponse.jsp");
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
