package servlets;

import dao.UserDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDelete", urlPatterns = "/userdelete")
public class UserDeleteServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(UserDeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        new UserDAO().delete(id);
        try {
            response.sendRedirect(request.getContextPath() + "/myprofile");
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
