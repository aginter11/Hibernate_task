package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {

        UserServiceImpl us = new UserServiceImpl();
   //     us.createUsersTable();
//        us.saveUser("Ivan", "Ivanov", (byte) 22);
//        us.saveUser("Petr", "Petrov", (byte) 33);
//        us.saveUser("Igor", "Sidorov", (byte) 55);
//        us.getAllUsers();
//        us.cleanUsersTable();
//        us.dropUsersTable();


//        us.saveUser("Ivan", "Ivanov", (byte) 11);
//        us.saveUser("Petr", "Petrov", (byte) 22);
//        us.saveUser("Igor", "Sidorov", (byte) 33);
        us.getAllUsers();

     //   us.saveUser("AAA", "BBB", (byte) 55);


    }
}
