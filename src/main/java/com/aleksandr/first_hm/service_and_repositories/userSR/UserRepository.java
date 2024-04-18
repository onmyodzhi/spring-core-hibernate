package com.aleksandr.first_hm.service_and_repositories.userSR;

import com.aleksandr.first_hm.data.User;
import com.aleksandr.first_hm.service_and_repositories.DBConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private SessionFactory sessionFactory;
    private Session session;

    void addUser(User user) {
        String name = user.getName();
        int age = user.getAge();
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.persist(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    User readUserById(Long id) {
        User user = null;
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            user = session.get(User.class, id);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    void updateUserById(Long id, String name, int age) {
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = session.get(User.class, id);

            user.setName(name);
            user.setAge(age);

            session.merge(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    void deleteUserById(Long id) {
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            User user = session.get(User.class, id);

            session.remove(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            users = session.createQuery("FROM User", User.class).getResultList();

            session.getTransaction().commit();

            return users;
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return users;
    }

    @PreDestroy
    public void preDestroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        if (session != null && session.getTransaction() != null) {
            session.getTransaction().rollback();
        }
    }
}
