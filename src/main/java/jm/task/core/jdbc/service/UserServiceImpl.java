package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    //UserDaoJDBCImpl userDaoRealization = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoRealization = new UserDaoHibernateImpl();
    public void createUsersTable() {
       userDaoRealization.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoRealization.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoRealization.saveUser(name,lastName, age);
    }

    public void removeUserById(long id) {
        userDaoRealization.removeUserById(id);

    }

    public List<User> getAllUsers() {
       return userDaoRealization.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoRealization.cleanUsersTable();
    }
}
