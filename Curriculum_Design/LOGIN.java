package Curriculum_Design;

import javax.swing.*;
import java.awt.*;

public class LOGIN extends JFrame {
    public LOGIN(TOOLS tools) {
        super("LOGIN");

        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 1));

        JPanel jPanel1 = new JPanel(new GridLayout(1, 2));
        JPanel jPanel2 = new JPanel(new GridLayout(1, 2));
        JPanel jPanel3 = new JPanel(new GridLayout(1, 4));
        JButton jButton1 = new JButton("看不清");
        JButton jButton2 = new JButton("登录");
        JLabel jLabel1 = new JLabel("用户名: ", JLabel.CENTER);
        JLabel jLabel2 = new JLabel("密码: ", JLabel.CENTER);
        JLabel jLabel3 = new JLabel("验证码: ", JLabel.CENTER);
        JLabel jLabel4 = new JLabel("1234", JLabel.CENTER);
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        JPasswordField jPasswordField = new JPasswordField();


        textField1.addActionListener(new TOOLS.TestFiled_LISTENER());
        textField2.addActionListener(new TOOLS.TestFiled_LISTENER());
        jPasswordField.addActionListener(new TOOLS.JPasswordField_LISTENER());
        jButton2.addActionListener(new TOOLS.LOGIN_JButton_login_LISTENER(textField1, jPasswordField, textField2, jLabel4, tools, jButton2));
        jButton1.addActionListener(new TOOLS.LOGIN_JButton_verification_code_LISTENER(jLabel4));


        jPanel1.add(jLabel1);
        jPanel1.add(textField1);
        jPanel2.add(jLabel2);
        jPanel2.add(jPasswordField);
        jPanel3.add(jLabel3);
        jPanel3.add(textField2);
        jPanel3.add(jLabel4);
        jPanel3.add(jButton1);


        container.add(jPanel1);
        container.add(jPanel2);
        container.add(jPanel3);
        container.add(jButton2);

        setBounds(600, 200, 700, 450);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void visible(TOOLS tools) {
        setVisible(tools.page.equals("LOGIN"));
    }
}
