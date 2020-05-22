package branches;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class BranchList {

    public void create(Branch branch) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.save(branch);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public ArrayList<Branch> getBranchList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<Branch> branchesList = (ArrayList<Branch>) session.createQuery("from Branch").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return branchesList;
    }

    public Branch refreshBranchData(Branch branch) {
        ArrayList<Branch> branchesList = getBranchList();
        for (Branch b : branchesList) {
            if (b.getId() == branch.getId()) {
                return b;
            }
        }
        return null;
    }


}