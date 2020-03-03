package servlets;

import dao.AdvertisementDAO;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdvertisementDeleteServlet", urlPatterns = "/advertisementdelete")
public class AdvertisementDeleteServlet extends HttpServlet {
    Logger logger = Logger.getLogger(AdvertisementDeleteServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        new AdvertisementDAO().delete(id);
        try {
            response.sendRedirect(request.getContextPath() + "/myprofile");
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
