package primary_accounts;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class PrimaryAccountList {

    public static ArrayList<PrimaryAccount> getPrimaryAccountList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<PrimaryAccount> primaryAccountList = (ArrayList<PrimaryAccount>) session.createQuery("from PrimaryAccount ").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return primaryAccountList;
    }

    public static String[] getPrimaryAccountTitles() {
        ArrayList<PrimaryAccount> primaryAccountsList = getPrimaryAccountList();
        String[] primaryAccountTitles = new String[primaryAccountsList.size()];
        for (int i = 0; i < primaryAccountsList.size(); i++) {
            primaryAccountTitles[i] = primaryAccountsList.get(i).getName();
        }
        return primaryAccountTitles;
    }
}
