/*
 * This program was written by Daniel McFarland.
 * I hope you enjoy it!
 */

import com.mycompany.luckysevens.LuckySevens;
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
@WebServlet(urlPatterns = {"/LuckySevensServlet"})
public class LuckySevensServlet extends HttpServlet {

    DecimalFormat df = new DecimalFormat("#.##");
    DecimalFormat dfMoney = new DecimalFormat("0.00");
    LuckySevens ls = new LuckySevens();

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

        double bet = 0;
        String myBet = request.getParameter("myBet");

        try {
            bet = Double.parseDouble(myBet);
        } catch (Exception ex) {
            String error = "Please enter a valid bet. Your must place a bet higher than 0 to play!";
            request.setAttribute("error", error);
            RequestDispatcher rdHome = request.getRequestDispatcher("index.jsp");
            rdHome.forward(request, response);
        }

        ls.run(bet);

        String result1 = "";
        String result2 = "";
        String result3 = "";
        if (bet > 0) {

            if (ls.getBet() == ls.getHighDollar()) {
                result1 = "You never earned more than your inital bet of $" + dfMoney.format(ls.getBet());

                result2 = "That should teach you to never gamble.";
            } else {
                result1 = "After " + df.format(ls.getHighRoll()) + " rolls, you had $"
                        + dfMoney.format(ls.getHighDollar()) + ".";

                result2 = "You should have quit there, but this is Lucky Sevens!";
                result3 = "You'll never win! Thus, a metaphor for life...";

            }
        }
        request.setAttribute("message", result1);
        request.setAttribute("message2", result2);
        request.setAttribute("message3", result3);
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
