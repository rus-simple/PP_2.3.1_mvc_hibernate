package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Service // Обозначение класса как сервиса Spring
public class CarServiceImp implements CarService {
    private final List<Car> cars = new ArrayList<>(); // список машин

    public CarServiceImp() {
        // Инициализация списка машин
        cars.add(new Car("Toyota", "RAV4", 2022));
        cars.add(new Car("Mazda", "CX-5", 2015));
        cars.add(new Car("Ford", "Explorer", 2020));
        cars.add(new Car("Porsche", "Cayenne", 2023));
        cars.add(new Car("Dodge", "Challenger", 2024));
    }

    @Override
    public List<Car> getCars(int count) {
        if (count >= cars.size()) {
            return new ArrayList<>(cars); // возвращаем весь список, если запрошенное количество больше или равно размера списка
        } else {
            return new ArrayList<>(cars.subList(0, count)); // возвращаем подсписок из первых count элементов
        }
    }
}