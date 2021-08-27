package com.kate.carthibernate.util;

import com.kate.carthibernate.domain.Cart;
import com.kate.carthibernate.domain.Customer;
import com.kate.carthibernate.domain.Product;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                Configuration configuration = new Configuration().configure();

                configuration.addAnnotatedClass(Cart.class);
                configuration.addAnnotatedClass(Customer.class);
                configuration.addAnnotatedClass(Product.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Session factory Error: " + e);
            }
        }
        return sessionFactory;
    }

}
