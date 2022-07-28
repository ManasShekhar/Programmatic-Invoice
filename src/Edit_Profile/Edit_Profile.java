package Edit_Profile;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.ResultSet;
import javax.swing.Icon;

//packages imported 
import Dashboard.Dashboard;

//Class Edit_Profile
public class Edit_Profile implements ActionListener {

    // frame , label , textfield , choice
    JFrame frame1;
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
    Choice cgender;
    JLabel address;
    JTextArea taddress;
    JLabel password;
    JPasswordField tpassword;
    JLabel cpassword1;
    JLabel cpassword2;
    JPasswordField tcpassword;
    JLabel branding;
    JLabel hint1;
    JLabel hint;

    // Buttons
    JButton back;
    JButton update;
    JButton reset;

    // Image and Icon
    String bg_img_1;
    String bg_img_2;
    ImageIcon image;
    ImageIcon image2;
    String Back_btn;
    Icon btn_b;

    // String to reset the Data
    String blank = "";

    // Employee Details
    String uname;
    String udob;
    String uemail;
    String uphno;
    String ugender;
    String uaddress;
    String upassword;
    int uphno_len;
    int upassword_len;

    // Shop Details
    JLabel Shopname;
    String CSName;

    // Checking
    Pattern p;
    Matcher m;
    String regex;

    // DataBase Connection
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String username = "admin";
    String dpassword = "123123";
    String query;
    String shopQuery;
    String employeeQuery;
    Statement stmt;
    ResultSet rs;
    Connection con;
    PreparedStatement pst;

    // Database Connection
    public void databaseConnect() {
        try {

            con = DriverManager.getConnection(url, username, dpassword);
            stmt = con.createStatement();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fetching Employee Details
    public void employee_details() {
        try {

            databaseConnect();
            query = "SELECT r.Name, r.DOB, r.Email, r.Mobile_Number, r.Gender, r.Address, r.Password FROM Registration r, Login l WHERE  r.Email = l.UID;";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                uname = rs.getString("Name");
                udob = rs.getString("DOB");
                uemail = rs.getString("Email");
                uphno = rs.getString("Mobile_Number");
                ugender = rs.getString("Gender");
                uaddress = rs.getString("Address");
                upassword = rs.getString("Password");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fetching Shop Details
    public void previos_details() {
        try {
            // con = DriverManager.getConnection(url,username,dpassword);
            // stmt = con.createStatement();
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

    // Registration Form
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

        // Confirm Password
        cpassword1 = new JLabel("Confirm");
        cpassword1.setFont(new Font("Arial", Font.BOLD, 18));
        cpassword1.setBounds(585, 485, 180, 20);
        label_bg.add(cpassword1);

        cpassword2 = new JLabel("Password");
        cpassword2.setFont(new Font("Arial", Font.BOLD, 18));
        cpassword2.setBounds(570, 505, 180, 20);
        label_bg.add(cpassword2);

        hint1 = new JLabel("*Email Can't Be Changed");
        hint1.setFont(new Font("Arial", Font.BOLD, 12));
        hint1.setBounds(670, 240, 300, 20);
        hint1.setForeground(Color.red);
        label_bg.add(hint1);

        // Calling function for Employee Details
        employee_details();

        // Calling function for Shop Details
        previos_details();

        // Right Column of TextFields

        // Full Name TestField
        tname = new JTextField(uname);
        tname.setFont(new Font("Arial", Font.PLAIN, 17));
        tname.setBounds(670, 130, 220, 20);
        label_bg.add(tname);

        // DOB
        tdob = new JTextField(udob);
        tdob.setFont(new Font("Arial", Font.PLAIN, 17));
        tdob.setBounds(670, 175, 220, 20);
        label_bg.add(tdob);

        // Email TextField
        temail = new JTextField(uemail);
        temail.setFont(new Font("Arial", Font.PLAIN, 17));
        temail.setBounds(670, 220, 220, 20);
        label_bg.add(temail);
        temail.setEditable(false);

        // Phone Number TextField
        tphno = new JTextField(uphno);
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
        taddress = new JTextArea(uaddress);
        taddress.setFont(new Font("Arial", Font.PLAIN, 17));
        taddress.setBounds(670, 375, 220, 40);
        taddress.setLineWrap(true);
        label_bg.add(taddress);

        // Password TextField
        tpassword = new JPasswordField(upassword);
        tpassword.setFont(new Font("Arial", Font.PLAIN, 17));
        tpassword.setBounds(670, 440, 220, 20);
        tpassword.setEchoChar('*');
        label_bg.add(tpassword);

        // Hint
        hint = new JLabel("*Enter atleast 8 Digits With Special Characters");
        hint.setBounds(670, 461, 300, 12);
        hint.setForeground(Color.red);
        label_bg.add(hint);

        // Confirm Password
        tcpassword = new JPasswordField(upassword);
        tcpassword.setFont(new Font("Arial", Font.PLAIN, 17));
        tcpassword.setBounds(670, 495, 220, 20);
        tcpassword.setEchoChar('*');
        label_bg.add(tcpassword);

        // Calling Buttons Function
        Buttons();
    }

    // Functions for Buttons
    public void Buttons() {
        // Back Button
        URL Back_btn = getClass().getClassLoader().getResource("Images/back_btn.png");
        Icon btn_b = new ImageIcon(Back_btn);
        back = new JButton(btn_b);
        back.setBounds(5, 5, 36, 36);
        back.setBackground(new Color(0, 187, 255));
        back.setBorder(null);
        label_bg.add(back);
        back.addActionListener(this);

        // Update Button
        update = new JButton("Update");
        update.setBounds(600, 560, 110, 50);
        update.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(update);
        update.addActionListener(this);

        // Reset Button
        reset = new JButton("Reset");
        reset.setBounds(750, 560, 100, 50);
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(reset);
        reset.addActionListener(this);
    }

    // Calling action performed on buttons
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Dashboard();
            frame1.dispose();
        } else {
            uname = tname.getText();
            udob = tdob.getText();
            uemail = temail.getText();
            uphno = tphno.getText();
            ugender = cgender.getSelectedItem();
            uaddress = taddress.getText();
            upassword = tpassword.getText();

            uphno_len = uphno.length();
            upassword_len = upassword.length();

            if (uphno_len != 10) {
                JOptionPane.showMessageDialog(label_bg, "Enter a valid Mobile number", "Warning",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!isValidPassword(upassword)) {
                JOptionPane.showMessageDialog(label_bg,
                        "Enter a valid Password \n Password should atleast be of \n 8 Characters/Digits", "Warning",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!upassword.equalsIgnoreCase(tcpassword.getText())) {
                JOptionPane.showMessageDialog(label_bg, "Password and Confirm Password Don't Match", "Warning",
                        JOptionPane.ERROR_MESSAGE);
            } else if (e.getSource() == update) {
                try {
                    databaseConnect();
                    employeeQuery = "UPDATE Registration SET Name=?,DOB=?,Mobile_Number=?,Gender=?,Address=?,Password=? WHERE Email=?;";
                    pst = con.prepareStatement(employeeQuery);
                    pst.setString(1, uname);
                    pst.setString(2, udob);
                    pst.setString(3, uphno);
                    pst.setString(4, ugender);
                    pst.setString(5, uaddress);
                    pst.setString(6, upassword);
                    pst.setString(7, uemail);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(label_bg, "Hi " + uname + ", your Profile has been Updated");
                    con.close();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == reset) {
//                 tname.setText(blank);
//                 tdob.setText(blank);
//                 temail.setText(blank);
                tphno.setText(blank);
                cgender.setName(blank);
                taddress.setText(blank);
                tpassword.setText(blank);
                tcpassword.setText(blank);
            }

        }

    }

    // Constructor
    public Edit_Profile() {
        // Frame for Window
        frame1 = new JFrame("Edit Profile");
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
        label_bg2.setBounds(45, 120, 400, 400);
        label_bg.add(label_bg2);

        // Panel For Form
        box = new JPanel();
        box.setBackground(new Color(0, 0, 0, 15));
        box.setBounds(500, 30, 450, 600);
        label_bg.add(box);

        // Brand Name
        branding = new JLabel("Powered By DUKANDAARI");
        branding.setBounds(800, 600, 200, 100);
        branding.setForeground(new Color(100, 100, 100));
        label_bg.add(branding);
        branding.setForeground(new Color(24, 39, 101));

        // Frame Components
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
    }

    public static void main(String args[]) {
        new Edit_Profile();
    }

}
