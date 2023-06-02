package ru.javarush.syrovatko.qa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;
import ru.javarush.syrovatko.qa.entity.Supervisor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QA_RunnerTest2 {

    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    @BeforeAll
    static void setup() throws IOException {
        try {
            runSqlScriptFile("src/main/resources/schema.sql");
            runSqlScriptFile("src/main/resources/test_data.sql");
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Supervisor.class)
                    .buildSessionFactory();


        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

    static void runSqlScriptFile(String path) throws IOException {
        String sqlScript = new String(Files.readAllBytes(Paths.get(path)));
        SessionFactory scriptSessionFactory = new Configuration().buildSessionFactory();
        Session scriptSession = scriptSessionFactory.openSession();
        scriptSession.beginTransaction();

        NativeQuery nativeQuery = scriptSession.createNativeQuery(sqlScript);
        nativeQuery.executeUpdate();

        scriptSession.getTransaction().commit();

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
    static void tear() {
        sessionFactory.close();
    }

    @Test
    void testMethod() {
        Supervisor supervisor = new Supervisor(12L, "Иван Иванов", "Супервизор", 9000, LocalDate.now(), 4);
        session.persist(supervisor);

        Supervisor gettedSupervisor = session.get(Supervisor.class, 12L);

        Query<Supervisor> query = session.createQuery("select sup from Supervisor sup", Supervisor.class);
        List<Supervisor> list = query.list();
        for (Supervisor supervisor1 : list) {
            System.out.println(supervisor1);
        }

        assertEquals("Иван Иванов", gettedSupervisor.getName());

    }
}