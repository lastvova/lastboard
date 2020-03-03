package servlets.filters;

import dao.UserDAO;
import enums.AccountStatus;
import enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String login = request.getParameter("userLogin");
        final String password = request.getParameter("userPassword");
        UserDAO userDAO = new UserDAO();
        final HttpSession session = request.getSession();

        if ((session != null) &&
                (session.getAttribute("userLogin")) != null &&
                (session.getAttribute("userPassword")) != null) {
            if (userDAO.findByLoginPassword((String) session.getAttribute("userLogin"),
                    (String) session.getAttribute("userPassword")).getAccountStatus().equals(AccountStatus.BLOCKED)) {
                request.getRequestDispatcher("/userblocked-page").forward(request, response);
            }
            final UserRole role = (UserRole) session.getAttribute("userRole");
            moveToMenu(request, response, role);
        } else if (userDAO.isRegistered(login, password)) {
            final UserRole role = userDAO.findRoleByLoginPassword(login, password);
            request.getSession().setAttribute("userLogin", login);
            request.getSession().setAttribute("userPassword", password);
            request.getSession().setAttribute("userRole", role);
            moveToMenu(request, response, role);
        } else {
            request.getRequestDispatcher("/login-page").forward(request, response);
        }
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final UserRole role) throws ServletException, IOException {
        if (role.equals(UserRole.ADMIN)) {
            req.getRequestDispatcher("/adminmenu").forward(req, res);
        } else if (role.equals(UserRole.MODERATOR)) {
            req.getRequestDispatcher("/moderatormenu").forward(req, res);
        } else {
            req.getRequestDispatcher("/myprofile").forward(req, res);
        }
    }
}
