package com.aleksandr.first_hm.service_and_repositories.productSR;


import com.aleksandr.first_hm.data.Product;
import com.aleksandr.first_hm.service_and_repositories.DBConnection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private SessionFactory sessionFactory;
    private Session session;

    void addProduct(Product product) {
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.persist(product);

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

    Product readProductById(Long id) {
        Product product = null;
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            product = session.get(Product.class, id);

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

        return product;
    }

    void updateProductById(Long id, String title, Integer price) {
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Product product = session.get(Product.class, id);

            product.setTitle(title);
            product.setPrice(price);

            session.merge(product);

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

    void deleteProductById(Long id) {
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Product product = session.get(Product.class, id);

            session.remove(product);

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


    List<Product> readAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            if (sessionFactory == null) sessionFactory = DBConnection.getSessionFactory();

            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            products = session.createQuery("FROM Product ", Product.class).getResultList();

            session.getTransaction().commit();

            return products;
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

        return products;
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
