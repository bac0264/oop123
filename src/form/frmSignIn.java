package form;

/**
 * Created by Bac on 11/13/2017.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class frmSignIn {
    // todo display
    JFrame main;
    JTextField txtUser;
    JPasswordField txtPass;
    private String url = "jdbc:mysql://localhost:3306/smanagement";
    private String username = "root";
    private String password = "";
    Connection conn;
    Statement stmt;
    ResultSet rs;
    public frmSignIn() {
        //todo connect db
        try {
            conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        //todo Jframe
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width =  screenSize.getWidth();
        double height = screenSize.getHeight();
        main = new JFrame(  "Sign in"); // Khung
        main.setBounds((int)width/2-170,(int)height/2-250,500,350);
        main.setResizable(false);
        main.setLayout(null);
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel labelMain = new JLabel("Student Management");
        labelMain.setFont(new Font("Courier New",Font.BOLD,25));
        labelMain.setBounds(130,20,400,50);
        main.add(labelMain);
        //todo JPanel
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(""));
        panel.setBounds(130,80,250,90);
        panel.setLayout(null);
        //todo Label of User and Passw
        JLabel labelUser = new JLabel("User: ");
        labelUser.setBounds(10,10,150,20);
        JLabel labelPass = new JLabel("Password:");
        labelPass.setBounds(10,40,150,20);
        panel.add(labelUser);
        panel.add(labelPass);
        //todo input Keybord
        txtUser = new JTextField();
        txtUser.setBounds(90,10,130,25);
        txtPass = new JPasswordField();
        txtPass.setBounds(90,40,130,25);
        panel.add(txtUser);
        panel.add(txtPass);
        //todo Button
        JButton btnSignIn = new JButton("Sign In");
        btnSignIn.setBounds(130, 210, 100,40);
        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(280, 210, 100,40);
        main.add(btnExit);
        main.add(btnSignIn);
        main.add(panel);

        //todo catch event SignIn
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn();
                txtUser.setText(null);
                txtPass.setText(null);
            }
        });
        //todo catch event Exit
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //todo catch event Enter to signin
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SignIn();
                    txtUser.setText(null);
                    txtPass.setText(null);
                }
            }
        });
    }
    public void SignIn(){
        //todo query sql
        String idUser = txtUser.getText().trim();
        String pasUser = txtPass.getText().trim();
        String sql = "select * from acountmanagement where id ='"+idUser+"' and pass ='"+pasUser+"'";

        int count = 0;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) count++;
            if (count > 0){
                JOptionPane.showMessageDialog(null, "Sucessed","",JOptionPane.INFORMATION_MESSAGE);
            }
            else  JOptionPane.showMessageDialog(null, "Failed","",JOptionPane.INFORMATION_MESSAGE);
            conn.close();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error","",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    frmSignIn frame = new frmSignIn();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
