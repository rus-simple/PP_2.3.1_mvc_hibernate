package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;
import java.util.ArrayList;
import java.util.List;

@Service // Обозначение класса как сервиса Spring
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userdao;

    public void createUsersTable() {
        this.userdao.createUsersTable();
    }

    public void dropUsersTable() {
        this.userdao.dropUsersTable();
    }

    public void saveUser(String firstname, String lastName) {
        this.userdao.saveUser(firstname, lastName);
        System.out.println("User с именем — " + firstname + " добавлен в базу данных.");
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

    private final List<User> users = new ArrayList<>(); // список машин

    public UserServiceImp() {
        // Инициализация списка машин
        users.add(new User("Иван", "Петров"));
        users.add(new User("Григорий", "Смирнов"));
        users.add(new User("Пётр", "Кротов"));
        users.add(new User("Анастасия", "Сидорова"));
        users.add(new User("Вероника", "Иванова"));
    }

    @Override
    public User getUserById(long id) {
        return userdao.getUserById(id);
    }

//    @Transactional
//    @Override
//    public void add(User user) {
//        // Сохраняем пользователя
//        UserDao.add(user);
//    }


//    @Override
//    public List<User> getAllUsers(int count) {
//        if (count >= users.size()) {
//            return new ArrayList<>(users); // возвращаем весь список, если запрошенное количество больше или равно размера списка
//        } else {
//            return new ArrayList<>(users.subList(0, count)); // возвращаем подсписок из первых count элементов
//        }
//    }
}