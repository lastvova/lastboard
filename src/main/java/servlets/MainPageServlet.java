package servlets;

import dao.AdvertisementDAO;
import entity.Advertisement;
import enums.AdvertisementStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "MainPageServlet", urlPatterns = "/main-page")
public class MainPageServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(MainPageServlet.class);
    private final AdvertisementDAO advertisementDAO = new AdvertisementDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Advertisement> list = advertisementDAO.findAll().stream()
                .filter(advertisement -> advertisement.getAdvertisementStatus().equals(AdvertisementStatus.APPROVED))
                .limit(4)
                .collect(Collectors.toList());
        request.setAttribute("advertisementList", list);
        try {
            request.getRequestDispatcher("/").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }

    }
}
