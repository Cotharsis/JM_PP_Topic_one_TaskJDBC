package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alesha", "Srik",(byte)6);
        userService.saveUser("Dlesha", "Srik",(byte)127);
        userService.saveUser("Salesha", "Srik",(byte)29);
        List<User> allUsers = userService.getAllUsers();
        for(User x:allUsers){
            System.out.println(x);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}




