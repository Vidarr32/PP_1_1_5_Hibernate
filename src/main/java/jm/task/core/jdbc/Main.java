package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Иван1", "Иванов1", (byte) 12);
        System.out.println("User с именем – Иван1 добавлен в базу данных");
        userService.saveUser("Иван2", "Иванов2", (byte) 13);
        System.out.println("User с именем – Иван2 добавлен в базу данных");
        userService.saveUser("Иван3", "Иванов3", (byte) 14);
        System.out.println("User с именем – Иван3 добавлен в базу данных");
        userService.saveUser("Иван3", "Иванов3", (byte) 14);
        System.out.println("User с именем – Иван4 добавлен в базу данных");

        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
