package Product_Details;

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
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.net.URL;

//packages imported 
import Dashboard.Dashboard;

public class Product_Details {
    JFrame fProd;
    ImageIcon i1;
    JLabel lb, lSu, lName, lPrice, lTitle, lBranding, lShopName;
    JPanel pbg;
    JTextField tSu, tName, tPrice;
    JButton bAdd, bUpdate, bDelete, bBack;
    JTable product;
    JScrollPane prodScroll;
    DefaultTableModel model;
    String shopName = "";
    URL image = getClass().getClassLoader().getResource("Images/bg.jfif");
    URL Back_btn = getClass().getClassLoader().getResource("Images/back_btn.png");
    String[] columnNames = { "SU", "Name", "Cost" };

    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String userName = "admin";
    String password = "123123";
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    ResultSetMetaData rsd;

    public Product_Details() {
        shopDetails();
        interfaceLayout();
        tableUpdate();
    }

    public void interfaceLayout() {
        fProd = new JFrame("Product Details");

        // background image
        i1 = new ImageIcon(image);
        lb = new JLabel(i1, JLabel.CENTER);
        lb.setBounds(0, 0, 1000, 700);
        lb.setLayout(null);
        fProd.add(lb);

        // panel to cover details
        pbg = new JPanel();
        pbg.setBounds(30, 80, 400, 550);
        pbg.setBackground(new Color(0, 0, 0, 15));
        lb.add(pbg);

        // title label
        lTitle = new JLabel("Product Details");
        lTitle.setBounds(130, 130, 200, 50);
        lTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lb.add(lTitle);

        // back button
        Icon backBtn = new ImageIcon(Back_btn);
        bBack = new JButton(backBtn);
        bBack.setBounds(5, 5, 36, 36);
        bBack.setBackground(new Color(0, 187, 255));
        bBack.setBorder(null);
        lb.add(bBack);
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Dashboard();
                fProd.dispose();
            }
        });

        // shop name label
        lShopName = new JLabel(shopName, JLabel.CENTER);
        lShopName.setBounds(50, 30, 900, 25);
        lShopName.setFont(new Font("Verdana", Font.BOLD, 25));
        lShopName.setForeground(new Color(24, 39, 101));
        lb.add(lShopName);

        // product su label and text field
        lSu = new JLabel("Product SU");
        lSu.setBounds(80, 240, 150, 20);
        lSu.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(lSu);
        tSu = new JTextField();
        tSu.setBounds(200, 240, 200, 25);
        tSu.setFont(new Font("Arial", Font.PLAIN, 18));
        lb.add(tSu);

        // product name label and text field
        lName = new JLabel("Product Name");
        lName.setBounds(60, 310, 150, 20);
        lName.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(lName);
        tName = new JTextField();
        tName.setBounds(200, 310, 200, 25);
        tName.setFont(new Font("Arial", Font.PLAIN, 18));
        lb.add(tName);

        // product price label and text field
        lPrice = new JLabel("Product Price");
        lPrice.setBounds(60, 380, 150, 20);
        lPrice.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(lPrice);
        tPrice = new JTextField();
        tPrice.setBounds(200, 380, 200, 25);
        tPrice.setFont(new Font("Arial", Font.PLAIN, 18));
        lb.add(tPrice);

        // button to add, update and delete
        bAdd = new JButton("Add");
        bAdd.setBounds(50, 500, 100, 30);
        bAdd.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(bAdd);
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        bUpdate = new JButton("Update");
        bUpdate.setBounds(170, 500, 120, 30);
        bUpdate.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(bUpdate);
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });
        bDelete = new JButton("Delete");
        bDelete.setBounds(310, 500, 100, 30);
        bDelete.setFont(new Font("Arial", Font.BOLD, 18));
        lb.add(bDelete);
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        // product table
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        product = new JTable(model);
        product.getColumnModel().getColumn(0).setPreferredWidth(70);
        product.getColumnModel().getColumn(1).setPreferredWidth(200);
        product.getColumnModel().getColumn(2).setPreferredWidth(70);
        product.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        product.setGridColor(new java.awt.Color(255, 255, 255));
        product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productMouseClicked(evt);
            }
        });
        prodScroll = new JScrollPane(product);
        prodScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        prodScroll.setBounds(470, 80, 490, 550);
        lb.add(prodScroll);

        // branding
        lBranding = new JLabel("Powered By DUKANDAARI");
        lBranding.setBounds(800, 600, 200, 100);
        lBranding.setForeground(new Color(24, 39, 101));
        lBranding.setFont(new Font("Verdana", Font.BOLD, 10));
        lb.add(lBranding);

        // frame specifications
        fProd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fProd.setBounds(250, 50, 1000, 700);
        fProd.setLocationRelativeTo(null);
        fProd.setResizable(false);
        fProd.setVisible(true);
    }

    public void databaseConnect() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void shopDetails() {
        try {
            databaseConnect();
            String sql = "SELECT * FROM ShopDetails ";
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
                shopName = rs.getString("Shop_Name");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void tableUpdate() {
        databaseConnect();
        int columnCount;
        try {
            pst = con.prepareStatement("SELECT * FROM ProductRecords ORDER BY SU ASC");
            rs = pst.executeQuery();
            rsd = rs.getMetaData();
            columnCount = rsd.getColumnCount();
            DefaultTableModel temp1 = (DefaultTableModel) product.getModel();
            temp1.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= columnCount; i++) {
                    v2.add(rs.getString("SU"));
                    v2.add(rs.getString("Product_Name"));
                    v2.add(rs.getInt("Cost"));
                }
                temp1.addRow(v2);
            }
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void productMouseClicked(java.awt.event.MouseEvent evt) {
        model = (DefaultTableModel) product.getModel();
        int selectIndex = product.getSelectedRow();
        tSu.setText(model.getValueAt(selectIndex, 0).toString());
        tName.setText(model.getValueAt(selectIndex, 1).toString());
        tPrice.setText(model.getValueAt(selectIndex, 2).toString());
    }

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {
        databaseConnect();
        String pSq = tSu.getText();
        String pName = tName.getText();
        float pPrice = Float.parseFloat(tPrice.getText());
        try {
            System.out.println(pSq + pName + pPrice);
            String sql = "INSERT INTO ProductRecords (SU, Product_Name, Cost) values(?, ?, ?);";
            pst = con.prepareStatement(sql);
            pst.setString(1, pSq);
            pst.setString(2, pName);
            pst.setFloat(3, pPrice);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product Added");
            tableUpdate();
            tSu.setText("");
            tName.setText("");
            tPrice.setText("");
            tSu.requestFocus();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Product not added. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        databaseConnect();
        String pSq = tSu.getText();
        String pName = tName.getText();
        float pPrice = Float.parseFloat(tPrice.getText());
        try {
            String sql = "UPDATE ProductRecords SET Product_Name=? , Cost=? WHERE SU=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, pName);
            pst.setFloat(2, pPrice);
            pst.setString(3, pSq);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Product Updated");
            tableUpdate();
            tSu.setText("");
            tName.setText("");
            tPrice.setText("");
            tSu.requestFocus();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Product not updated. Please try later!\nNote: Product SU cannot be updated", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        databaseConnect();
        String pSq = tSu.getText();
        String pName = tName.getText();
        float pPrice = Float.parseFloat(tPrice.getText());
        try {
            String sql = "DELETE FROM ProductRecords WHERE SU=?";
            pst = con.prepareStatement(sql);
            pst.setString(1, pSq);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,
                    "Product Deleted -\nSU:" + pSq + "\nName:" + pName + "\nCost:" + pPrice);
            tableUpdate();
            tSu.setText("");
            tName.setText("");
            tPrice.setText("");
            tSu.requestFocus();
            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Product not deleted. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        new Product_Details();
    }
}