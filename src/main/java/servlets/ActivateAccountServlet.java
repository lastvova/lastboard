package servlets;

import dao.UserDAO;
import entity.User;
import enums.AccountStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActivateAccountServlet", urlPatterns = "/activateaccount")
public class ActivateAccountServlet extends HttpServlet {
    Logger logger = Logger.getLogger(ActivateAccountServlet.class);
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("userEmail") != null) {
            User user = userDAO.findByEmail(request.getParameter("userEmail"));
            user.setAccountStatus(AccountStatus.ACTIVE.toString());
            userDAO.updateUser(user);
            try {
                request.getRequestDispatcher("/").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }

        }
    }
}
