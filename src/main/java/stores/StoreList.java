package stores;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class StoreList {

    public void create(Store store) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.save(store);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public ArrayList<Store> getStoreList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<Store> storeList = (ArrayList<Store>) session.createQuery("from Store").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return storeList;
    }

}