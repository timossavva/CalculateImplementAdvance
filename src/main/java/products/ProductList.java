package products;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.ArrayList;

public class ProductList {
    public static SessionFactory sessionFactory;
    public static Session session;
    private final ArrayList<Product> productList;
    private String accountType;

    public ProductList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.setUpSessionFactory();
        productList = getAllFromDb();
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void create(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public Product getWithId(long userId) {
        ArrayList<Product> allStores = getAllFromDb();
        for (Product product : allStores) {
            if (product.getId() == userId) {
                return product;
            }
        }
        return null;
    }


    public ArrayList<Product> getAllFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Product> result = (ArrayList<Product>) session.createQuery("from Product ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}