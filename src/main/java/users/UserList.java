package users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    public static SessionFactory sessionFactory;
    public static Session session;
    private final ArrayList<User> userList;
    private String accountType;

    public UserList() {
        HibernateUtil hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.setUp();
        userList = getAllFromDb();
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public User checkData(String u, String p) {
        int outcome, pos = -2;

        for (User user : userList) {
            outcome = user.isUser(u, p);
            if (outcome == 0) {
                accountType = user.getType();
                return user;
            }
        }
        return null;
    }

    public String getAccountType() {
        return accountType;
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


    public boolean create(User user) {
        if (!checkIfUsernameExists(sessionFactory, user.getUsername())) {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }

    public void update(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    public User getWithId(SessionFactory sessionFactory, long userId) {
        session = sessionFactory.openSession();
        User user = session.get(User.class, userId);
        session.close();
        return user;
    }


    private ArrayList<User> getAllFromDb() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        ArrayList<User> result = (ArrayList<User>) session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }


    public List<User> search(String searchQuery) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User U WHERE U.username LIKE :query");
        query.setParameter("query", "%" + searchQuery + "%");
        List<User> result = (List<User>) query.list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public boolean checkIfUsernameExists(SessionFactory sessionFactory, String username) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User U WHERE U.username = :username");
        query.setParameter("username", username);
        List<User> result = (List<User>) query.list();
        session.getTransaction().commit();
        session.close();
        return result.size() > 0;
    }

    public void deleteUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
}