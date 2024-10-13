package ru.job4j.repository.task;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Task;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class Sql2oTaskStoreRepository implements TaskStoreRepository {

    private SessionFactory sessionFactory;

    @Override
    public Task save(Task task) {
        var session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public Optional<Task> findById(int id) {
        var session = sessionFactory.openSession();
        Optional<Task> rslTask = Optional.empty();
        try {
            session.beginTransaction();
            rslTask = session.createQuery("FROM Task as i WHERE i.id = :fId", Task.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rslTask;
    }

    @Override
    public Collection<Task> findByDone() {
        var session = sessionFactory.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("FROM Task as i WHERE i.done = true", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean changeDone(int id) {
        var session = sessionFactory.openSession();
        int rows = 0;
        try {
            session.beginTransaction();
            rows = session.createQuery("UPDATE Task SET done = true WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }
        return rows > 0;
    }

    @Override
    public Collection<Task> findByNewTask() {
        var session = sessionFactory.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("FROM Task as i WHERE i.done = false", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }

    @Override
    public boolean update(Task task) {
        var session = sessionFactory.openSession();
        int rsl = 0;
        try {
            session.beginTransaction();
            var sql = """ 
        UPDATE Task SET title = :fTitle, description = :fDescription, done = :fDone
        WHERE id = :fId
        """;
            rsl = session.createQuery(sql)
                    .setParameter("fTitle", task.getTitle())
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fDone", task.isDone())
                    .setParameter("fId", task.getId())
                    .executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();

        } finally {
            session.close();
        }
        return rsl > 0;
    }

    @Override
    public boolean deleteTask(int id) {
        var session = sessionFactory.openSession();
        var rsl = 0;
        try {
            session.beginTransaction();
             rsl = session.createQuery("DELETE Task as i WHERE i.id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl != 0;
    }

    @Override
    public Collection<Task> findAll() {
        var session = sessionFactory.openSession();
        List<Task> rsl = new ArrayList<>();
        try {
            session.beginTransaction();
            rsl = session.createQuery("FROM Task", Task.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return rsl;
    }
}
