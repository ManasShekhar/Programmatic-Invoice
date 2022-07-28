package Final_Bill;

import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Final_Bill {

    JFrame frame1;
    String bg_img_1;
    ImageIcon image;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;

    JLabel label_bg;

    // DataBase Connection
    String driverName = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String username = "admin";
    String dpassword = "123123";
    Statement stmt;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    String billQuery;
    String totalQuery;
    String shopQuery;

    // Shop Details
    String ownerName;
    String shopName;
    String address;
    String shopPhno;
    String shopEmail;
    JLabel label_ownerName;
    JLabel label_shopName;
    JLabel label_address;
    JLabel label_shopPhno;
    JLabel label_shopEmail;

    // Customer Details
    String customerQuery;
    String customerName;
    String customerPhno;
    String seller;
    String billno;
    String datetime;
    JLabel label_Billno;
    JLabel label_datetime;
    JLabel label_customerName;
    JLabel label_customerphno;
    JLabel label_seller;

    // Bill
    DefaultTableModel model;
    JTable product;
    JScrollPane prodScroll;
    String[] columnNames = { "SQ", "Name", "Quantity", "Unit Price", "Total" };
    JLabel total;
    int columnCount;
    int pquant;
    Vector v;
    float totalcost = 0;

    public void databaseconnect() {
        try {
            con = DriverManager.getConnection(url, username, dpassword);
            stmt = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void shop_details() {
        try {
            databaseconnect();
            shopQuery = "SELECT * from ShopDetails";
            rs = stmt.executeQuery(shopQuery);
            while (rs.next()) {
                ownerName = rs.getString("Owner_Name");
                shopName = rs.getString("Shop_name");
                address = rs.getString("Shop_Address");
                shopPhno = rs.getString("Phone_Number");
                shopEmail = rs.getString("Email");
            }

            label_shopName = new JLabel(shopName, JLabel.CENTER);
            label_shopName.setFont(new Font("Arial", Font.BOLD, 30));
            label_shopName.setBounds(0, 10, 485, 40);
            label_bg.add(label_shopName);

            label_ownerName = new JLabel("Owner -" + ownerName, JLabel.LEFT);
            label_ownerName.setFont(new Font("Arial", Font.BOLD, 18));
            label_ownerName.setBounds(10, 55, 300, 20);
            label_bg.add(label_ownerName);

            label_shopPhno = new JLabel("Phno.- " + shopPhno, JLabel.RIGHT);
            label_shopPhno.setFont(new Font("Arial", Font.BOLD, 18));
            label_shopPhno.setBounds(270, 55, 200, 20);
            label_bg.add(label_shopPhno);

            label_address = new JLabel(address, JLabel.CENTER);
            label_address.setFont(new Font("Arial", Font.BOLD, 10));
            label_address.setBounds(0, 80, 500, 30);
            label_bg.add(label_address);

            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void customer_details() {
        try {
            databaseconnect();
            customerQuery = "SELECT Customer_Name,Customer_Phno,Seller,Purchase_Time,Bill_No from CustomerRecords order by Bill_No desc limit 1";
            rs = stmt.executeQuery(customerQuery);
            while (rs.next()) {
                customerName = rs.getString("Customer_Name");
                customerPhno = rs.getString("Customer_Phno");
                seller = rs.getString("Seller");
                billno = rs.getString("Bill_No");
                datetime = rs.getString("Purchase_Time");
            }

            label_Billno = new JLabel("Bill no-" + billno);
            label_Billno.setFont(new Font("Arial", Font.BOLD, 15));
            label_Billno.setBounds(30, 180, 150, 20);
            label_bg.add(label_Billno);

            label_datetime = new JLabel(datetime);
            label_datetime.setFont(new Font("Arial", Font.BOLD, 15));
            label_datetime.setBounds(300, 180, 200, 20);
            label_bg.add(label_datetime);

            label_customerName = new JLabel("Customer Name - " + customerName);
            label_customerName.setFont(new Font("Arial", Font.BOLD, 20));
            label_customerName.setBounds(30, 210, 400, 20);
            label_bg.add(label_customerName);

            label_customerphno = new JLabel("Customer Phno  - " + customerPhno);
            label_customerphno.setFont(new Font("Arial", Font.BOLD, 20));
            label_customerphno.setBounds(30, 240, 400, 20);
            label_bg.add(label_customerphno);

            label_seller = new JLabel("Seller -" + seller);
            label_seller.setFont(new Font("Arial", Font.BOLD, 20));
            label_seller.setBounds(30, 270, 400, 20);
            label_bg.add(label_seller);

            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void bill() {
        try {
            databaseconnect();
            model = new DefaultTableModel();
            model.setColumnIdentifiers(columnNames);
            product = new JTable(model);
            product.getColumnModel().getColumn(0).setPreferredWidth(100);
            product.getColumnModel().getColumn(1).setPreferredWidth(200);
            product.getColumnModel().getColumn(2).setPreferredWidth(100);
            product.getColumnModel().getColumn(3).setPreferredWidth(100);
            product.getColumnModel().getColumn(4).setPreferredWidth(100);
            product.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            product.setGridColor(new java.awt.Color(0, 0, 0));

            prodScroll = new JScrollPane(product);
            prodScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            prodScroll.setBounds(30, 320, 425, 300);
            label_bg.add(prodScroll);

            billQuery = "select p.Product_Name , p.Cost , b.SU, b.QTY from ProductRecords p , Bill b where b.Bill_No=? AND b.SU=p.SU";
            // where p.Bill_No = b.Bill_No ";
            pst = con.prepareStatement(billQuery);
            pst.setString(1, billno);
            rs = pst.executeQuery();
            // rs = stmt.executeQuery(billQuery);
            ResultSetMetaData rsd = rs.getMetaData();
            columnCount = rsd.getColumnCount();
            DefaultTableModel temp1 = (DefaultTableModel) product.getModel();
            temp1.setRowCount(0);
            while (rs.next()) {
                v = new Vector();
                for (int i = 1; i <= columnCount; i++) {
                    v.add(rs.getString("b.SU"));
                    v.add(rs.getString("p.Product_Name"));
                    pquant = rs.getInt("b.QTY");
                    v.add(pquant);
                    v.add(rs.getFloat("p.Cost"));
                    v.add(pquant * rs.getFloat("p.Cost"));

                    totalcost += pquant * rs.getFloat("p.Cost");
                    // System.out.println(totalcost);

                }
                temp1.addRow(v);

            }

            totalcost /= 4;
            total = new JLabel("Grand Total - ₹" + totalcost + "/-");
            total.setFont(new Font("Arial", Font.BOLD, 20));
            total.setBounds(230, 630, 400, 20);
            label_bg.add(total);

            con.close();
            grand_total();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void grand_total() {
        try {
            databaseconnect();
            totalQuery = "UPDATE CustomerRecords SET Total_Price = ? WHERE Bill_No=?";
            pst = con.prepareStatement(totalQuery);
            pst.setFloat(1, totalcost);
            pst.setString(2, billno);
            pst.executeUpdate();
            // stmt.executeUpdate(totalQuery);
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public Final_Bill() {
        frame1 = new JFrame("RECEIPT");
        frame1.setBounds(520, 20, 500, 800);

        // Background Image
        URL bg_img_1 = getClass().getClassLoader().getResource("Images/Bill_bg.jpg");
        image = new ImageIcon(bg_img_1);
        label_bg = new JLabel(image);
        label_bg.setBackground(Color.red);
        frame1.add(label_bg);

        // databaseconnect();
        shop_details();

        label1 = new JLabel("**************************************************");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        label1.setBounds(40, 115, 400, 20);
        label_bg.add(label1);

        label2 = new JLabel("RECEIPT");
        label2.setFont(new Font("Arial", Font.BOLD, 25));
        label2.setBounds(180, 130, 200, 30);
        label_bg.add(label2);

        label3 = new JLabel("**************************************************");
        label3.setFont(new Font("Arial", Font.BOLD, 20));
        label3.setBounds(40, 160, 400, 20);
        label_bg.add(label3);

        customer_details();
        bill();

        label4 = new JLabel("**************************************************");
        label4.setFont(new Font("Arial", Font.BOLD, 20));
        label4.setBounds(40, 685, 400, 20);
        label_bg.add(label4);

        label5 = new JLabel("Thank You For Shopping!");
        label5.setFont(new Font("Arial", Font.BOLD, 25));
        label5.setBounds(85, 710, 400, 25);
        label_bg.add(label5);

        // Frame Components
        frame1.setVisible(true);
        // frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        frame1.setResizable(false);
    }

    public static void main(String args[]) {
        new Final_Bill();
    }
}