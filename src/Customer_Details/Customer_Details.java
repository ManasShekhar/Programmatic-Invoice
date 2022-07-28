package Customer_Details;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.net.URL;

//packages imported 
import Dashboard.Dashboard;
import Customer_Bill_Info.Customer_Bill_Info;

public class Customer_Details {
    // common variables
    JFrame fCustomer; // f2
    ImageIcon imgIcon; // i2
    JLabel lBg, lName, lBill, lTitle, lShopName, lBranding; // l2
    JButton bSpecificName, bSpecificBill, bAll, bBack;
    JTextField tSearchName, tSearchBill;
    DefaultTableModel custModel; // model1
    JTable custTable;
    JScrollPane custScroll;
    JPanel pbg;

    URL image = getClass().getClassLoader().getResource("Images/bg.jfif");
    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String userName = "admin";
    String password = "123123";
    String[] columnNames = { "Name", "Phone No", "Email", "Address", "Bill Number", "Bill Amount", "Employee",
            "Date Time" };
    String shopName = "";
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Customer_Details() {
        shopDetails();
        layout();
        showAllRecords();
    }

    public void layout() {
        fCustomer = new JFrame("Customer and Bill Details");

        // background image
        imgIcon = new ImageIcon(image);
        lBg = new JLabel(imgIcon, JLabel.CENTER);
        lBg.setBounds(0, 0, 1020, 700);
        lBg.setBackground(Color.BLACK);
        lBg.setLayout(null);
        fCustomer.add(lBg);

        // shop name label
        lShopName = new JLabel(shopName, JLabel.CENTER);
        lShopName.setBounds(50, 30, 900, 25);
        lShopName.setFont(new Font("Verdana", Font.BOLD, 25));
        lShopName.setForeground(new Color(24, 39, 101));
        lBg.add(lShopName);

        // panel to cover details
        pbg = new JPanel();
        pbg.setBounds(15, 130, 975, 120);
        pbg.setBackground(new Color(0, 0, 0, 15));
        lBg.add(pbg);

        // title label
        lTitle = new JLabel("Customer and Bill Details");
        lTitle.setBounds(360, 90, 280, 25);
        lTitle.setFont(new Font("Arial", Font.PLAIN, 25));
        lBg.add(lTitle);

        // back button
        URL Back_btn = getClass().getClassLoader().getResource("Images/back_btn.png");
        Icon backBtn = new ImageIcon(Back_btn);
        bBack = new JButton(backBtn);
        bBack.setBounds(5, 5, 36, 36);
        bBack.setBackground(new Color(0, 187, 255));
        bBack.setBorder(null);
        lBg.add(bBack);
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Dashboard();
                fCustomer.dispose();
            }
        });

        // search name label, text box and button
        lName = new JLabel("Enter Customer Name");
        lName.setBounds(220, 150, 300, 20);
        lName.setFont(new Font("Arial", Font.BOLD, 15));
        lBg.add(lName);
        tSearchName = new JTextField();
        tSearchName.setBounds(400, 150, 140, 25);
        tSearchName.setFont(new Font("Arial", Font.PLAIN, 14));
        lBg.add(tSearchName);
        bSpecificName = new JButton("Search Name");
        bSpecificName.setBounds(560, 150, 130, 25);
        bSpecificName.setFont(new Font("Arial", Font.BOLD, 14));
        lBg.add(bSpecificName);
        bSpecificName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSpecificName(evt);
            }
        });

        // search bill label, text box and button
        lBill = new JLabel("Enter Bill Number");
        lBill.setBounds(220, 200, 300, 20);
        lBill.setFont(new Font("Arial", Font.BOLD, 15));
        lBg.add(lBill);
        tSearchBill = new JTextField();
        tSearchBill.setBounds(400, 200, 140, 25);
        tSearchBill.setFont(new Font("Arial", Font.PLAIN, 14));
        lBg.add(tSearchBill);
        bSpecificBill = new JButton("Search Bill");
        bSpecificBill.setBounds(560, 200, 130, 25);
        bSpecificBill.setFont(new Font("Arial", Font.BOLD, 14));
        lBg.add(bSpecificBill);
        bSpecificBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSpecificName(evt);
            }
        });

        // button to show all records
        bAll = new JButton("All Records");
        bAll.setBounds(720, 170, 130, 30);
        bAll.setFont(new Font("Arial", Font.BOLD, 14));
        lBg.add(bAll);
        bAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // bAllActionPerformed(evt);
                showAllRecords();
            }
        });

        // table
        custModel = new DefaultTableModel();
        custModel.setColumnIdentifiers(columnNames);
        custTable = new JTable(custModel);
        custTable.setFillsViewportHeight(true);
        custTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        custTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        custTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        custTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        custTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        custTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        custTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        custTable.getColumnModel().getColumn(7).setPreferredWidth(100);
        custScroll = new JScrollPane(custTable);
        custScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        custScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        custScroll.setBounds(15, 270, 975, 370);
        lBg.add(custScroll);

        // branding
        lBranding = new JLabel("Powered By DUKANDAARI");
        lBranding.setBounds(800, 600, 200, 100);
        lBranding.setForeground(new Color(100, 100, 100));
        lBranding.setForeground(new Color(24, 39, 101));
        lBg.add(lBranding);

        // frame specifications
        fCustomer.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fCustomer.setSize(1020, 700);
        fCustomer.setLocationRelativeTo(null);
        fCustomer.setResizable(false);
        fCustomer.setVisible(true);
    }

    public void shopDetails() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
            String sql = "SELECT * FROM ShopDetails ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                shopName = rs.getString("Shop_Name");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showAllRecords() {
        custModel.setRowCount(0);
        String custName = "";
        String custPhone = "";
        String custEmail = "";
        String custAddress = "";
        String custBillNo = "";
        String custTotal = "";
        String custSeller = "";
        String custDate = "";
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
            String sql = "SELECT * FROM CustomerRecords ORDER BY Bill_No DESC";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                custName = rs.getString("Customer_Name");
                custPhone = rs.getString("Customer_Phno");
                custEmail = rs.getString("Customer_Email");
                custAddress = rs.getString("Customer_Address");
                custBillNo = rs.getString("Bill_No");
                custTotal = rs.getString("Total_Price");
                custSeller = rs.getString("Seller");
                custDate = rs.getString("Purchase_Time");
                custModel.addRow(new Object[] { custName, custPhone, custEmail, custAddress, custBillNo, custTotal,
                        custSeller, custDate });
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showSpecificName(ActionEvent ae) {
        custModel.setRowCount(0);
        String searchButton = ae.getActionCommand();
        // String textName = tSearchName.getText();
        // int textBill = Integer.parseInt(tSearchBill.getText());
        String custName = "";
        String custPhone = "";
        String custEmail = "";
        String custAddress = "";
        String custBillNo = "";
        String custTotal = "";
        String custSeller = "";
        String custDate = "";
        String sql = "";
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
            if (searchButton == "Search Name") {
                String textName = tSearchName.getText();
                sql = "SELECT * FROM CustomerRecords WHERE Customer_Name = ? ORDER BY Bill_No DESC";

                ps = con.prepareStatement(sql);
                ps.setString(1, textName);
            } else if (searchButton == "Search Bill") {
                int textBill = Integer.parseInt(tSearchBill.getText());
                sql = "SELECT * FROM CustomerRecords WHERE Bill_No = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, textBill);
                new Customer_Bill_Info(textBill);
            }
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                custName = rs.getString("Customer_Name");
                custPhone = rs.getString("Customer_Phno");
                custEmail = rs.getString("Customer_Email");
                custAddress = rs.getString("Customer_Address");
                custBillNo = rs.getString("Bill_No");
                custTotal = rs.getString("Total_Price");
                custSeller = rs.getString("Seller");
                custDate = rs.getString("Purchase_Time");
                custModel.addRow(new Object[] { custName, custPhone, custEmail, custAddress, custBillNo, custTotal,
                        custSeller, custDate });
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.WARNING_MESSAGE);
                showAllRecords();
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new Customer_Details();
    }
}