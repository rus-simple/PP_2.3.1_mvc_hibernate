package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userdao;

    public void createUsersTable() {
        this.userdao.createUsersTable();
    }

    public void dropUsersTable() {
        this.userdao.dropUsersTable();
    }

    public void saveUser(User user) {
        this.userdao.saveUser(user);
    }

    public User removeUserById(long id) {
        this.userdao.removeUserById(id);
        return null;
    }

    public List<User> getAllUsers() {
        return this.userdao.getAllUsers();
    }

    public void cleanUsersTable() {
        this.userdao.cleanUsersTable();
    }

    private final List<User> users = new ArrayList<>();

    public UserServiceImp() {
    }

    @Override
    public User getUserById(long id) {
        return userdao.getUserById(id);
    }

    public void updateUser(User user) {
        userdao.updateUser(user);
    }
}