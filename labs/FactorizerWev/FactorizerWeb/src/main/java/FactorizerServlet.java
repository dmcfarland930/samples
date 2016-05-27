/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.factorizerweb.Factorizer;
import java.io.IOException;
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
@WebServlet(urlPatterns = {"/FactorizerServlet"})
public class FactorizerServlet extends HttpServlet {

    Factorizer fZ = new Factorizer();
    StringBuilder listString = new StringBuilder();
    List<String> factors = new ArrayList();

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

        int number = 0;
        String myNum = request.getParameter("myNumber");

        try {

            number = Integer.parseInt(myNum);

        } catch (Exception ex) {

            String error = "Please enter a valid number. Your number must be greater than 0 to factorize!";
            request.setAttribute("error", error);
            RequestDispatcher rdHome = request.getRequestDispatcher("index.jsp");
            rdHome.forward(request, response);

        }

        fZ.run(number);

        factors = fZ.getResults();
        String message1 = "";
        String message2 = "";
        if (number > 1) {

            if (fZ.getPerfectCount() == number) {
                message1 = number + " is a perfect number.";
            } else if (fZ.getPerfectCount() != number) {
                message1 = number + " is not a perfect number.";
            }

            if (fZ.getPrimeCount() == 1) {
                message2 = number + " is a prime number.";
            } else if (fZ.getPrimeCount() != 1) {
                message2 = number + " is not a prime number.";
            }

            request.setAttribute("factors", factors);

        }

        request.setAttribute("message", message1);
        request.setAttribute("message2", message2);

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
