package Shop_Details;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.net.URL;

//packages imported
import Dashboard.Dashboard;

public class Shop_Details implements ActionListener {
    // frame, label, textfield, textarea, textpane

    JLabel label_bg;
    JPanel box;
    JFrame frame1;
    JLabel heading;
    JLabel oname;
    JTextField otname;
    JLabel shop_name;
    JTextField shop_tname;
    JLabel address;
    JTextArea taddress;
    JLabel phno1;
    JTextField tphno;
    JLabel email;
    JTextField temail;
    JTextPane txtarea;
    JLabel branding;

    // Shop Details
    String CName;
    String CPhno;
    String CSName;
    String CEmail;
    String CAddress;
    int CPhno_len;
    String blank = "";

    // Buttons
    JButton check;
    JButton reset;
    JButton update;
    JButton back;

    // Image and Icons
    String bg_img_1;
    ImageIcon image;
    String Back_btn;
    Icon btn_b;

    // DataBase Connection
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String username = "admin";
    String dpassword = "123123";
    String shopQuery;
    String updateQuery;
    Statement stmt;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void database_connection() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, dpassword);
            stmt = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database not connected", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    // Origial Shop Details
    public void previos_details() {
        try {
            // con = DriverManager.getConnection(url,username,dpassword);
            // stmt = con.createStatement();
            database_connection();
            shopQuery = "SELECT * FROM ShopDetails";
            rs = stmt.executeQuery(shopQuery);
            while (rs.next()) {

                CName = rs.getString("Owner_Name");
                CSName = rs.getString("Shop_name");
                CAddress = rs.getString("Shop_Address");
                CPhno = rs.getString("Phone_Number");
                CEmail = rs.getString("Email");
            }
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Constructor
    public Shop_Details() {
        frame1 = new JFrame("Shop Details");
        frame1.setBounds(250, 50, 1000, 700);

        // Background Image
        URL bg_img_1 = getClass().getClassLoader().getResource("Images/bg.jfif");
        image = new ImageIcon(bg_img_1);
        label_bg = new JLabel(image, JLabel.CENTER);
        frame1.add(label_bg);

        // Brand Name
        branding = new JLabel("Powered By DUKANDAARI");
        branding.setBounds(800, 600, 200, 100);
        branding.setForeground(new Color(24, 39, 101));

        label_bg.add(branding);

        Form();

        // Transparent Box
        box = new JPanel();
        box.setBackground(new Color(0, 0, 0, 15));
        box.setBounds(50, 50, 880, 300);
        label_bg.add(box);

        // TextPane for Showing the Details
        txtarea = new JTextPane();
        txtarea.setBounds(50, 375, 880, 175);
        txtarea.setFont(new Font("Arial", Font.BOLD, 20));
        StyledDocument doc = txtarea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
        txtarea.setBorder(border);
        label_bg.add(txtarea);
        txtarea.setText("Check The Displayed Details\n\nShop Name - " + CSName + "\nOwner Name - " + CName
                + "\nPhnone Number -  " + CPhno + "\nEmail - " + CEmail + "\nAddress - " + CAddress);

        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);
    }

    public void Form() {
        // Heading
        heading = new JLabel("Enter Shop Details");
        heading.setFont(new Font("Arial", Font.BOLD, 25));
        heading.setBounds(375, 70, 300, 30);
        label_bg.add(heading);

        previos_details();

        // Shop Owner Name
        oname = new JLabel("Owner Name");
        oname.setFont(new Font("Arial", Font.BOLD, 18));
        oname.setBounds(100, 150, 180, 20);
        label_bg.add(oname);

        // Shop Owner Name Text Field
        otname = new JTextField(CName);
        otname.setFont(new Font("Arial", Font.PLAIN, 17));
        otname.setBounds(230, 150, 220, 20);
        label_bg.add(otname);

        // Shop Phone Number
        phno1 = new JLabel("Mobile Number");
        phno1.setFont(new Font("Arial", Font.BOLD, 18));
        phno1.setBounds(500, 150, 180, 20);
        label_bg.add(phno1);

        // Shop Phone Number Text Field
        tphno = new JTextField(CPhno);
        tphno.setFont(new Font("Arial", Font.PLAIN, 17));
        tphno.setBounds(650, 150, 220, 20);
        label_bg.add(tphno);

        // Shop Details
        shop_name = new JLabel("Shop Name");
        shop_name.setFont(new Font("Arial", Font.BOLD, 18));
        shop_name.setBounds(100, 200, 180, 20);
        label_bg.add(shop_name);

        // Shop Name Text Field
        shop_tname = new JTextField(CSName);
        shop_tname.setFont(new Font("Arial", Font.PLAIN, 17));
        shop_tname.setBounds(230, 200, 220, 20);
        label_bg.add(shop_tname);

        // Shop Official Mail
        email = new JLabel("Official Mail");
        email.setFont(new Font("Arial", Font.BOLD, 18));
        email.setBounds(500, 200, 180, 20);
        label_bg.add(email);

        // Shop Official Mail Text Field
        temail = new JTextField(CEmail);
        temail.setFont(new Font("Arial", Font.PLAIN, 17));
        temail.setBounds(650, 200, 220, 20);
        label_bg.add(temail);

        // Address
        address = new JLabel("Address");
        address.setFont(new Font("Arial", Font.BOLD, 18));
        address.setBounds(100, 250, 180, 20);
        label_bg.add(address);

        // Address TextArea
        taddress = new JTextArea(CAddress);
        taddress.setFont(new Font("Arial", Font.PLAIN, 18));
        taddress.setBounds(230, 250, 640, 60);
        label_bg.add(taddress);

        Buttons();
    }

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

        // Check
        check = new JButton("Check");
        check.setBounds(260, 580, 100, 50);
        check.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(check);
        check.addActionListener(this);

        // Reset Button
        reset = new JButton("Reset");
        reset.setBounds(430, 580, 100, 50);
        reset.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(reset);
        reset.addActionListener(this);

        // Submit Button
        update = new JButton("Update");
        update.setBounds(600, 580, 120, 50);
        update.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(update);
        update.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Dashboard();
            frame1.dispose();
        } else {
            CSName = shop_tname.getText();
            CName = otname.getText();
            CPhno = tphno.getText();
            CEmail = temail.getText();
            CAddress = taddress.getText();
            CPhno_len = CPhno.length();
            if (CSName.isEmpty()) {
                JOptionPane.showMessageDialog(label_bg, "Field Shop Name Can't be Empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (CName.isEmpty()) {
                JOptionPane.showMessageDialog(label_bg, "Field ShopOwner Name Can't be Empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else if (CPhno_len != 10) {
                JOptionPane.showMessageDialog(label_bg, "Enter a valid Mobile number", "Warning",
                        JOptionPane.ERROR_MESSAGE);
            }

            else if (e.getSource() == check) {
                txtarea.setText("Check The Displayed Details\n\nShop Name - " + CSName + "\nOwner Name - " + CName
                        + "\nPhnone Number -  " + CPhno + "\nEmail - " + CEmail + "\nAddress - " + CAddress);
            }

            else if (e.getSource() == reset) {
                otname.setText(blank);
                shop_tname.setText(blank);
                temail.setText(blank);
                tphno.setText(blank);
                taddress.setText(blank);
                txtarea.setText(blank);
                JOptionPane.showMessageDialog(label_bg, "You have reset the Details");

            }

            else if (e.getSource() == update) {
                otname.setText(blank);
                shop_tname.setText(blank);
                temail.setText(blank);
                tphno.setText(blank);
                taddress.setText(blank);

                try {
                    // con = DriverManager.getConnection(url,username,dpassword);
                    database_connection();
                    updateQuery = "UPDATE ShopDetails SET Shop_Name =?, Owner_Name =?, Phone_Number=?, Email=?, Shop_Address =? WHERE ID =?;";
                    pst = con.prepareStatement(updateQuery);
                    pst.setString(1, CSName);
                    pst.setString(2, CName);
                    pst.setString(3, CPhno);
                    pst.setString(4, CEmail);
                    pst.setString(5, CAddress);
                    pst.setInt(6, 1);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(label_bg,
                            "Mr " + CName + ", your Shop Details are Successfully Updated");
                    txtarea.setText("Check The Displayed Details\n\nShop Name - " + CSName + "\nOwner Name - " + CName
                            + "\nPhnone Number -  " + CPhno + "\nEmail - " + CEmail + "\nAddress - " + CAddress);

                    con.close();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame1, ex, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    public static void main(String args[]) {
        new Shop_Details();
    }

}