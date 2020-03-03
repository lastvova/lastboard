package servlets;

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

@WebServlet(name = "SendEmailServlet", urlPatterns = "/sendemail")
public class SendVerificationEmailServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(SendVerificationEmailServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties properties = new Properties();
        String userEmail = request.getParameter("userEmail");
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
            message.setSubject("Confirm your email");
            String url = "  <a href= http://localhost:8080/board/activateaccount?userEmail=" + userEmail + ">Activating link</a>";
            message.setText(url, "UTF-8", "html");
            Transport.send(message);
        } catch (MessagingException e) {
            logger.error(e);
        }
        try {
            request.getRequestDispatcher("/").forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
    }
}

