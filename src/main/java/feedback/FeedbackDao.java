package feedback;

import jakarta.servlet.ServletException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedbackDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/servletDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void insertFeedback(String email, String phone, String message) throws SQLException, ClassNotFoundException, ServletException {
//        Database connectivity and save data
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO feedback (email, phone, message) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, email);
                    statement.setString(2, phone);
                    statement.setString(3, message);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database connection problem", e);
        }
    }

    public void insertFeedbackViaHibernate(String email, String phone, String message) throws ServletException {

        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setPhone(phone);
        feedback.setMessage(message);

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(feedback);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new ServletException("Database connection problem", e);
        }
    }

    public void insertFeedbackViaJPAHibernate(String email, String phone, String message) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Feedback feedback = new Feedback();
        feedback.setEmail(email);
        feedback.setPhone(phone);
        feedback.setMessage(message);

        entityManager.getTransaction().begin();
        entityManager.persist(feedback);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

