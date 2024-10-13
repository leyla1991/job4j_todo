package ru.job4j.repository.user;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.User;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class Sql2oUserRepository implements UserRepository {

    private SessionFactory sessionFactory;

    static final Logger LOG = LoggerFactory.getLogger(Sql2oUserRepository.class.getName());

    @Override
    public Optional<User> save(User user) {
        var session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.ofNullable(user);
        } catch (HibernateException e) {
            LOG.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        var session = sessionFactory.openSession();
        Optional<User> userRsl = Optional.empty();
        try {
            session.beginTransaction();
            userRsl = session
                    .createQuery("FROM User as i WHERE i.email = :fEmail AND i.password = :fPassword", User.class)
                    .setParameter("fEmail", email)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
          session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return userRsl;
    }
}
