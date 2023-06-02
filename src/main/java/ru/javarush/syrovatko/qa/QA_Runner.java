package ru.javarush.syrovatko.qa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.javarush.syrovatko.qa.entity.Manager;

public class QA_Runner {
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.setId(1L);
        manager.setName("Alex");

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Manager.class)
                .buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.persist(manager);

            Manager getted_entity = session.get(Manager.class, 1L);
            System.out.println(getted_entity);

            session.getTransaction().commit();

        }

    }
}
