package stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.ArrayList;

public class StoreList {
    public static SessionFactory sessionFactory;
    public static Session session;
    private final ArrayList<Store> storeList;

    public StoreList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.setUp();
        storeList = getAllFromDb();
        hibernateUtil.shutdown(sessionFactory);
    }

    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public void create(Store store) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(store);
        session.getTransaction().commit();
        session.close();
    }

    public Store getWithId(long userId) {
        ArrayList<Store> allStores = getAllFromDb();
        for (Store store : allStores) {
            if (store.getId() == userId) {
                return store;
            }
        }
        return null;
    }


    public ArrayList<Store> getAllFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Store> result = (ArrayList<Store>) session.createQuery("from Store").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

}