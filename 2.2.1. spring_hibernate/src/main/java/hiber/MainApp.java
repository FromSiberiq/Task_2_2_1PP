package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Danil", "Lastname1", "user1@mail.ru").setCar(new Car("Nissan", 131)));
        userService.add(new User("Dmitriy", "Lastname2", "user2@mail.ru").setCar(new Car("Audi", 441)));
        userService.add(new User("Ivan", "Lastname3", "user3@mail.ru").setCar(new Car("Toyota", 143)));
        userService.add(new User("Matvei", "Lastname4", "user4@mail.ru").setCar(new Car("Mitsubishi", 1325)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        Optional<User> userOp = userService.findUserByCar("Nissan", 131);

        userOp.ifPresent(System.out::println);

        context.close();
    }
}
