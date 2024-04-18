package com.aleksandr.first_hm.service_and_repositories;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class DBConnection {
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory(
                                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build()
                        );
            }catch (RuntimeException exception){
                exception.printStackTrace();
                throw new RuntimeException();
            }
        }
        return sessionFactory;
    }

    public static synchronized void shotDown(){
        if (sessionFactory != null){
            sessionFactory.close();
            sessionFactory = null;
        }
    }
}
