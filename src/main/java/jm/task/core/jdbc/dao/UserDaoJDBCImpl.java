package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sqlCommandCreate = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, " +
                "Username VARCHAR(20), User_lastName VARCHAR(20), Age INT (127))";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sqlCommandDrop = "DROP TABLE IF EXISTS users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandDrop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
//        String sqlCommandSaveUser = "INSERT users (Username, User_lastName, Age) VALUES ('" +
//                name + "', '" + lastName + "', " + age + ")";
        String sqlCommandSaveUser = "INSERT users (Username, User_lastName, Age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sqlCommandSaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try (Statement statement = Util.getConnection().createStatement()) {
//            statement.executeUpdate(sqlCommandSaveUser);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public void removeUserById(long id) {
        String sqlCommandRemove = "DELETE FROM users WHERE Id = " + id;
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandRemove);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try(Statement statement = Util.getConnection().createStatement()) {
            String sqlCommandGetId = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(sqlCommandGetId);
            while(resultSet.next()) {
                User user = new User();
                user.setId((long)resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String sqlCommandClean = "TRUNCATE TABLE users";
        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandClean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getAllUsers().clear();
    }
}
