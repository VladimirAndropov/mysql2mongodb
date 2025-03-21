package com.okmich.mysql2mongodb.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.okmich.mysql2mongodb.migrate.BaseMigration;
import  com.okmich.mysql2mongodb.migrate.UsersByGenre;

public class UserPanel extends JPanel {
    private JButton button1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JComboBox comboBox1;

    /**
     * @param dbServerUrl
     * @param dbUser
     * @param dbPassword
     * @param mongoDbUrl
     * @param mongoDbName
     */
    public UserPanel(String dbServerUrl, String dbUser, String dbPassword,
                     String mongoDbUrl, String mongoDbName) {
        $$$setupUI$$$();
        comboBox1.addItem("F");
        comboBox1.addItem("M");

//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Получаем данные из MongoDB
//                BaseMigration migrationUtil = new UsersByGenre(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
//                String data = migrationUtil.getDataFromMongo();
//
//                // Отображаем данные в JTextArea
//                textArea1.append(data);
//                textArea1.append("success");
//            }
//        });


        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox1.getSelectedItem();
                BaseMigration migrationUtil = new UsersByGenre(dbServerUrl, dbUser, dbPassword, mongoDbUrl, mongoDbName);
                String data = migrationUtil.getDataFromMongo(selectedItem);

                // Отображаем данные в JTextArea
                textArea1.setText(data);
                textArea1.append("success");
            }
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        textArea1 = new JTextArea();
        panel1.add(textArea1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        button1 = new JButton();
        button1.setText("Button");
        panel1.add(button1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        comboBox1.setModel(defaultComboBoxModel1);
        panel1.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


}
