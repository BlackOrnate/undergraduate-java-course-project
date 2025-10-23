package Curriculum_Design;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FUZZY_QUERY {
    public FUZZY_QUERY(String table, int id, String password, String name, String gender, int age, int phone_number, String address, String email, int level, String[] information, JLabel jLabel) {

        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String sql;

        try {
            connection = TOOLS.DBCP.getConnection();

            sql = "select `id`, `password`, `name`, `gender`, `age`, `phone_number`, `address`, `email`, `level` " +
                    "from " + table + " where `id` like ? || `password` like ? || `name` like ? || `gender` like ? " +
                    "|| `age` like ? || `phone_number` like ? || `address` like ? || `email` like ? || `level` like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + id + "%");
            preparedStatement.setString(2, "%" + password + "%");
            preparedStatement.setString(3, "%" + name + "%");
            preparedStatement.setString(4, "%" + gender + "%");
            preparedStatement.setString(5, "%" + age + "%");
            preparedStatement.setString(6, "%" + phone_number + "%");
            preparedStatement.setString(7, "%" + address + "%");
            preparedStatement.setString(8, "%" + email + "%");
            preparedStatement.setString(9, "%" + level + "%");

            resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            resultSet = preparedStatement.executeQuery();

            Object[][] search_table = new Object[count][9];
            count = 0;
            while (resultSet.next()) {
                search_table[count][0] = resultSet.getInt("id");
                search_table[count][1] = resultSet.getString("password");
                search_table[count][2] = resultSet.getString("name");
                search_table[count][3] = resultSet.getString("gender");
                search_table[count][4] = resultSet.getInt("age");
                search_table[count][5] = resultSet.getInt("phone_number");
                search_table[count][6] = resultSet.getString("address");
                search_table[count][7] = resultSet.getString("email");
                search_table[count][8] = resultSet.getInt("level");
                count++;
            }
            if (count > 0) {
                jLabel.setText("查询成功");
                ADMINISTRATOR_WINDOW.jTable = new JTable(search_table, information);
            } else {
                jLabel.setText("查询失败");
            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
