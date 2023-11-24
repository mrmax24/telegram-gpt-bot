package app.telegramgptbot.adminpanel.dao.impl;

import app.telegramgptbot.adminpanel.dao.AbstractDao;
import app.telegramgptbot.adminpanel.dao.UserDao;
import app.telegramgptbot.exception.DataProcessingException;
import app.telegramgptbot.adminpanel.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = factory.openSession()) {
            Query<User> query = session.createQuery("FROM User u " +
                    "WHERE u.email = :email ", User.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("User with email "
                    + email + " not found. Reason: " + e.getMessage());
        }
    }
}
