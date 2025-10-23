package Curriculum_Design;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SEARCH {
    // 是否已经存在
    boolean exist = false;

    int id_1 = 0, id_2 = 0;
    String password_1 = "", password_2 = "";
    String name_1 = "", name_2 = "";
    String gender_1 = "", gender_2 = "";
    int age_1 = 0, age_2 = 0;
    int phone_number_1 = 0, phone_number_2 = 0;
    String address_1 = "", address_2 = "";
    String email_1 = "", email_2 = "";
    int level_1 = 0, level_2 = 0;

    public SEARCH(String table, int id, int phone_number, JLabel jLabel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql;

        try {
            connection = TOOLS.DBCP.getConnection();

            // 搜索数据
            sql = "select `id`, `phone_number` from " + table + " where `id` = " + id + " || `phone_number` = " + phone_number;
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            int i = 1;
            while (resultSet.next()) {
                exist = true;
                // 是否需要打印
                sql = "select * from " + table + " where `id` = " + resultSet.getObject("id");
                preparedStatement = connection.prepareStatement(sql);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    if (rs.getInt("level") >= TOOLS.level){
                        if (i == 1) {
                            this.id_1 = rs.getInt("id");
                            this.password_1 = rs.getString("password");
                            this.name_1 = rs.getString("name");
                            this.gender_1 = rs.getString("gender");
                            this.age_1 = rs.getInt("age");
                            this.phone_number_1 = rs.getInt("phone_number");
                            this.address_1 = rs.getString("address");
                            this.email_1 = rs.getString("email");
                            this.level_1 = rs.getInt("level");
                            i++;
                        } else {
                            this.id_2 = rs.getInt("id");
                            this.password_2 = rs.getString("password");
                            this.name_2 = rs.getString("name");
                            this.gender_2 = rs.getString("gender");
                            this.age_2 = rs.getInt("age");
                            this.phone_number_2 = rs.getInt("phone_number");
                            this.address_2 = rs.getString("address");
                            this.email_2 = rs.getString("email");
                            this.level_2 = rs.getInt("level");
                        }
                    }else {
                        jLabel.setText("当前管理员等级较低，无法查询");
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TOOLS.DBCP.Release(connection, preparedStatement, resultSet);
        }
    }
}
