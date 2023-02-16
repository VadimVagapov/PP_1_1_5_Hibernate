package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
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
        System.out.println(userDao.getAllUsers().size());
        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
