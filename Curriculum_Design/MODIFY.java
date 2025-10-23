package Curriculum_Design;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MODIFY {
    public MODIFY(String table, int id, String password, String name, String gender, int age, int phone_number, String address, String email, int level, JLabel jLabel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql;

        if (new SEARCH(table, id, phone_number, jLabel).exist) {
            try {
                connection = TOOLS.DBCP.getConnection();

                if (level >= TOOLS.level){
                    // 更新数据
                    sql = "update " + table + " set `password` = ?,`name` = ? , `gender` = ?, `age` = ?, `phone_number` = ?, `address` = ?, `email` = ?, `level` = ? where `id` = ?";
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setString(1, password);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, gender);
                    preparedStatement.setInt(4, age);
                    preparedStatement.setInt(5, phone_number);
                    preparedStatement.setString(6, address);
                    preparedStatement.setString(7, email);
                    preparedStatement.setInt(8, level);
                    preparedStatement.setInt(9, id);

                    if (preparedStatement.executeUpdate() == 1) {
                        jLabel.setText("id=" + id + "修改成功");
                    }
                }else {
                    jLabel.setText("修改目标不能高于当前管理员等级");
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                TOOLS.DBCP.Release(connection, preparedStatement, null);
            }
        }
    }
}
