package stores.objects;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class StoreList {

    public boolean create(Store store) {
        if (!checkIfStoreExists(store.getName())) {
            HibernateUtil hibernateUtil = new HibernateUtil();
            Session session = hibernateUtil.beginSessionTransaction();
            session.save(store);
            session.getTransaction().commit();
            session.close();
            hibernateUtil.shutdown();
        }
        return true;

    }

    public void update(Store store) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.update(store);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public void deleteStore(Store store) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.delete(store);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    private boolean checkIfStoreExists(String name) {
        for (Store store : getStoreList())
            if (store.getName().equals(name))
                return true;
        return false;
    }

    public static ArrayList<Store> getStoreList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<Store> storeList = (ArrayList<Store>) session.createQuery("from Store").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return storeList;
    }

    public static String[] getStoreNames(ArrayList<Store> storeList) {
        String[] storeNames = new String[storeList.size()];
        for (int i = 0; i < storeList.size(); i++) {
            storeNames[i] = storeList.get(i).getName();
        }
        return storeNames;
    }

}