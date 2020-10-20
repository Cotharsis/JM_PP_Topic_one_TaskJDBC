package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(20), lastname VARCHAR(40), age INT)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE `jm_schema_one`.`users`").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory = null;
        List<User> list = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("FROM User");
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory = null;
        try {
            sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}

