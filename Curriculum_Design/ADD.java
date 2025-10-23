package Curriculum_Design;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ADD {
    public ADD(String table, String password, String name, String gender, int age, int phone_number, String address, String email, int level, JLabel jLabel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        String sql;

        // ��Ĵ�С��ȱʧ��
        int size = 0, line = 0;

        // ����Ѿ����������
        if (!new SEARCH(table, 0, phone_number, jLabel).exist) {
            try {
                connection = TOOLS.DBCP.getConnection();


                // ��ȡ����
                sql = "select count(*) from " + table;
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    size = resultSet.getInt(1);
                    TOOLS.anInt = size;
                }


                // ����ȱʧ����
                sql = "select `id` from " + table;
                preparedStatement = connection.prepareStatement(sql);

                resultSet = preparedStatement.executeQuery();
                for (int i = 1; i <= size; i++) {
                    int flag = 1;
                    while (resultSet.next()) {
                        line = resultSet.getInt(1);
                        if (i == line) {
                            flag = 0;
                            break;
                        }
                    }
                    if (flag == 1) {
                        line = i;
                        break;
                    }
                    if (i == size) {
                        line = size + 1;
                    }
                }


                if (level >= TOOLS.level){
                    // �������
                    sql = "insert into " + table + "(`id`, `password`, `name`, `gender`, `age`, `phone_number`, `address`, `email`, `level`) values (?,?,?,?,?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, line);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, name);
                    preparedStatement.setString(4, gender);
                    preparedStatement.setInt(5, age);
                    preparedStatement.setInt(6, phone_number);
                    preparedStatement.setString(7, address);
                    preparedStatement.setString(8, email);
                    preparedStatement.setInt(9, level);

                    if (preparedStatement.executeUpdate() == 1) {
                        jLabel.setText("phone_number=" + phone_number + " �������ڣ����");
                    }
                }else {
                    jLabel.setText("level=" + phone_number + " �����ڵ�ǰ����Ա�ȼ��������");
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                TOOLS.DBCP.Release(connection, preparedStatement, null);
            }
        } else {
            jLabel.setText("phone_number=" + phone_number + " ���Ѵ��ڣ������");
        }
    }
}
