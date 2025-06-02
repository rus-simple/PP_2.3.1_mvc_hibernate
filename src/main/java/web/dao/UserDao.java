package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void saveUser(String firstname, String lastName);

    void createUsersTable();

    void dropUsersTable();

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
