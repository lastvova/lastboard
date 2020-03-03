package servlets;

import dao.AdvertisementDAO;
import entity.Advertisement;
import enums.AdvertisementStatus;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet(name = "AdvertisementApproveOrRejectServlet", urlPatterns = "/approve-or-reject")
public class AdvertisementApproveOrRejectServlet extends HttpServlet {
    private final AdvertisementDAO advertisementDAO = new AdvertisementDAO();
    private final Logger logger = Logger.getLogger(AdvertisementApproveOrRejectServlet.class);

    private void reject(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            Advertisement advertisement = advertisementDAO.findById(id);
            advertisement.setAdvertisementStatus(AdvertisementStatus.REJECTED.toString());
            advertisementDAO.update(advertisement);
        }
    }

    private void approve(HttpServletRequest request) {
        if (request.getParameter("id") != null) {
            long id = Long.parseLong(request.getParameter("id"));
            Advertisement advertisement = advertisementDAO.findById(id);
            advertisement.setAdvertisementStatus(AdvertisementStatus.APPROVED.toString());
            advertisementDAO.update(advertisement);
            sendEmailAboutApprove(advertisement);
        }
    }

    private void sendEmailAboutApprove(Advertisement advertisement) {
        Properties properties = new Properties();
        String userEmail = advertisement.getUser().getEmail();

        try (InputStream inputStream = SendVerificationEmailServlet.class.getClassLoader().getResourceAsStream("email.properties")) {
            if (inputStream == null) {
                logger.error("Wrong path to property");
                return;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e);
        }
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lastvova6@gmail.com", "softserve1");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lastvova6@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("About your advertisement");
            message.setText("Your advertisement was APPROVED");
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("approve").equals("true")) {
            approve(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile");
            } catch (IOException e) {
                logger.error(e);
            }
        } else if (request.getParameter("approve").equals("false")) {
            reject(request);
            try {
                response.sendRedirect(request.getContextPath() + "/myprofile");
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}
