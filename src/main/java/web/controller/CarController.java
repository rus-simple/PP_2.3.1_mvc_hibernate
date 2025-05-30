package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService; // сервис для работы с машинами

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService; // внедрение зависимости через конструктор
    }

    @GetMapping("/cars") // обработчик GET-запроса по URL /cars
    public String showCars(@RequestParam(value = "count", required = false) Integer count, Model model) {
        if (count == null || count < 1) {
            count = Integer.MAX_VALUE; // показываем все машины, если параметр не задан или меньше 1
        }
        List<Car> cars = carService.getCars(count); // получаем список машин по количеству
        model.addAttribute("cars", cars); // добавляем список машин в модель для отображения в представлении
        return "cars"; // возвращаем имя шаблона для отображения
    }
}