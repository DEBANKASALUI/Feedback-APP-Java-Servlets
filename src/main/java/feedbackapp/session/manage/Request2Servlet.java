package feedbackapp.session.manage;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.lang.model.element.ModuleElement;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/servlet2")
public class Request2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        var writer = resp.getWriter();
        var flag = false;
        Cookie[] cookies = req.getCookies();
        StringBuilder content = new StringBuilder();
        if (cookies != null) {
            for (Cookie c : cookies) {
                String name = c.getName();
                if (name.equals("username")) {
                    System.out.println("UserName: " + c.getValue());
                    content.append("""
                            <h1>Welcome, %s</h1>
                            """.formatted(c.getValue()));
                    flag = true;
                } else if (name.equals("userid")) {
                    System.out.println("UserID: " + c.getValue());
                    content.append("""
                            <h1>UserId: %s</h1>
                            """.formatted(c.getValue()));
                    flag = true;
                }
            }
        }

//        Servlet-Context & Servlet-Config
        ServletConfig servletConfig = getServletConfig();
        ServletContext servletContext = servletConfig.getServletContext();
        String appName = servletContext.getInitParameter("app_name");
        String useremail = servletConfig.getInitParameter("useremail");

//      HttpSession
        HttpSession session = req.getSession();
        String secret = (String) session.getAttribute("userSecret");
        content.append("""
                <h2>Session secret: %s</h2>
                """.formatted(secret));

        if (flag) {
            writer.println("<h2>User Cookie found</h2>");
            writer.println(content);
        } else {
            writer.println("<h1>No User found in cookies</h1>");
            writer.println("<h2>User cookie not found in request</h2>");
            writer.println(content);
        }
        resp.getWriter().println("""
                <h2>Context: %s</h2>
                <h2>Init Params: %s</h2>
                """.formatted(appName, useremail));
    }
}
