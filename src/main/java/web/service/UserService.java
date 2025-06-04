package web.service;

import web.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void createUsersTable();
    void dropUsersTable();
    void saveUser(User user);
    User removeUserById(long id);
    void cleanUsersTable();
    User getUserById(long id);
    void updateUser(User user);
}