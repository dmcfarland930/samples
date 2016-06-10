/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.dto.GrowingInvestment;
import com.mycompany.interestcalculatorweb.InterestCalculator;
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
@WebServlet(urlPatterns = {"/InterestCalculatorServlet"})
public class InterestCalculatorServlet extends HttpServlet {

    InterestCalculator iC = new InterestCalculator();
    List<GrowingInvestment> investList = new ArrayList();

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

        double investment = 0;
        double rate = 0;
        double length = 0;
        String myInvestment = request.getParameter("myInvestment");
        String myRate = request.getParameter("myRate");
        String myLength = request.getParameter("myLength");
        String compound = request.getParameter("compound");

        try {

            investment = Double.parseDouble(myInvestment);
            rate = Double.parseDouble(myRate);
            length = Double.parseDouble(myLength);

        } catch (Exception ex) {
            String error = "You must fill each field!";
            request.setAttribute("error", error);
            RequestDispatcher rdHome = request.getRequestDispatcher("index.jsp");
            rdHome.forward(request, response);

        }

        iC.run(investment, rate, length, compound);

        investList = iC.getInvestList();
        request.setAttribute("investments", investList);

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