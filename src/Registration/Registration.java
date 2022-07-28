package Registration;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//imported packages
import Login.Login;

public class Registration implements ActionListener {
    JLabel label_bg;
    JLabel label_bg2;
    JPanel box;
    JLabel heading;
    JLabel name;
    JTextField tname;
    JLabel dob;
    JTextField tdob;
    JLabel email;
    JTextField temail;
    JLabel phno1;
    JLabel phno2;
    JTextField tphno;
    JLabel gender;
    JFrame frame1;
    Choice cgender;
    JLabel address;
    JTextArea taddress;
    JLabel password;
    JPasswordField tpassword;
    JLabel cpassword1;
    JLabel cpassword2;
    JPasswordField tcpassword;
    JLabel txt;
    JButton back;
    JButton submit;
    JButton reset;
    JButton login;
    String bg_img_1;
    String bg_img_2;
    ImageIcon image;
    ImageIcon image2;
    String blank = "";

    String uname;
    String udob;
    String uemail;
    String uphno;
    String ugender;
    String uaddress;
    String upassword;
    int uphno_len;
    int upassword_len;
    int new_register;

    JLabel hint;
    JLabel branding;

    Pattern p;
    Matcher m;
    String regex;

    // DataBase Connection
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store";
    String username = "admin";
    String dpassword = "123123";
    Statement stmt;
    Connection con;
    PreparedStatement pst;
    String query;
    String shopQuery;
    String CSName;
    ResultSet rs;
    JLabel Shopname;

    public void databaseConnect() {
        try {

            con = DriverManager.getConnection(url, username, dpassword);
            stmt = con.createStatement();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void previous_details() {
        try {
            databaseConnect();
            shopQuery = "SELECT * FROM ShopDetails";
            rs = stmt.executeQuery(shopQuery);
            while (rs.next()) {
                CSName = rs.getString("Shop_name");

            }

            Shopname = new JLabel(CSName, JLabel.CENTER);
            Shopname.setFont(new Font("Verdana", Font.BOLD, 30));
            Shopname.setBounds(20, 50, 450, 30);
            Shopname.setForeground(new Color(24, 39, 101));
            label_bg.add(Shopname);

            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean isValidPassword(String password) {

        // Regex to check valid password.
        regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public boolean isValidEmail(String email) {

        // Regex to check valid password.
        regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        // Compile the ReGex
        p = Pattern.compile(regex);

        // If the email is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given email
        // and regular expression.
        m = p.matcher(email);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    public void Registration_Form() {
        // Heading
        heading = new JLabel("Enter Your Details");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setBounds(625, 50, 300, 30);
        label_bg.add(heading);

        // Left Column
        // Full Name
        name = new JLabel("Full Name");
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setBounds(570, 130, 180, 20);
        label_bg.add(name);

        // DOB
        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.BOLD, 18));
        dob.setBounds(615, 175, 80, 20);
        label_bg.add(dob);

        // Email
        email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.BOLD, 18));
        email.setBounds(600, 220, 80, 20);
        label_bg.add(email);

        // Phone Number
        phno1 = new JLabel("Mobile");
        phno1.setFont(new Font("Arial", Font.BOLD, 18));
        phno1.setBounds(600, 265, 180, 20);
        label_bg.add(phno1);
        phno2 = new JLabel("Number");
        phno2.setFont(new Font("Arial", Font.BOLD, 18));
        phno2.setBounds(590, 285, 180, 20);
        label_bg.add(phno2);

        // Gender
        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.BOLD, 18));
        gender.setBounds(592, 330, 75, 20);
        label_bg.add(gender);

        // Address
        address = new JLabel("Address");
        address.setFont(new Font("Arial", Font.BOLD, 18));
        address.setBounds(585, 375, 180, 20);
        label_bg.add(address);

        // Password
        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.BOLD, 18));
        password.setBounds(570, 440, 180, 20);
        label_bg.add(password);

        // Hint
        hint = new JLabel("*Enter atleast 8 Digits With Special Characters");
        hint.setBounds(670, 461, 300, 12);
        hint.setForeground(Color.red);
        label_bg.add(hint);

        // Confirm Password
        cpassword1 = new JLabel("Confirm");
        cpassword1.setFont(new Font("Arial", Font.BOLD, 18));
        cpassword1.setBounds(585, 485, 180, 20);
        label_bg.add(cpassword1);

        cpassword2 = new JLabel("Password");
        cpassword2.setFont(new Font("Arial", Font.BOLD, 18));
        cpassword2.setBounds(570, 505, 180, 20);
        label_bg.add(cpassword2);

        txt = new JLabel("Have an Existing Account?");
        txt.setFont(new Font("Arial", Font.BOLD, 20));
        txt.setBounds(45, 540, 300, 25);
        label_bg.add(txt);

        previous_details();

        // Right Column of TextFields

        // Full Name TestField
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 17));
        tname.setBounds(670, 130, 220, 20);
        label_bg.add(tname);

        // DOB
        tdob = new JTextField();
        tdob.setFont(new Font("Arial", Font.PLAIN, 17));
        tdob.setBounds(670, 175, 220, 20);
        label_bg.add(tdob);

        // Email TextField
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 17));
        temail.setBounds(670, 220, 220, 20);
        label_bg.add(temail);

        // Phone Number TextField
        tphno = new JTextField();
        tphno.setFont(new Font("Arial", Font.PLAIN, 17));
        tphno.setBounds(670, 275, 220, 20);
        label_bg.add(tphno);

        // Gender TextField
        cgender = new Choice();
        cgender.setFont(new Font("Arial", Font.PLAIN, 17));
        cgender.add("Male");
        cgender.add("Female");
        cgender.add("Other");
        cgender.setBounds(670, 330, 220, 20);
        label_bg.add(cgender);

        // Address TextArea
        taddress = new JTextArea();
        taddress.setFont(new Font("Arial", Font.PLAIN, 17));
        taddress.setBounds(670, 375, 220, 40);
        taddress.setLineWrap(true);
        label_bg.add(taddress);

        // Password TextField
        tpassword = new JPasswordField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 17));
        tpassword.setBounds(670, 440, 220, 20);
        tpassword.setEchoChar('*');
        label_bg.add(tpassword);

        // Confirm Password
        tcpassword = new JPasswordField();
        tcpassword.setFont(new Font("Arial", Font.PLAIN, 17));
        tcpassword.setBounds(670, 495, 220, 20);
        tcpassword.setEchoChar('*');
        label_bg.add(tcpassword);

        Buttons();
    }

    public void Buttons() {
 
        // Submit Button
        submit = new JButton("SUBMIT");
        submit.setBounds(570, 560, 130, 50);
        submit.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(submit);
        submit.addActionListener(this);

        // Reset Button
        reset = new JButton("RESET");
        reset.setBounds(760, 560, 130, 50);
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(reset);
        reset.addActionListener(this);

        // Login Button
        login = new JButton("Login");
        login.setBounds(45, 570, 100, 50);
        login.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(login);
        login.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            new Login();
            frame1.dispose();

        }

        else {
            uname = tname.getText();
            udob = tdob.getText();
            uemail = temail.getText();
            uphno = tphno.getText();
            ugender = cgender.getSelectedItem();
            uaddress = taddress.getText();
            upassword = tpassword.getText();

            uphno_len = uphno.length();
            upassword_len = upassword.length();

            if (e.getSource() == reset) {
                tname.setText(blank);
                tdob.setText(blank);
                temail.setText(blank);
                tphno.setText(blank);
                cgender.setName(blank);
                taddress.setText(blank);
                tpassword.setText(blank);
                tcpassword.setText(blank);
            } else if (uname.isEmpty() || uname.length() < 4) {
                JOptionPane.showMessageDialog(label_bg, "Field Full Name Is Inappropriate", "Error",
                        JOptionPane.ERROR_MESSAGE);

            } else if (udob.isEmpty()) {
                JOptionPane.showMessageDialog(label_bg, "Field DOB Can't be Empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if (!isValidEmail(uemail)) {
                JOptionPane.showMessageDialog(label_bg, "Enter a valid Email", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (uphno_len != 10) {
                JOptionPane.showMessageDialog(label_bg, "Enter a valid Mobile number", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (!isValidPassword(upassword)) {
                JOptionPane.showMessageDialog(label_bg,
                        "Enter a valid Password \n Password should atleast be of \n 8 Characters/Digits With Special Characters",
                        "Warning", JOptionPane.ERROR_MESSAGE);
            } else if (!upassword.equalsIgnoreCase(tcpassword.getText())) {
                JOptionPane.showMessageDialog(label_bg, "Password and Confirm Password Don't Match", "Warning",
                        JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == submit) {

                try {

                    con = DriverManager.getConnection(url, username, dpassword);
                    query = "Insert into Registration values ('" + uname + " ',' " + udob + "','" + uemail + "','"
                            + uphno + "','" + ugender + "','" + uaddress + "','" + upassword + "'); ";
                    stmt = con.createStatement();
                    new_register = stmt.executeUpdate(query);
                    if (new_register == 0) {
                        JOptionPane.showMessageDialog(null, "Account Already Exists", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(label_bg, "Hi " + uname + ", you are Successfully Registered");
                        tname.setText(blank);
                        tdob.setText(blank);
                        temail.setText(blank);
                        tphno.setText(blank);
                        cgender.setName(blank);
                        taddress.setText(blank);
                        tpassword.setText(blank);
                        tcpassword.setText(blank);
                    }
                    con.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Account Already Exists ", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        }

    }

    public Registration() {
        // Frame for Window
        frame1 = new JFrame("Resgistration");
        frame1.setBounds(250, 50, 1000, 700);

        // Background Image
        URL bg_img_1 = getClass().getClassLoader().getResource("Images/bg.jfif");

        image = new ImageIcon(bg_img_1);
        label_bg = new JLabel(image, JLabel.CENTER);
        frame1.add(label_bg);
        Registration_Form();

        URL bg_img_2 = getClass().getClassLoader().getResource("Images/bg.gif");

        image2 = new ImageIcon(bg_img_2);
        label_bg2 = new JLabel(image2);
        label_bg2.setBounds(45, 115, 400, 400);
        label_bg.add(label_bg2);

        // Panel For Form
        box = new JPanel();
        box.setBackground(new Color(0, 0, 0, 15));
        box.setBounds(500, 30, 450, 600);
        label_bg.add(box);

        // Brand Name
        branding = new JLabel("Powered By DUKANDAARI");
        branding.setBounds(800, 600, 200, 100);
        branding.setForeground(new Color(24, 39, 101));
        label_bg.add(branding);

        // Frame Components
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
    }

    public static void main(String args[]) {
        new Registration();
    }

}
