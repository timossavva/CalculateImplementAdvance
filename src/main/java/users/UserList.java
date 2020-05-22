package users;

import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;

public class UserList {
    private String accountType;

    public boolean create(User user) {
        if (!checkIfUsernameExists(user.getUsername())) {
            HibernateUtil hibernateUtil = new HibernateUtil();
            Session session = hibernateUtil.beginSessionTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            hibernateUtil.shutdown();
            return true;
        }
        return false;
    }

    public ArrayList<User> getUserList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        ArrayList<User> userList = (ArrayList<User>) session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
        return userList;
    }


    public void update(User user) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public void deleteUser(User user) {
        HibernateUtil hibernateUtil = new HibernateUtil();
        Session session = hibernateUtil.beginSessionTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        hibernateUtil.shutdown();
    }

    public boolean checkIfUsernameExists(String username) {
        for (User user : getUserList())
            if (user.getUsername().equals(username))
                return true;
        return false;
    }

    public User validateCredentials(String u, String p) {
        for (User user : getUserList()) {
            if (user.exists(u, p)) {
                accountType = user.getType();
                return user;
            }
        }
        return null;
    }


    public void openCorrespondingUI(User user) {
        String[] accountTypes = AccountType.getAccountTypes();
        if (accountTypes[0].equals(accountType)) {
            new AdministrativeFrame();
        } else if (accountTypes[1].equals(accountType)) {

        } else if (accountTypes[2].equals(accountType)) {

        } else if (accountTypes[3].equals(accountType)) {

        } else if (accountTypes[4].equals(accountType)) {

        } else if (accountTypes[5].equals(accountType)) {
            new StoreKeeperFrame(user.getBranch());
        }
    }
}