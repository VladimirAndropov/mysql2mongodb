/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.mysql2mongodb;

import com.okmich.mysql2mongodb.ui.AppFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michael.enudi
 */
public class Main {



    public static void main(String[] args) {


        String userInput = (String) JOptionPane.showInputDialog(
                null,
                "Введите данные через запятую:",
                "Movielens on Mongo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "users,jdbc:mysql://localhost:3306/movielens,debian-sys-maint,Fpqjfwnl9KQKzrPM,mongodb://localhost:27017/movielens,movielens");

        String[] parts = userInput.split(",");
        /**
         *
         * @param dbServerUrl
         * @param dbUser
         * @param dbPassword
         * @param mongoDbUrl
         * @param mongoDbName
         */

        Map<String, String> param = new HashMap<>();
        param.put("dbServerUrl", null);
        param.put("dbUser", null);
        param.put("dbPassword", null);
        param.put("mongoDbUrl", null);
        param.put("mongoDbName", null);

        String entity = param.getOrDefault( parts[0], "users");
        String dbServerUrl = param.getOrDefault( parts[1], "jdbc:mysql://localhost:3306/movielens");
        String dbUser =  param.getOrDefault( parts[2], "debian-sys-maint");
        String dbPassword =  param.getOrDefault( parts[3], "Fpqjfwnl9KQKzrPM");
        String mongoDbUrl =  param.getOrDefault( parts[4], "mongodb://localhost:27017/movielens");
        String mongoDbName =  param.getOrDefault( parts[5], "movielens");

//        if (entity == null || entity.isEmpty()) {
//            System.err.println("Specify at least the table you wish to migrate ");
//            System.err.println("Usage: Main -tblname=[users|movies|tags|ratings|ratings2] -mysqluser=[username] "
//                    + "-mysqlpassword=[password] -mysqlurl=[jdbc-url] -mongourl=[mongourl] -mongodb=[mongodb]");
//            System.exit(-1);
//        }

//        BaseMigration migrationUtil;
//        switch (entity.toLowerCase()) {
//            case "users":
//                migrationUtil = new UsersMigration(jdbcUrl, jdbcUsername, jdbcPassword, mongoUrl, mongoDBName);
//                break;
//            case "movies":
//                migrationUtil = new MoviesMigration(jdbcUrl, jdbcUsername, jdbcPassword, mongoUrl, mongoDBName);
//                break;
//            case "tags":
//                migrationUtil = new TagsMigration(jdbcUrl, jdbcUsername, jdbcPassword, mongoUrl, mongoDBName);
//                break;
//            case "ratings":
//                migrationUtil = new RatingsMigration(jdbcUrl, jdbcUsername, jdbcPassword, mongoUrl, mongoDBName);
//                break;
//            case "ratings2":
//                migrationUtil = new RatingsV2Migration(jdbcUrl, jdbcUsername, jdbcPassword, mongoUrl, mongoDBName);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown entity");
//        }
//
//        migrationUtil.migrate();
//
//        System.out.println(">>>>>>>>>>>>> Done");
//

        SwingUtilities.invokeLater(() -> {
            // Создаем экземпляр формы
            AppFrame appForm = new AppFrame(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
            // Делаем форму видимой
            appForm.setVisible(true);
        });

    }

}
