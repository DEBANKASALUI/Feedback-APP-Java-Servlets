package feedbackapp.session.manage;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

//@WebServlet("/servlet1")
public class Request1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = "Debu";
        String userId = "2";

//        Cookie
        Cookie cookie = new Cookie("username", userName);
        Cookie cookie1 = new Cookie("userid", userId);
        cookie.setMaxAge(10 * 60);
        cookie1.setMaxAge(5 * 60);
        resp.addCookie(cookie);
        resp.addCookie(cookie1);

//        HttpSession
        HttpSession session = req.getSession();
        session.setAttribute("userSecret", UUID.randomUUID().toString());
        session.setMaxInactiveInterval(10 * 60);

//        Servlet-Context & Servlet-Config
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = servletConfig.getServletContext();
        String appName = servletContext.getInitParameter("app_name");
        String username = servletConfig.getInitParameter("username");

//        Can't able to access different servlet-config Init-param(will give null)
//        String username = servletConfig.getInitParameter("useremail");

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h2>Cookie Set successfully</h2>");
        resp.getWriter().println("""
                <h2>Context: %s</h2>
                <h2>Init Params: %s</h2>
                """.formatted(appName, username));
    }
}
