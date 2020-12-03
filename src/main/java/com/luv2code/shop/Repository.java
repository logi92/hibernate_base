package com.luv2code.shop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    static SessionFactory factory = null;
    static Session session = null;
    static Query query = null;
    static List<Consumer> consumers;
    static List<Product> products;

    static {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Consumer.class)
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
    }

    public static void showMeAllProducts() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        query = session.createQuery("SELECT p from Product p");
        products = query.list();
        session.getTransaction().commit();
        System.out.println("\nСписок продуктов доступные для покупки : ");
        for (Product p : products) {
            System.out.println(p);
        }

    }

    public static void showMeAllConsumers() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        query = session.createQuery("SELECT c from Consumer c");
        consumers = query.list();
        session.getTransaction().commit();
        System.out.println("\nПокупатели которые сегодня были в магазине : ");
        for (Consumer c : consumers) {
            System.out.println(c);
        }
    }

    public static void toBuy(String consumerName, String productName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Consumer consumer = null;
        Product product = null;
        try {
            consumer = (Consumer) session.createQuery("select c from Consumer c where c.name = :name")
                    .setParameter("name", consumerName).getSingleResult();
            product = (Product) session.createQuery("select p from Product p where p.productName = :productName")
                    .setParameter("productName", productName).getSingleResult();
        } catch (NoResultException i) {
            System.out.println("Ошибка при вводе товара или покупателя!");
            session.getTransaction().rollback();
            return;
        }
        consumer.addProduct(product);
        session.saveOrUpdate(consumer);
        session.getTransaction().commit();
    }

    public static void showProductsByConsumer(String consumerName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Consumer consumer = null;
        try {
            consumer = (Consumer) session.createQuery("select c from Consumer c where c.name = :name")
                    .setParameter("name", consumerName).getSingleResult();
        } catch (NoResultException  i) {
            System.out.println("Нет покупателя с таким именем!");
            session.getTransaction().rollback();
            return;
        }
        products = consumer.getProducts();
        System.out.println("\nСписок продуктов которые приобрел покупатель " + consumerName + " :");
        for (Product p : products) {
            System.out.println(p);
        }
        session.getTransaction().commit();
    }

    public static void findConsumerByProductName(String productName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = null;
        try {
            product = (Product) session.createQuery("select p from Product p where p.productName = :productName")
                    .setParameter("productName", productName).getSingleResult();
        } catch (NoResultException  i) {
            System.out.println("Нет такого товара!");
            session.getTransaction().rollback();
            return;
        }
        consumers = product.getConsumers();
        System.out.println("\nПокупатели которые приобрели " + productName + " :");
        for (Consumer c : consumers) {
            System.out.println(c);
        }
        session.getTransaction().commit();
    }

    public static void removeConsumer(String consumerName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        int result = session.createQuery("DELETE from Consumer where name = :name").setParameter("name", consumerName).executeUpdate();
        session.getTransaction().commit();
        if (result == 1) {
            System.out.println("Покупатель с именем " + consumerName + " удален!");
        } else {
            System.out.println("Покупатель с именем " + consumerName + " не найден!");
        }
    }

    public static void removeProduct(String productName) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        int result = session.createQuery("DELETE from Product where productName = :name").setParameter("name", productName).executeUpdate();
        session.getTransaction().commit();
        if (result == 1) {
            System.out.println("Товар с наименованием " + productName + " удален!");
        } else {
            System.out.println("Товар с наименованием " + productName + " не найден!");
        }
    }

    public static void prepareBD(){
        session = factory.getCurrentSession();
        try {
            String sql = Files.lines(Paths.get("src//main//resources//shop_sqript.sql")).collect(Collectors.joining(" "));
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
