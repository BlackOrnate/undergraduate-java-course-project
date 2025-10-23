package Curriculum_Design;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class TOOLS {
    static int anInt;
    static int level;
    String page = "LOGIN";

    static class DBCP {
        private static DataSource dataSource = null;

        static {
            try {
                InputStream in = DBCP.class.getClassLoader().getResourceAsStream("dbcp_config.properties");
                Properties properties = new Properties();
                properties.load(in);

                // 创建数据源 工厂模式
                dataSource = BasicDataSourceFactory.createDataSource(properties);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // 获取连接
        public static Connection getConnection() throws Exception {
            return dataSource.getConnection();
        }

        // 释放连接
        public static void Release(Connection connection, Statement statement, ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TestFiled_LISTENER implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TextField textField = (TextField) e.getSource();
            System.out.println(textField.getText());
        }
    }

    static class JPasswordField_LISTENER implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPasswordField jPasswordField = (JPasswordField) e.getSource();
            System.out.println(jPasswordField.getPassword());
        }
    }

    static class LOGIN_JButton_login_LISTENER implements ActionListener {
        TextField textField1;
        TextField textField2;
        JPasswordField jPasswordField;
        JLabel jLabel;
        TOOLS tools;
        JButton jButton;

        public LOGIN_JButton_login_LISTENER(TextField textField1, JPasswordField jPasswordField, TextField textField2, JLabel jLabel, TOOLS tools, JButton jButton) {
            this.textField1 = textField1;
            this.jPasswordField = jPasswordField;
            this.textField2 = textField2;
            this.jLabel = jLabel;
            this.tools = tools;
            this.jButton = jButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SEARCH search = new SEARCH("administrator_information", Integer.parseInt(textField1.getText()), Integer.parseInt(textField1.getText()), jLabel);
            if (search.exist) {
                if (search.password_1.equals(new String(jPasswordField.getPassword()))) {
                    if (textField2.getText().equals(jLabel.getText())) {
                        level = search.level_1;
                        jButton.setText("登 录 成 功");
                        tools.page = "MENU";
                    } else {
                        jPasswordField.setText("");
                        textField2.setText("验证码错误");
                    }
                } else {
                    textField1.setText("密码错误");
                    jPasswordField.setText("");
                    textField2.setText("");
                }
            } else {
                textField1.setText("用户不存在");
                jPasswordField.setText("");
                textField2.setText("");
            }
        }
    }

    static class LOGIN_JButton_verification_code_LISTENER implements ActionListener {
        JLabel jLabel;

        public LOGIN_JButton_verification_code_LISTENER(JLabel jLabel) {
            this.jLabel = jLabel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            jLabel.setText(String.format("%04d", new Random().nextInt(9999)));
        }
    }

    static class MENU_JButton_Administrator_Window_LINSTENER implements ActionListener {
        TOOLS tools;

        public MENU_JButton_Administrator_Window_LINSTENER(TOOLS tools) {
            this.tools = tools;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tools.page = "ADMINISTRATOR_WINDOW";
        }
    }

    static class MENU_JButton_User_Window_LINSTENER implements ActionListener {
        TOOLS tools;

        public MENU_JButton_User_Window_LINSTENER(TOOLS tools) {
            this.tools = tools;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tools.page = "USER_WINDOW";
        }
    }

    static class MENU_JButton_Back_LINSTENER implements ActionListener {
        TOOLS tools;

        public MENU_JButton_Back_LINSTENER(TOOLS tools) {
            this.tools = tools;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tools.page = "LOGIN";
        }
    }

    static class WINDOW_JButton_LINSTENER implements ActionListener {
        TextField textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level;
        JScrollPane jScrollPane;
        JLabel jLabel;
        String button_name, table_name;
        String[] information = {"id", "password", "name", "gender", "age", "phone_number", "address", "email", "level"};
        TOOLS tools;

        public WINDOW_JButton_LINSTENER(TextField textField_id, TextField textField_password, TextField textField_name, TextField textField_gender, TextField textField_age, TextField textField_phone_number, TextField textField_address, TextField textField_email, TextField textField_level, JScrollPane jScrollPane, JLabel jLabel, String button_name, String table_name, TOOLS tools) {
            this.textField_id = textField_id;
            this.textField_password = textField_password;
            this.textField_name = textField_name;
            this.textField_gender = textField_gender;
            this.textField_age = textField_age;
            this.textField_phone_number = textField_phone_number;
            this.textField_address = textField_address;
            this.textField_email = textField_email;
            this.textField_level = textField_level;
            this.jScrollPane = jScrollPane;
            this.jLabel = jLabel;
            this.button_name = button_name;
            this.table_name = table_name;
            this.tools = tools;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (button_name) {

                case "增":
                    new ADD(table_name, textField_password.getText(), textField_name.getText(), textField_gender.getText(), Integer.parseInt(textField_age.getText()), Integer.parseInt(textField_phone_number.getText()), textField_address.getText(), textField_email.getText(), Integer.parseInt(textField_level.getText()), jLabel);
                    SEARCH add_search = new SEARCH(table_name, 0, Integer.parseInt(textField_phone_number.getText()), jLabel);
                    Object[][] add_table = new Object[1][9];

                    add_table[0][0] = add_search.id_1;
                    add_table[0][1] = add_search.password_1;
                    add_table[0][2] = add_search.name_1;
                    add_table[0][3] = add_search.gender_1;
                    add_table[0][4] = add_search.age_1;
                    add_table[0][5] = add_search.phone_number_1;
                    add_table[0][6] = add_search.address_1;
                    add_table[0][7] = add_search.email_1;
                    add_table[0][8] = add_search.level_1;

                    if(tools.page.equals("ADMINISTRATOR_WINDOW")){
                        ADMINISTRATOR_WINDOW.jTable = new JTable(add_table, information);
                        jScrollPane.getViewport().add(ADMINISTRATOR_WINDOW.jTable);
                    } else if (tools.page.equals("USER_WINDOW")){
                        USER_WINDOW.jTable = new JTable(add_table, information);
                        jScrollPane.getViewport().add(USER_WINDOW.jTable);
                    }


                    break;
                case "删":
                    SEARCH delete_search = new SEARCH(table_name, Integer.parseInt(textField_id.getText()), Integer.parseInt(textField_phone_number.getText()), jLabel);
                    Object[][] delete_table = new Object[1][9];

                    delete_table[0][0] = delete_search.id_1;
                    delete_table[0][1] = delete_search.password_1;
                    delete_table[0][2] = delete_search.name_1;
                    delete_table[0][3] = delete_search.gender_1;
                    delete_table[0][4] = delete_search.age_1;
                    delete_table[0][5] = delete_search.phone_number_1;
                    delete_table[0][6] = delete_search.address_1;
                    delete_table[0][7] = delete_search.email_1;
                    delete_table[0][8] = delete_search.level_1;

                    if(tools.page.equals("ADMINISTRATOR_WINDOW")){
                        ADMINISTRATOR_WINDOW.jTable = new JTable(delete_table, information);
                        jScrollPane.getViewport().add(ADMINISTRATOR_WINDOW.jTable);
                    } else if (tools.page.equals("USER_WINDOW")){
                        USER_WINDOW.jTable = new JTable(delete_table, information);
                        jScrollPane.getViewport().add(USER_WINDOW.jTable);
                    }

                    new DELETE(table_name, Integer.parseInt(textField_id.getText()), Integer.parseInt(textField_phone_number.getText()), jLabel);


                    break;
                case "改":
                    new MODIFY(table_name, Integer.parseInt(textField_id.getText()), textField_password.getText(), textField_name.getText(), textField_gender.getText(), Integer.parseInt(textField_age.getText()), Integer.parseInt(textField_phone_number.getText()), textField_address.getText(), textField_email.getText(), Integer.parseInt(textField_level.getText()), jLabel);

                    SEARCH modify_search = new SEARCH(table_name, Integer.parseInt(textField_id.getText()), Integer.parseInt(textField_phone_number.getText()), jLabel);
                    Object[][] modify_table = new Object[1][9];

                    modify_table[0][0] = modify_search.id_1;
                    modify_table[0][1] = modify_search.password_1;
                    modify_table[0][2] = modify_search.name_1;
                    modify_table[0][3] = modify_search.gender_1;
                    modify_table[0][4] = modify_search.age_1;
                    modify_table[0][5] = modify_search.phone_number_1;
                    modify_table[0][6] = modify_search.address_1;
                    modify_table[0][7] = modify_search.email_1;
                    modify_table[0][8] = modify_search.level_1;

                    if(tools.page.equals("ADMINISTRATOR_WINDOW")){
                        ADMINISTRATOR_WINDOW.jTable = new JTable(modify_table, information);
                        jScrollPane.getViewport().add(ADMINISTRATOR_WINDOW.jTable);
                    } else if (tools.page.equals("USER_WINDOW")){
                        USER_WINDOW.jTable = new JTable(modify_table, information);
                        jScrollPane.getViewport().add(USER_WINDOW.jTable);
                    }

                    break;
                case "细节查":
                    SEARCH search = new SEARCH(table_name, Integer.parseInt(textField_id.getText()), Integer.parseInt(textField_phone_number.getText()), jLabel);
                    Object[][] table;
                    if (search.id_2 == 0) {
                        table = new Object[1][9];

                    } else {
                        table = new Object[2][9];
                        table[1][0] = search.id_2;
                        table[1][1] = search.password_2;
                        table[1][2] = search.name_2;
                        table[1][3] = search.gender_2;
                        table[1][4] = search.age_2;
                        table[1][5] = search.phone_number_2;
                        table[1][6] = search.address_2;
                        table[1][7] = search.email_2;
                        table[1][8] = search.level_2;
                    }

                    table[0][0] = search.id_1;
                    table[0][1] = search.password_1;
                    table[0][2] = search.name_1;
                    table[0][3] = search.gender_1;
                    table[0][4] = search.age_1;
                    table[0][5] = search.phone_number_1;
                    table[0][6] = search.address_1;
                    table[0][7] = search.email_1;
                    table[0][8] = search.level_1;


                    JTable jTable = new JTable(table, information);
                    jScrollPane.getViewport().add(jTable);
                    if (search.id_2 == 0) {
                        if (Integer.parseInt(textField_id.getText()) == search.id_1 && Integer.parseInt(textField_phone_number.getText()) != search.phone_number_1) {
                            jLabel.setText("id = " + search.id_1 + " 存在，查询成功");
                        } else if (Integer.parseInt(textField_id.getText()) != search.id_1 && Integer.parseInt(textField_phone_number.getText()) == search.phone_number_1) {
                            jLabel.setText("phone_number = " + search.phone_number_1 + " 存在，查询成功");
                        } else if (Integer.parseInt(textField_id.getText()) == search.id_1 && Integer.parseInt(textField_phone_number.getText()) == search.phone_number_1) {
                            jLabel.setText("id = " + Integer.parseInt(textField_id.getText()) + " ,phone_number = " + Integer.parseInt(textField_phone_number.getText()) + " 存在，查询成功");
                        } else if (Integer.parseInt(textField_id.getText()) != search.id_1 && Integer.parseInt(textField_phone_number.getText()) != search.phone_number_1) {
                            jLabel.setText("查询失败");
                        }
                    } else {
                        jLabel.setText("id = " + Integer.parseInt(textField_id.getText()) + " ,phone_number = " + Integer.parseInt(textField_phone_number.getText()) + " 存在，查询成功");
                    }

                    break;
                case "模糊查":
                    new FUZZY_QUERY(table_name, Integer.parseInt(textField_id.getText()), textField_password.getText(), textField_name.getText(), textField_gender.getText(), Integer.parseInt(textField_age.getText()), Integer.parseInt(textField_phone_number.getText()), textField_address.getText(), textField_email.getText(), Integer.parseInt(textField_level.getText()), information, jLabel);
                    if(tools.page.equals("ADMINISTRATOR_WINDOW")){
                        jScrollPane.getViewport().add(ADMINISTRATOR_WINDOW.jTable);
                    } else if (tools.page.equals("USER_WINDOW")){
                        jScrollPane.getViewport().add(USER_WINDOW.jTable);
                    }

                    break;
            }
        }
    }

    static class WINDOW_JButton_Back_LINSTENER implements ActionListener {
        TOOLS tools;

        public WINDOW_JButton_Back_LINSTENER(TOOLS tools) {
            this.tools = tools;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tools.page = "MENU";
        }
    }
}
