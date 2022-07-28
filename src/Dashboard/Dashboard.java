
package Dashboard;
// import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.net.URL;

//imported packages
import Shop_Details.Shop_Details;
import Customer_Details.Customer_Details;
import Product_Details.Product_Details;
import Edit_Profile.Edit_Profile;
import Login.Login;
import Bill_Generations.Bill_Generations;

public class Dashboard implements ActionListener {
    // back button icon
    // String Back_btn = "images/back_btn.png";
    // Icon btn_b = new ImageIcon(Back_btn);

    // background image
    URL url = getClass().getClassLoader().getResource("Images/bg.jfif");
    ImageIcon icon = new ImageIcon(url);

    // dashboard image
    URL url1 = getClass().getClassLoader().getResource("Images/bill.png");
    ImageIcon icon1 = new ImageIcon(url1);

    // Frame
    JFrame f = new JFrame("Dashboard");

    // Buttons
    JButton b_bill = new JButton("Generate Bill");
    JButton b_products = new JButton("Product Details");
    JButton b_customer = new JButton("Customer Details");
    JButton b_shop = new JButton("Shop Details");
    JButton b_edit = new JButton("Edit Profile");
    JButton b_logOut = new JButton("Log Out");
    // JButton b_back = new JButton(btn_b);

    // Labels
    JLabel imcon = new JLabel(icon, JLabel.CENTER);
    JLabel imcon1 = new JLabel(icon1, JLabel.CENTER);
    JLabel l_name = new JLabel("Name", JLabel.CENTER);
    JLabel l_email = new JLabel("Email", JLabel.CENTER);
    JLabel l_phone = new JLabel("Phone No.", JLabel.CENTER);
    JLabel l_branding = new JLabel("Powered By DUKANDAARI");
    JLabel l_shopName = new JLabel("Shop Name", JLabel.CENTER);

    // Panel
    JPanel cntnr = new JPanel();

    // Database variables
    // String DB_URL = "jdbc:mysql://localhost:3306/store";
    String DB_URL = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String USER = "admin";
    String PASS = "123123";
    String UID;
    String QUERY;

    public void Database() {
        try {
            QUERY = "select r.Name, r.Email, r.Mobile_Number, l.UID from Registration r, Login l where r.Email = l.UID ORDER BY l.SNO DESC LIMIT 1";
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                l_name.setText(rs.getString("r.Name"));
                l_email.setText(rs.getString("r.Email"));
                l_phone.setText(rs.getString("r.Mobile_Number"));
            }
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(f, e, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void Database2() {
        try {
            QUERY = "SELECT Shop_Name from ShopDetails";
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement(QUERY);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                l_shopName.setText(rs.getString("Shop_Name"));
            }
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(f, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Dashboard() {
        // frame
        f.setBounds(250, 50, 1000, 700);

        // Buttons

        // bill button
        b_bill.setBounds(700, 150, 200, 50);
        b_bill.setFont(new Font("Arial", Font.BOLD, 20));
        b_bill.addActionListener(this);
        f.add(b_bill);

        // products button
        b_products.setBounds(700, 250, 200, 50);
        b_products.setFont(new Font("Arial", Font.BOLD, 20));
        b_products.addActionListener(this);
        f.add(b_products);

        // customer button
        b_customer.setBounds(700, 350, 200, 50);
        b_customer.setFont(new Font("Arial", Font.BOLD, 20));
        b_customer.addActionListener(this);
        f.add(b_customer);

        // shop button
        b_shop.setBounds(700, 450, 200, 50);
        b_shop.setFont(new Font("Arial", Font.BOLD, 20));
        b_shop.addActionListener(this);
        f.add(b_shop);

        // edit button
        b_edit.setBounds(150, 580, 150, 50);
        b_edit.setFont(new Font("Arial", Font.PLAIN, 20));
        b_edit.addActionListener(this);
        f.add(b_edit);

        // logout button
        b_logOut.setBounds(400, 580, 150, 50);
        b_logOut.setFont(new Font("Arial", Font.PLAIN, 20));
        b_logOut.addActionListener(this);
        f.add(b_logOut);

        // datbase connectivity function called
        Database();
        Database2();

        // Labels

        // shop name

        l_shopName.setBounds(0, 0, 1000, 100);
        l_shopName.setFont(new Font("Verdana", Font.BOLD, 30));
        l_shopName.setForeground(new Color(24, 39, 101));
        f.add(l_shopName);

        // name
        l_name.setBounds(100, 370, 500, 50);
        l_name.setFont(new Font("Arial", Font.PLAIN, 30));
        f.add(l_name);

        // email
        l_email.setBounds(100, 420, 500, 50);
        l_email.setFont(new Font("Arial", Font.PLAIN, 30));
        f.add(l_email);

        // phone no.
        l_phone.setBounds(100, 470, 500, 50);
        l_phone.setFont(new Font("Arial", Font.PLAIN, 30));
        f.add(l_phone);

        // branding
        l_branding.setBounds(800, 600, 200, 100);
        // l_branding.setForeground(new Color(100, 100, 100));
        l_branding.setForeground(new Color(24, 39, 101));

        f.add(l_branding);

        // opaque pannel
        cntnr.setBounds(100, 100, 500, 450);
        cntnr.setBackground(new Color(0, 0, 0, 10));
        f.add(cntnr);

        // dashboard image
        imcon1.setBounds(100, 100, 500, 250);
        f.add(imcon1);

        // background image
        imcon.setBounds(0, 0, 1000, 670);
        f.add(imcon);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setResizable(false);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b_bill) {
            new Bill_Generations();
            f.dispose();

        } else if (e.getSource() == b_customer) {
            new Customer_Details();
            f.dispose();
        } else if (e.getSource() == b_edit) {
            new Edit_Profile();
            f.dispose();
        } else if (e.getSource() == b_logOut) {
            new Login();
            f.dispose();
        } else if (e.getSource() == b_products) {
            new Product_Details();
            f.dispose();
        } else if (e.getSource() == b_shop) {
            new Shop_Details();
            f.dispose();
        }

    }

    public static void main(String[] args) {
        new Dashboard();
    }

}