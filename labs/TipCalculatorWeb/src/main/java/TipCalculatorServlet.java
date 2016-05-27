/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.tipcalculatorweb.TipCalculator;
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
@WebServlet(urlPatterns = {"/TipCalculatorServlet"})
public class TipCalculatorServlet extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfMoney = new DecimalFormat("0.00");
    TipCalculator tC = new TipCalculator();

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

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double amountEntry;
        double tipEntry;
        double tipGet;
        double totalGet;
        String myAmount = request.getParameter("myAmount");
        String myTip = request.getParameter("myTip");
        String tip;
        String total;
        try {

            amountEntry = Double.parseDouble(myAmount);
            tipEntry = Double.parseDouble(myTip);
        } catch (Exception ex) {

            amountEntry = 0;
            tipEntry = 0;
        }

        tC.run(amountEntry, tipEntry);
        
        if (amountEntry == 0) {

            String error = "You must fill in each field!";
            request.setAttribute("message", error);

        } else {

            myAmount = dfMoney.format(amountEntry);
            myTip = df.format(tipEntry);
            tipGet = tC.getTip();
            totalGet = tC.getTotal();
            tip = dfMoney.format(tipGet);
            total = dfMoney.format(totalGet);
            request.setAttribute("myAmount", myAmount);
            request.setAttribute("myTip", myTip);
            request.setAttribute("tip", tip);
            request.setAttribute("total", total);
        }

        //remember to create logic for orders that take less than an hour
        //set order parameters
        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
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
