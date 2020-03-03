package servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogOut", urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(LogOutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        try {
            request.getRequestDispatcher("/").include(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
