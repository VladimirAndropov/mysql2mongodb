/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.mysql2mongodb;

import com.okmich.mysql2mongodb.migrate.BaseMigration;
import com.okmich.mysql2mongodb.migrate.MoviesMigration;
import com.okmich.mysql2mongodb.migrate.RatingsMigration;
import com.okmich.mysql2mongodb.migrate.RatingsV2Migration;
import com.okmich.mysql2mongodb.migrate.TagsMigration;
import com.okmich.mysql2mongodb.migrate.UsersMigration;
import com.okmich.mysql2mongodb.ui.AppFrame;

import javax.swing.*;
import java.awt.*;
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
        String entity = getOrDefault( parts,0, "users");
        String dbServerUrl = getOrDefault(parts,1, "jdbc:mysql://localhost:3306/movielens");
        String dbUser =  getOrDefault(parts,2, "debian-sys-maint");
        String dbPassword =  getOrDefault(parts,3, "Fpqjfwnl9KQKzrPM");
        String mongoDbUrl =  getOrDefault(parts,4, "mongodb://localhost:27017/movielens");
        String mongoDbName =  getOrDefault(parts,5, "movielens");

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
    public static String getOrDefault(String[] array, int index, String defaultValue) {
        if (index >= 0 && index < array.length) {
            return array[index].trim(); // Убираем лишние пробелы
        }
        return defaultValue;
    }
}
