package branches;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateUtil;

import java.util.ArrayList;

public class BranchList {
    public static SessionFactory sessionFactory;
    public static Session session;
    private final ArrayList<Branch> branchList;

    public BranchList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.setUp();
        branchList = getAllFromDb();
        hibernateUtil.shutdown(sessionFactory);
    }

    public ArrayList<Branch> getBranchList() {
        return branchList;
    }


    public void create(Branch branch) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(branch);
        session.getTransaction().commit();
        session.close();
    }

    public Branch getWithId(long branchId) {
        ArrayList<Branch> allBranches = getAllFromDb();
        for (Branch branch : allBranches) {
            if (branch.getId() == branchId) {
                return branch;
            }
        }
        return null;
    }


    public ArrayList<Branch> getAllFromDb() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<Branch> result = (ArrayList<Branch>) session.createQuery("from Branch").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }


}