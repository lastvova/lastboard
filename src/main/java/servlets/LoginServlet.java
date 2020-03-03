package servlets;

import dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(LoginServlet.class);
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String login = request.getParameter("userLogin");
        String password = request.getParameter("userPassword");
        HttpSession session = request.getSession();

        if (userDAO.isRegistered(login, password)) {
            session.setAttribute("userLogin", login);
            session.setAttribute("userPassword", password);
            session.setAttribute("userRole", userDAO.findRoleByLoginPassword(login, password));
            try {
                request.getRequestDispatcher("/").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        } else {
            try {
                request.getRequestDispatcher("/login-page").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }
}
