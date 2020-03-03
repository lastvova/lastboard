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
import java.time.LocalDateTime;

@WebServlet(name = "AdvertisementController", urlPatterns = "/advertisementcontroller")
public class AdvertisementControllerServlet extends HttpServlet {
    private final AdvertisementDAO advertisementDAO = new AdvertisementDAO();
    private final Logger logger = Logger.getLogger(AdvertisementControllerServlet.class);

    private void add(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Advertisement advertisement = new Advertisement();
        advertisement.setTag(request.getParameter("advertisementTag"));
        advertisement.setSubject(request.getParameter("advertisementSubject"));
        advertisement.setBody(request.getParameter("advertisementBody"));
        advertisement.setUser(new UserDAO().findByLoginPassword((String) session.getAttribute("userLogin"),
                (String) session.getAttribute("userPassword")));
        advertisementDAO.create(advertisement);
    }

    private void update(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            Advertisement advertisement = advertisementDAO.findById(Long.parseLong(request.getParameter("id")));
            advertisement.setTag(request.getParameter("advertisementTag"));
            advertisement.setSubject(request.getParameter("advertisementSubject"));
            advertisement.setBody(request.getParameter("advertisementBody"));
            advertisement.setAdvertisementStatus("UNAPPROVED");
            advertisement.setDate(LocalDateTime.parse(request.getParameter("advertisementDate")));
            advertisementDAO.update(advertisement);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            add(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile");
            } catch (IOException e) {
                logger.error(e);
            }
        } else {
            update(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Advertisement advertisement = advertisementDAO.findById(id);
        if (advertisement != null) {
            request.setAttribute("id", advertisement.getId());
            try {
                getServletContext().getRequestDispatcher("/editadvertisement").forward(request, response);
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
