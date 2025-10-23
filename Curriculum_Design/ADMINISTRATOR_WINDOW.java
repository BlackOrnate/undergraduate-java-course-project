package Curriculum_Design;


import javax.swing.*;
import java.awt.*;

public class ADMINISTRATOR_WINDOW extends JFrame {
    static JTable jTable;

    public ADMINISTRATOR_WINDOW(TOOLS tools) {
        super("ADMINISTRATOR_WINDOW");

        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 1));

        JPanel jPanel1 = new JPanel(new GridLayout(2, 9));
        JPanel jPanel2 = new JPanel(new FlowLayout());
        JScrollPane jScrollPane = new JScrollPane();
        JButton jButton_Add = new JButton("增");
        JButton jButton_Delete = new JButton("删");
        JButton jButton_Modify = new JButton("改");
        JButton jButton_Search = new JButton("细节查");
        JButton jButton_Fuzzy_query = new JButton("模糊查");
        JButton jButton_Return = new JButton("返回");
        JLabel jLabel = new JLabel("", JLabel.CENTER);
        JLabel jLabel_id = new JLabel("id");
        JLabel jLabel_password = new JLabel("password");
        JLabel jLabel_name = new JLabel("name");
        JLabel jLabel_gender = new JLabel("gender");
        JLabel jLabel_age = new JLabel("age");
        JLabel jLabel_phone_number = new JLabel("phone_number");
        JLabel jLabel_address = new JLabel("address");
        JLabel jLabel_email = new JLabel("email");
        JLabel jLabel_level = new JLabel("level");
        TextField textField_id = new TextField();
        TextField textField_password = new TextField();
        TextField textField_name = new TextField();
        TextField textField_gender = new TextField();
        TextField textField_age = new TextField();
        TextField textField_phone_number = new TextField();
        TextField textField_address = new TextField();
        TextField textField_email = new TextField();
        TextField textField_level = new TextField();


        jButton_Add.addActionListener(new TOOLS.WINDOW_JButton_LINSTENER(textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level, jScrollPane, jLabel, jButton_Add.getText(), "administrator_information", tools));
        jButton_Delete.addActionListener(new TOOLS.WINDOW_JButton_LINSTENER(textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level, jScrollPane, jLabel, jButton_Delete.getText(), "administrator_information", tools));
        jButton_Modify.addActionListener(new TOOLS.WINDOW_JButton_LINSTENER(textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level, jScrollPane, jLabel, jButton_Modify.getText(), "administrator_information", tools));
        jButton_Search.addActionListener(new TOOLS.WINDOW_JButton_LINSTENER(textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level, jScrollPane, jLabel, jButton_Search.getText(), "administrator_information", tools));
        jButton_Fuzzy_query.addActionListener(new TOOLS.WINDOW_JButton_LINSTENER(textField_id, textField_password, textField_name, textField_gender, textField_age, textField_phone_number, textField_address, textField_email, textField_level, jScrollPane, jLabel, jButton_Fuzzy_query.getText(), "administrator_information", tools));
        jButton_Return.addActionListener(new TOOLS.WINDOW_JButton_Back_LINSTENER(tools));


        jPanel1.add(jLabel_id);
        jPanel1.add(jLabel_password);
        jPanel1.add(jLabel_name);
        jPanel1.add(jLabel_gender);
        jPanel1.add(jLabel_age);
        jPanel1.add(jLabel_phone_number);
        jPanel1.add(jLabel_address);
        jPanel1.add(jLabel_email);
        jPanel1.add(jLabel_level);
        jPanel1.add(textField_id);
        jPanel1.add(textField_password);
        jPanel1.add(textField_name);
        jPanel1.add(textField_gender);
        jPanel1.add(textField_age);
        jPanel1.add(textField_phone_number);
        jPanel1.add(textField_address);
        jPanel1.add(textField_email);
        jPanel1.add(textField_level);
        jPanel2.add(jButton_Add, BorderLayout.WEST);
        jPanel2.add(jButton_Delete);
        jPanel2.add(jButton_Modify);
        jPanel2.add(jButton_Search);
        jPanel2.add(jButton_Fuzzy_query);
        jPanel2.add(jButton_Return);
        jScrollPane.getViewport().add(jTable);


        container.add(jPanel1);
        container.add(jLabel);
        container.add(jScrollPane);
        container.add(jPanel2);

        setBounds(600, 200, 700, 450);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void visible(TOOLS tools) {
        setVisible(tools.page.equals("ADMINISTRATOR_WINDOW"));
    }
}
