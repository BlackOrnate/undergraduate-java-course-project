package Curriculum_Design;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MENU extends JFrame {
    public MENU(TOOLS tools) {
        super("MENU");

        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 1));

        JPanel jPanel = new JPanel(new GridLayout(1, 2));

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        JLabel jLabel1 = new JLabel("1918180205\n������\nR��ѧ192", JLabel.CENTER);
        JLabel jLabel2 = new JLabel(ft.format(date), JLabel.CENTER);
        JButton jButton1 = new JButton("�û�����");
        JButton jButton2 = new JButton("����Ա����");
        JButton jButton3 = new JButton("�л�����Ա");

        jButton1.addActionListener(new TOOLS.MENU_JButton_User_Window_LINSTENER(tools));
        jButton2.addActionListener(new TOOLS.MENU_JButton_Administrator_Window_LINSTENER(tools));
        jButton3.addActionListener(new TOOLS.MENU_JButton_Back_LINSTENER(tools));


        jPanel.add(jLabel1);
        jPanel.add(jLabel2);

        container.add(jPanel);
        container.add(jButton1);
        container.add(jButton2);
        container.add(jButton3);


        setBounds(600, 200, 700, 450);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void visible(TOOLS tools) {
        setVisible(tools.page.equals("MENU"));
    }
}
