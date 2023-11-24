package app.telegramgptbot.adminpanel.dao;

import java.util.Optional;

import app.telegramgptbot.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> clazz;

    public AbstractDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    public T add(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + clazz.getSimpleName() + " " + t
                    + " Reason: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + clazz.getSimpleName() + ", id: " + id
                    + " Reason: " + e.getMessage());
        }
    }

    public T update(T t) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update "
                    + clazz.getSimpleName() + " " + t
                    + " Reason: " + e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
