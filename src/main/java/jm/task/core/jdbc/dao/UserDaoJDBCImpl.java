package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private List<User> allUsers = new ArrayList<>();
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
        String sqlCommandSaveUser = "INSERT users (Username, User_lastName, Age) VALUES ('" +
                name + "', '" + lastName + "', " + age + ")";
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCommandSaveUser);
            String sqlCommandGetId = "SELECT id FROM users WHERE Username = '" + name +
                    "' AND User_lastName = '" + lastName + "' AND Age = " + age;
            ResultSet resultSet = statement.executeQuery(sqlCommandGetId);
            long id = 0;
            while(resultSet.next()) {
                id = resultSet.getInt(1);
            }
            user.setId(id);
            allUsers.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
