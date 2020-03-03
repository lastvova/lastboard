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

@WebServlet(name = "AllAdvertisementsServlet", urlPatterns = "/alladvertisements")
public class AllAdvertisementsServlet extends HttpServlet {
    Logger logger = Logger.getLogger(AllAdvertisementsServlet.class);
    private final AdvertisementDAO advertisementDAO = new AdvertisementDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Advertisement> allAdvertisements = advertisementDAO.findAll()
                .stream()
                .filter(advertisement -> advertisement.getAdvertisementStatus().equals(AdvertisementStatus.APPROVED))
                .collect(Collectors.toList());
        request.setAttribute("allAdvertisements", allAdvertisements);
        try {
            request.getRequestDispatcher("/alladvertisements-page").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
