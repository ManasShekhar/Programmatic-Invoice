package Login;

// link register button 122
// correct password getText()

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//imported packages
import Dashboard.Dashboard;
import Registration.Registration;

public class Login {
    JFrame frame;
    ImageIcon imgIconBg, imgIconLogo;
    JLabel lbg, lTitle, lUid, lPw, lLogo, lTagline, lBranding, lDivision, lTitleUnderline;
    JButton bLogin, bToRegister;
    JTextField tUid;
    JPasswordField tPw;

    // String imageBg = "/Images/login.png";
    URL imageBg = getClass().getClassLoader().getResource("Images/login.png");
    String imageLogo = "Images/logo.png";
    // URL imageLogo = getClass().getClassLoader().getResource( "Images/logo.png" );

    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String userName = "admin";
    String password = "123123";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Login() {
        layout();
    }

    public void layout() {
        frame = new JFrame("Login");

        // background image
        imgIconBg = new ImageIcon(imageBg);
        lbg = new JLabel(imgIconBg, JLabel.CENTER);
        frame.add(lbg);

        // logo
        imgIconLogo = new ImageIcon(imageLogo);
        lLogo = new JLabel(imgIconLogo, JLabel.CENTER);
        lLogo.setBounds(650, 510, 50, 50);
        lbg.add(lLogo);

        // tagline
        lTagline = new JLabel("A few clicks is all it takes!");
        lTagline.setBounds(715, 530, 300, 13);
        lTagline.setFont(new Font("Arial", Font.BOLD, 15));
        lbg.add(lTagline);

        // title label
        lTitle = new JLabel("Enter your details");
        lTitle.setBounds(650, 120, 400, 25);
        lTitle.setFont(new Font("Verdana", Font.BOLD, 25));
        lbg.add(lTitle);

        // title underline
        lTitleUnderline = new JLabel();
        lTitleUnderline.setBounds(620, 160, 300, 1);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        lTitleUnderline.setBorder(blackline);
        lbg.add(lTitleUnderline);

        // uid label
        lUid = new JLabel("E-Mail");
        lUid.setBounds(740, 200, 100, 20);
        lUid.setFont(new Font("Arial", Font.BOLD, 18));
        lbg.add(lUid);
        // uid textfield
        tUid = new JTextField();
        tUid.setBounds(630, 230, 280, 30);
        tUid.setFont(new Font("Arial", Font.PLAIN, 18));
        lbg.add(tUid);

        // password label
        lPw = new JLabel("Password");
        lPw.setBounds(730, 280, 105, 20);
        lPw.setFont(new Font("Arial", Font.BOLD, 18));
        lbg.add(lPw);
        // password textfield
        tPw = new JPasswordField();
        tPw.setBounds(630, 310, 280, 30);
        tPw.setFont(new Font("Arial", Font.PLAIN, 18));
        tPw.setEchoChar('*');
        lbg.add(tPw);

        // login button
        bLogin = new JButton("Log In");
        bLogin.setBounds(630, 370, 280, 30);
        bLogin.setFont(new Font("Arial", Font.BOLD, 18));
        lbg.add(bLogin);
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        // division line between login and register button
        lDivision = new JLabel();
        lDivision.setBounds(620, 430, 300, 2);
        lDivision.setBorder(blackline);
        lbg.add(lDivision);

        // register button
        bToRegister = new JButton("Create New Account");
        bToRegister.setBounds(630, 460, 280, 30);
        bToRegister.setFont(new Font("Arial", Font.BOLD, 18));
        lbg.add(bToRegister);
        bToRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                new Registration();
                frame.dispose();
            }
        });

        // branding
        lBranding = new JLabel("Powered By DUKANDAARI");
        lBranding.setBounds(800, 600, 200, 100);
        lBranding.setForeground(new Color(255, 255, 255));
        lBranding.setFont(new Font("Verdana", Font.BOLD, 10));
        lbg.add(lBranding);

        // frame specifications
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(250, 50, 1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void bLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String EnteredEmail = tUid.getText();
        String EnteredPassword = tPw.getText();
        String LoginEmail = "";
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
            String sql = "SELECT * FROM Registration WHERE Email = ? AND Password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, EnteredEmail);
            ps.setString(2, EnteredPassword);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                LoginEmail = rs.getString("Email");
                String tempSqlInsert = "INSERT INTO Login(UID) values (?)";
                ps = con.prepareStatement(tempSqlInsert);
                ps.setString(1, LoginEmail);
                ps.executeUpdate();
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "Incorrect login details. No such record found.", "Error",
                        JOptionPane.WARNING_MESSAGE);
                tUid.setText("");
                tPw.setText("");
                tUid.requestFocus();
            } else {
                tUid.setText("");
                tPw.setText("");
                tUid.requestFocus();
                // to next page
                // new cust();
                new Dashboard();
                frame.dispose();
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new Login();
    }
}
