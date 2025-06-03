package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(String firstname, String lastName);
    void createUsersTable();
    void dropUsersTable();
    void removeUserById(long id);
    List<User> getAllUsers();
    void cleanUsersTable();
    User getUserById(long id);
    void updateUser(User user);
}
