package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Иван1", "Иванов1", (byte) 12);
    private static final User user2 = new User("Иван2", "Иванов2", (byte) 13);
    private static final User user3 = new User("Иван3", "Иванов3", (byte) 14);
    private static final User user4 = new User("Иван4", "Иванов4", (byte) 15);


    public static void main(String[] args) {

        userService.createUsersTable();

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);

        userList.forEach(x -> {userService.saveUser(x.getName(), x.getLastName(), x.getAge());
            System.out.println("User с именем – " + x.getName() + " добавлен в базу данных");});

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
