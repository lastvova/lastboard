package servlets;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Registration", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO userDAO = new UserDAO();
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        String email = request.getParameter("userEmail");
        String name = request.getParameter("userName");

        if (!userDAO.isRegistered(login, password)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            userDAO.create(user);
            try {
                request.getRequestDispatcher("/sendemail").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        } else {
            try {
                request.getRequestDispatcher("/registration-page").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userLogin") != null) {
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile");
            } catch (IOException e) {
                logger.error(e);
            }
        } else try {
            request.getRequestDispatcher("/registration-page").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
