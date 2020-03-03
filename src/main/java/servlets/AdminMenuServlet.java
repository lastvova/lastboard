package servlets;

import dao.AdvertisementDAO;
import dao.UserDAO;
import entity.Advertisement;
import entity.User;
import enums.UserRole;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "AdminMenu", urlPatterns = "/adminmenu")
public class AdminMenuServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(AdminMenuServlet.class);
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("advertisementTag") != null &&
                request.getParameter("advertisementSubject") != null &&
                request.getParameter("advertisementBody") != null) {
            Advertisement advertisement = new Advertisement();
            advertisement.setTag(request.getParameter("advertisementTag"));
            advertisement.setSubject(request.getParameter("advertisementSubject"));
            advertisement.setBody(request.getParameter("advertisementBody"));
            advertisement.setAdvertisementStatus("APPROVED");
            advertisement.setUser(userDAO.findByLoginPassword((String) session.getAttribute("userLogin"),
                    (String) session.getAttribute("userPassword")));
            new AdvertisementDAO().create(advertisement);
        }
        try {
            response.sendRedirect(request.getContextPath() + "/myprofile");
        } catch (IOException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<User> userList = userDAO.findAll().stream()
                .filter(user -> user.getUserRole().equals(UserRole.USER) || user.getUserRole().equals(UserRole.MODERATOR))
                .sorted(Comparator.comparingLong(User::getId))
                .collect(Collectors.toList());
        request.setAttribute("userList", userList);
        User user = userDAO.findByLoginPassword((String) session.getAttribute("userLogin"), (String) session.getAttribute("userPassword"));
        List<Advertisement> advertisementList = user.getAdvertisementList();
        request.setAttribute("advertisementsList", advertisementList);
        try {
            request.getRequestDispatcher("/adminmenu-page").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }
}
