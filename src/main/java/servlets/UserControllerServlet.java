package servlets;

import dao.UserDAO;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = "/usercontroller")
public class UserControllerServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(UserControllerServlet.class);
    private final UserDAO userDAO = new UserDAO();

    private void update(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            User user = userDAO.findById(Long.parseLong(request.getParameter("id")));
            user.setUserRole(request.getParameter("userRole"));
            user.setAccountStatus(request.getParameter("userAccountStatus"));
            userDAO.updateUser(user);
        }
    }

    private void add(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("userLogin"));
        user.setPassword(request.getParameter("userPassword"));
        user.setEmail(request.getParameter("userEmail"));
        user.setName(request.getParameter("userName"));
        user.setUserRole(request.getParameter("userRole"));
        user.setAccountStatus(request.getParameter("userAccountStatus"));
        userDAO.create(user);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            add(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile-page");
            } catch (IOException e) {
                logger.error(e);
            }
        } else {
            update(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile-page");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User user = userDAO.findById(id);
        if (user != null) {
            request.setAttribute("id", user.getId());
            try {
                getServletContext().getRequestDispatcher("/edituser").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        } else try {
            request.getRequestDispatcher("myprofile").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
