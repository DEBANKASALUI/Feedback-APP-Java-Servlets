package feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/servletDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//      GET form data
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String message = req.getParameter("message");

//        Database Logic Added
        FeedbackDao dao=new FeedbackDao();
        try {
            dao.insertFeedback(email, phone, message);
//            dao.insertFeedbackViaHibernate(email, phone, message);
//            dao.insertFeedbackViaJPAHibernate(email, phone, message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//      form data response
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Feedback submitted!!</h1>");
        writer.println("<h2>Your Data Submitted.. </h2>");
        writer.println("<h3>Email:" + email + "</h3>");
        writer.println("<h3>Phone:" + phone + "</h3>");
        writer.println("<h3>Message:" + message + "</h3>");
//        resp.sendRedirect("/home");
    }
}
