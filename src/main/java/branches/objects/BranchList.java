package branches.objects;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class BranchList {

    public boolean create(Branch branch) {
        if (!checkIfBranchExists(branch.getName())) {
            HibernateUtil hibernateUtil = new HibernateUtil();
            Session session = hibernateUtil.beginSessionTransaction();
            session.save(branch);
            session.getTransaction().commit();
            session.close();
            hibernateUtil.shutdown();
            return true;
        }
        return false;
    }

    public synchronized static void update(Branch branch) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.update(branch);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public void deleteBranch(Branch branch) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.delete(branch);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public static ArrayList<Branch> getBranchList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<Branch> branchesList = (ArrayList<Branch>) session.createQuery("from Branch").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return branchesList;
    }

    public static String[] getBranchTitles(ArrayList<Branch> branchList) {
        String[] storeNames = new String[branchList.size()];
        for (int i = 0; i < branchList.size(); i++) {
            storeNames[i] = branchList.get(i).getName();
        }
        return storeNames;
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

    public boolean checkIfBranchExists(String branchName) {
        for (Branch branch : getBranchList())
            if (branch.getName().equals(branchName))
                return true;
        return false;
    }
}