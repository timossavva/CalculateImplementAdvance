package products;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class ProductList {
    public static SessionFactory sessionFactory;

    public ProductList(SessionFactory sessionFactory) {
        ProductList.sessionFactory = sessionFactory;
    }


    public void create(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }

    public Product getWithId(long userId) {
        ArrayList<Product> allStores = getAll();
        for (Product product : allStores) {
            if (product.getId() == userId) {
                return product;
            }
        }
        return null;
    }


    public ArrayList<Product> getAll() {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Product> result = (ArrayList<Product>) session.createQuery("from Product ").list();
        session.getTransaction().commit();
        session.close();
        /*adfadf*/
        return result;
    }

}