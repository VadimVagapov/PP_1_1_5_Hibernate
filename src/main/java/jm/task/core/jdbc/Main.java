package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User with name " + userDao.getAllUsers().get(0).getName() +
        " add in Database");
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User with name " + userDao.getAllUsers().get(1).getName() +
                " add in Database");
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User with name " + userDao.getAllUsers().get(2).getName() +
                " add in Database");
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User with name " + userDao.getAllUsers().get(3).getName() +
                " add in Database");
        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        Util.closeConnection();
    }
}
