package servlets;

import dao.AdvertisementDAO;
import dao.UserDAO;
import entity.Advertisement;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CreateAdvertisementServlet")
public class CreateAdvertisementServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateAdvertisementServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("userLogin");
        String password = (String) session.getAttribute("userPassword");
        Advertisement advertisement = new Advertisement();
        advertisement.setTag(request.getParameter("adTag"));
        advertisement.setSubject(request.getParameter("adSubject"));
        advertisement.setBody(request.getParameter("adBody"));
        advertisement.setUser(new UserDAO().findByLoginPassword(login, password));
        new AdvertisementDAO().create(advertisement);
        try {
            response.sendRedirect(request.getContextPath() + "/my-profile");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }
}
