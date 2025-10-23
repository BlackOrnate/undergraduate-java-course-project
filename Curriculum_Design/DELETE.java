package Curriculum_Design;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DELETE {
    public DELETE(String table, int id, int phone_number, JLabel jLabel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql;


        try {
            SEARCH search = new SEARCH(table, id, phone_number, jLabel);
            connection = TOOLS.DBCP.getConnection();

            if (search.level_1 >= TOOLS.level) {
                // 删除数据
                sql = "delete from " + table + " where `id` = ? && `phone_number` = ?";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, phone_number);

                if (preparedStatement.executeUpdate() == 1) {
                    jLabel.setText("删除成功");
                } else {
                    jLabel.setText("id=" + id + " ,phone_number=" + phone_number + " ，不存在，不删除");
                }
            } else {
                jLabel.setText("管理员等级过低，无法删除");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TOOLS.DBCP.Release(connection, preparedStatement, null);
        }
    }
}
