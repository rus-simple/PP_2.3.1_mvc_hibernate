package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

// Объявляем класс как контроллер Spring
@Controller
public class HelloController {

	// Обработка GET-запроса по URL "/"
	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		// Создаем список сообщений для отображения на странице
		List<String> messages = new ArrayList<>();
		messages.add("Hello!"); // Первое сообщение
		messages.add("I'm Spring MVC application"); // Второе сообщение
		messages.add("5.2.0 version by sep'19 "); // Третье сообщение с версией и датой

		// Передаем список сообщений в модель под именем "messages"
		model.addAttribute("messages", messages);

		// Возвращаем имя представления (шаблона) "index"
		// Это означает, что будет использован файл index.html или index.jsp (зависит от конфигурации)
		return "index";
	}
}