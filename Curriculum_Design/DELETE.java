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
                // ɾ������
                sql = "delete from " + table + " where `id` = ? && `phone_number` = ?";
                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, id);
                preparedStatement.setInt(2, phone_number);

                if (preparedStatement.executeUpdate() == 1) {
                    jLabel.setText("ɾ���ɹ�");
                } else {
                    jLabel.setText("id=" + id + " ,phone_number=" + phone_number + " �������ڣ���ɾ��");
                }
            } else {
                jLabel.setText("����Ա�ȼ����ͣ��޷�ɾ��");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TOOLS.DBCP.Release(connection, preparedStatement, null);
        }
    }
}
