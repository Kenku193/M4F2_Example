package ru.javarush.syrovatko.repository.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.javarush.syrovatko.entity.User;
import ru.javarush.syrovatko.repository.Repository;

import java.util.List;
import java.util.stream.Stream;

public class UserRepository implements Repository<User> {

    SessionFactory sessionFactory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    @Override
    public Stream<User> getAll() {
        Session session = sessionFactory.openSession();
        try {
            Query<User> from_users = session.createQuery("from users", User.class);
            List<User> userList = from_users.list();
            session.getTransaction().commit();
            return userList.stream();

        }
        catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        }

    }

    @Override
    public Stream<User> find(User entity) {
        return null;
    }

    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }



}
