package com.aleksandr.first_hm.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrepareData {
    public static void forcePrepareData() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            Path path = Paths.get("data.sql");
            String sql = new String(Files.readAllBytes(path));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}

