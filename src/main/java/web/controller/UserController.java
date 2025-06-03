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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        // Создаем таблицу, если еще не создана
        userService.createUsersTable();

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users"; // имя шаблона users.html
    }


    // Добавление нового пользователя
    @PostMapping("/add")
    public String addUser(@RequestParam String firstname,
                          @RequestParam String lastname) {
        userService.saveUser(firstname, lastname); // исправлено
        return "redirect:/users";
    }

    // Удаление пользователя по ID
    @PostMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    // Редактирование пользователя
    @PostMapping("/edit")
    public String editUser(@RequestParam long id,
                           @RequestParam String firstname,
                           @RequestParam String lastname) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.setFirstname(firstname);
            user.setLastname(lastname);
            userService.saveUser(firstname, lastname);
        }
        return "redirect:/users";
    }
}