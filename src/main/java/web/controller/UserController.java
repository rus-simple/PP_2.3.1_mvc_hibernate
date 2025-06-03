package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/users") // Обработка запросов, начинающихся с /users
public class UserController {

    @Autowired
    private UserService userService; // Внедрение сервиса для работы с пользователями

    /**
     * Обработка GET-запроса по адресу /users
     * Отображает список всех пользователей
     */
    @GetMapping
    public String listUsers(Model model) {
        // Создаем таблицу пользователей, если она еще не создана
        userService.createUsersTable();

        // Получаем список всех пользователей из базы данных
        List<User> users = userService.getAllUsers();

        // Передаем список пользователей в модель для отображения в шаблоне
        model.addAttribute("users", users);

        // Возвращаем имя шаблона (например, users.html)
        return "users";
    }

    /**
     * Обработка POST-запроса для добавления нового пользователя по адресу /users/add
     * Получает firstname и lastname из параметров формы
     */
    @PostMapping("/add")
    public String saveUser(@RequestParam String firstname,
                          @RequestParam String lastname) {

        // Сохраняем нового пользователя в базе данных
        //userService.saveUser(firstname, lastname);
        userService.saveUser(firstname, lastname);
        // Перенаправляем обратно на страницу со списком пользователей
        return "redirect:/users";
    }

    /**
     * Обработка POST-запроса для удаления пользователя по ID по адресу /users/delete
     */
    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        // Удаляем пользователя по его ID
        userService.removeUserById(id);
        // Перенаправляем обратно на страницу со списком пользователей
        return "redirect:/users";
    }

    /**
     * Обработка POST-запроса для редактирования пользователя по адресу /users/edit
     * Получает id, firstname и lastname из параметров формы
     */
    @PostMapping("/edit")
    public String editUser(@RequestParam long id,
                           @RequestParam String firstname,
                           @RequestParam String lastname) {
        // Получаем текущего пользователя по ID
        User user = userService.getUserById(id);
        if (user != null) {
            // Обновляем поля пользователя новыми значениями
            user.setFirstname(firstname);
            user.setLastname(lastname);
            // Сохраняем обновленного пользователя в базе данных
            userService.updateUser(user);
        }
        // Перенаправляем обратно на страницу со списком пользователей
        return "redirect:/users";
    }

    @PostMapping("/clean")
    public String cleanUsersTable() {
        userService.cleanUsersTable();
        return "redirect:/users";
    }
}