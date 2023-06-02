package ru.javarush.syrovatko.qa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import ru.javarush.syrovatko.qa.entity.Supervisor;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QA_RunnerTest1 {

    private SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    void setup() {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Supervisor.class)
                    .buildSessionFactory();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @BeforeEach
    void setupThis() {
        session = sessionFactory.openSession();
        session.beginTransaction();

    }

    @AfterEach
    void tearThis() {
        session.getTransaction().commit();
    }

    @AfterAll
    void tear() {
        sessionFactory.close();
    }

    @Test
    void testMethod() {
        Supervisor supervisor = new Supervisor(12L, "Иван Иванов", "Супервизор", 9000, LocalDate.now(), 4);
        session.persist(supervisor);

        Supervisor gettedSupervisor = session.get(Supervisor.class, 12L);

        assertEquals("Иван Иванов", supervisor.getName());

    }
}