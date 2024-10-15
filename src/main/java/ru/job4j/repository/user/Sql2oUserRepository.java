package ru.job4j.repository.user;

import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.Main;
import ru.job4j.model.User;
import ru.job4j.repository.crud.CrudRepository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class Sql2oUserRepository implements UserRepository {

    private CrudRepository crudRepository;

    static final Logger LOG = LoggerFactory.getLogger(Sql2oUserRepository.class.getName());

    @Override
    public Optional<User> save(User user) {
        try {
            crudRepository.run(session -> session.save(user));
            return Optional.of(user);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try {
           return crudRepository.optional("FROM User as i WHERE i.email = :fEmail AND i.password = :fPassword",
                    User.class, Map.of("fEmail", email, "fPassword", password));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return Optional.empty();
    }
}
