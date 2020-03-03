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

@WebServlet(name = "FindAdvertisement", urlPatterns = "/findadvertisement")
public class FindAdvertisementServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(FindAdvertisementServlet.class);
    private final AdvertisementDAO advertisementDAO = new AdvertisementDAO();

    private List<Advertisement> findByAuthor(HttpServletRequest request) {
        return advertisementDAO.findByAuthor(request.getParameter("findRequest")).stream()
                .filter(advertisement -> advertisement.getAdvertisementStatus().equals(AdvertisementStatus.APPROVED))
                .collect(Collectors.toList());
    }

    private List<Advertisement> findByTag(HttpServletRequest request) {
        return advertisementDAO.findByTag(request.getParameter("findRequest")).stream()
                .filter(advertisement -> advertisement.getAdvertisementStatus().equals(AdvertisementStatus.APPROVED))
                .collect(Collectors.toList());
    }

    private List<Advertisement> findBySubject(HttpServletRequest request) {
        return advertisementDAO.findBySubject(request.getParameter("findRequest")).stream()
                .filter(advertisement -> advertisement.getAdvertisementStatus().equals(AdvertisementStatus.APPROVED))
                .collect(Collectors.toList());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("findCategory").equals("byAuthor")) {
            List<Advertisement> advertisementList = findByAuthor(request);
            request.setAttribute("advertisementList", advertisementList);
            try {
                request.getRequestDispatcher("/findadvertisement-page").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        } else if (request.getParameter("findCategory").equals("byTag")) {
            List<Advertisement> advertisementList = findByTag(request);
            request.setAttribute("advertisementList", advertisementList);
            try {
                request.getRequestDispatcher("/findadvertisement-page").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        } else if (request.getParameter("findCategory").equals("bySubject")) {
            List<Advertisement> advertisementList = findBySubject(request);
            request.setAttribute("advertisementList", advertisementList);
            try {
                request.getRequestDispatcher("/findadvertisement-page").forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            request.getRequestDispatcher("/findadvertisement-page").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
