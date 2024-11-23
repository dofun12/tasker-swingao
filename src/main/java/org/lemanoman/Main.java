package org.lemanoman;

import javax.swing.*;
import java.awt.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SqliteFactory sqliteFactory = new SqliteFactory(System.getProperty("user.home") + File.separator + "tasks.db");

        TaskGui taskGui = new TaskGui(sqliteFactory);
        JFrame frame = new JFrame("TaskGui");
        frame.setContentPane(taskGui.getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);


    }
}