package Bill_Generations;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

import javax.swing.Icon;

//imported packages
import Dashboard.Dashboard;
import Final_Bill.Final_Bill;

public class Bill_Generations implements ActionListener {
    JFrame frame1;
    JLabel label_bg;
    String bg_img_1;
    ImageIcon image;
    JLabel heading1;
    JLabel heading2;
    JLabel name;
    JLabel phno;
    JLabel email;
    JLabel address;
    JLabel sq;
    JLabel quantity;

    JTextField tname;
    JTextField tphno;
    JTextField temail;
    JTextArea taddress;
    JTextField tsq;
    JTextField tquantity;

    JTextArea txtarea;

    JTable product;
    JScrollPane prodScroll;
    String[] columnNames = { "SQ", "Name", "Quantity", "Price" };

    // DataBase Connection
    String driverName = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/store?characterEncoding=utf8";
    String username = "admin";
    String dpassword = "123123";
    Statement stmt;
    Connection con;
    PreparedStatement pst, pst1;
    String query;
    String query1;
    String query2;
    String shopQuery;
    String shopName;
    JLabel label_shopName;
    JLabel branding;
    ResultSet rs;
    String seller;
    int billno;

    JButton bBack;
    JButton back;
    JButton add;
    JButton print;
    JButton delete;
    String uname;
    String uphno;
    String uemail;
    String uaddress;
    String psq;
    int pquantity;
    int pquant;
    String pname;
    int pcost;
    int columnCount;

    int i, rno = 0;
    int uphno_len;
    JPanel box;

    public void databaseconnect() {
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, username, dpassword);
            stmt = con.createStatement();
            System.out.println("DataBase Connected");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to database. Please try later!", "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    public void seller() {
        try {

            query = "select r.Name from Registration r, Login l where r.Email = l.UID ORDER BY l.SNO DESC LIMIT 1";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                seller = rs.getString("r.Name");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public Bill_Generations() {
        // Frame for Window
        frame1 = new JFrame("Bill Generation");
        frame1.setBounds(250, 50, 1000, 700);

        // Background Image
        URL bg_img_1 = getClass().getClassLoader().getResource("Images/bg.jfif");
        image = new ImageIcon(bg_img_1);
        label_bg = new JLabel(image, JLabel.CENTER);

        frame1.add(label_bg);

        databaseconnect();
        seller();
        Form();

        // Box
        box = new JPanel();
        box.setBackground(new Color(0, 0, 0, 15));
        box.setBounds(30, 60, 430, 575);
        label_bg.add(box);

        frame1.setVisible(true);

        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);

    }

    public void Form() {
        // Heading 1
        heading1 = new JLabel("Enter Customer Details");
        heading1.setFont(new Font("Arial", Font.BOLD, 25));
        heading1.setBounds(100, 70, 300, 30);
        label_bg.add(heading1);

        // Customer Name
        name = new JLabel("Customer Name");
        name.setFont(new Font("Arial", Font.BOLD, 18));
        name.setBounds(60, 125, 180, 20);
        label_bg.add(name);

        // Customer Name TextField
        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 17));
        tname.setBounds(220, 125, 205, 20);
        label_bg.add(tname);

        // Customer Number
        phno = new JLabel("Mobile Number");
        phno.setFont(new Font("Arial", Font.BOLD, 18));
        phno.setBounds(60, 165, 180, 20);
        label_bg.add(phno);

        // Customer Mobile Number TextField
        tphno = new JTextField();
        tphno.setFont(new Font("Arial", Font.PLAIN, 17));
        tphno.setBounds(220, 165, 205, 20);
        label_bg.add(tphno);

        // Email
        email = new JLabel("E-mail");
        email.setFont(new Font("Arial", Font.BOLD, 18));
        email.setBounds(60, 205, 80, 20);
        label_bg.add(email);

        // Customer Mobile Number TextField
        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 17));
        temail.setBounds(220, 205, 205, 20);
        label_bg.add(temail);

        // Customer Address
        address = new JLabel("Address");
        address.setFont(new Font("Arial", Font.BOLD, 18));
        address.setBounds(60, 245, 180, 20);
        label_bg.add(address);

        // Customer Address TextArea
        taddress = new JTextArea();
        taddress.setFont(new Font("Arial", Font.PLAIN, 17));
        taddress.setBounds(220, 245, 205, 51);
        label_bg.add(taddress);

        // Heading 2
        heading2 = new JLabel("Enter Product Details");
        heading2.setFont(new Font("Arial", Font.BOLD, 25));
        heading2.setBounds(110, 350, 300, 30);
        label_bg.add(heading2);

        // SQ Code
        sq = new JLabel("SQ Code");
        sq.setFont(new Font("Arial", Font.BOLD, 18));
        sq.setBounds(60, 420, 180, 20);
        label_bg.add(sq);

        // SQ Code TextField
        tsq = new JTextField();
        tsq.setFont(new Font("Arial", Font.PLAIN, 17));
        tsq.setBounds(220, 420, 205, 20);
        label_bg.add(tsq);

        // Quantity
        quantity = new JLabel("Quantity");
        quantity.setFont(new Font("Arial", Font.BOLD, 18));
        quantity.setBounds(60, 460, 180, 20);
        label_bg.add(quantity);

        // Quantity Code TextField
        tquantity = new JTextField();
        tquantity.setFont(new Font("Arial", Font.PLAIN, 17));
        tquantity.setBounds(220, 460, 205, 20);
        label_bg.add(tquantity);

        // TextArea**********
        txtarea = new JTextArea();
        txtarea.setBounds(500, 60, 450, 575);
        txtarea.setFont(new Font("Arial", Font.BOLD, 20));
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0, 25), 2);
        txtarea.setBorder(border);
        txtarea.setEditable(false);

        // Txtarea content
        String data = "\n    Name       : \n    Mobile     : \n    Email       : ";
        String data3 = "\n    Address  : ";
        txtarea.setText(data + data3);

        // Branding
        branding = new JLabel("Powered By DUKANDAARI");
        branding.setBounds(800, 600, 200, 100);
        branding.setForeground(new Color(24, 39, 101));
        label_bg.add(branding);

        // Product Table
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        product = new JTable(model);
        product.getColumnModel().getColumn(0).setPreferredWidth(100);
        product.getColumnModel().getColumn(1).setPreferredWidth(200);
        product.getColumnModel().getColumn(2).setPreferredWidth(100);
        product.getColumnModel().getColumn(3).setPreferredWidth(100);
        product.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        product.setGridColor(new java.awt.Color(0, 0, 0));
        product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productMouseClicked(evt);
            }
        });
        prodScroll = new JScrollPane(product);
        prodScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        prodScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        prodScroll.setBounds(25, 130, 400, 420);
        txtarea.add(prodScroll);

        label_bg.add(txtarea);

        shop_details();
        Buttons();
    }

    public void shop_details() {
        try {

            shopQuery = "SELECT Shop_name from ShopDetails";
            rs = stmt.executeQuery(shopQuery);
            while (rs.next()) {
                shopName = rs.getString("Shop_name");
            }

            label_shopName = new JLabel(shopName, JLabel.CENTER);
            label_shopName.setFont(new Font("Arial", Font.BOLD, 35));
            label_shopName.setBounds(0, 10, 1000, 40);
            label_shopName.setForeground(new Color(24, 39, 101));

            label_bg.add(label_shopName);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void productMouseClicked(java.awt.event.MouseEvent evt) {
        DefaultTableModel temp2 = (DefaultTableModel) product.getModel();
        int selectIndex = product.getSelectedRow();
        tsq.setText(temp2.getValueAt(selectIndex, 0).toString());
        tquantity.setText(temp2.getValueAt(selectIndex, 2).toString());
        
    }

    public void Buttons() {
        add = new JButton("ADD");
        add.setBounds(60, 500, 150, 50);
        add.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(add);
        add.addActionListener(this);

        delete = new JButton("Delete");
        delete.setBounds(280, 500, 145, 50);
        delete.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(delete);
        delete.addActionListener(this);

        print = new JButton("Receipt");
        print.setBounds(60, 580, 365, 30);
        print.setFont(new Font("Arial", Font.BOLD, 20));
        label_bg.add(print);
        print.addActionListener(this);
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                new Final_Bill();
               
            }
        });

        // back button
        URL Back_btn = getClass().getClassLoader().getResource("Images/back_btn.png");
        Icon backBtn = new ImageIcon(Back_btn);
        bBack = new JButton(backBtn);
        bBack.setBounds(5, 5, 36, 36);
        bBack.setBackground(new Color(0, 187, 255));
        bBack.setBorder(null);
        label_bg.add(bBack);
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Dashboard();
                frame1.dispose();
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        uname = tname.getText();
        uemail = temail.getText();
        uphno = tphno.getText();
        uaddress = taddress.getText();
        psq = tsq.getText();
        pquantity = Integer.parseInt(tquantity.getText());
        uphno_len = uphno.length();

        if (uname.isEmpty()) {
            JOptionPane.showMessageDialog(label_bg, "Enter Customer Name", "Warning", JOptionPane.ERROR_MESSAGE);
        } else if (uphno_len != 10) {
            JOptionPane.showMessageDialog(label_bg, "Enter a valid Mobile number", "Warning",
                    JOptionPane.ERROR_MESSAGE);
        } else if (e.getSource() == add) {

            try {

                if (rno == 0) {

                    String data = "\n    Name       : " + uname + "\n    Mobile     : " + uphno + "\n    Email       : "
                            + uemail;
                    String data3 = "\n    Address  : " + uaddress;
                    txtarea.setText(data + data3);

                    query = "Insert into CustomerRecords(Customer_Name,Customer_Phno,Customer_Email,Customer_Address,Seller,Total_Price) values ('"
                            + uname + "','" + uphno + "','" + uemail + "','" + uaddress + "','" + seller + "','" + 0
                            + "')";
                    pst1 = con.prepareStatement(query);
                    pst1.executeUpdate();
                    rno++;
                }

                query1 = "select c.Bill_No , p.Product_Name,p.Cost from CustomerRecords c , ProductRecords p where p.SU='"
                        + psq + "'order by c.Bill_No desc limit 1";
                pst = con.prepareStatement(query1);
                rs = pst.executeQuery();
                while (rs.next()) {
                    pname = rs.getString("p.Product_Name");
                    pcost = rs.getInt("p.Cost");
                    billno = rs.getInt("c.Bill_no");

                }

                query2 = "insert into Bill(Bill_No,SU, Product_Name,QTY, Cost) select c.Bill_No, p.SU,'" + pname + "','"
                        + pquantity + "','" + pcost + "' from CustomerRecords c, ProductRecords p where p.SU = '" + psq
                        + "' and c.Bill_no ='" + billno + "';";
                pst = con.prepareStatement(query2);
                i=pst.executeUpdate();

                if(i!=0)
                {
                    tableUpdate();
                    tsq.setText("");
                    tquantity.setText("");
                }
                else
                    JOptionPane.showMessageDialog(label_bg,"Product Not Found","Warning",JOptionPane.ERROR_MESSAGE);
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(label_bg, ex.toString(), "Warning", JOptionPane.ERROR_MESSAGE);

            }
        } else if (e.getSource() == delete) {
            try {
                psq = tsq.getText();
                pquantity = Integer.parseInt(tquantity.getText());

                pst = con.prepareStatement(
                        "delete from Bill where SU='" + psq + "'and QTY='" + pquantity + "' and Bill_No=" + billno);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product Deleted -\nSQ:" + psq);

                tableUpdate();
                tsq.setText("");
                tquantity.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(label_bg, ex.toString(), "Warning", JOptionPane.ERROR_MESSAGE);
                

            }
        }

    }

    public void tableUpdate() {
        try {
            pst = con.prepareStatement("select * from Bill where  Bill_No=" + billno);

            rs = pst.executeQuery();
            ResultSetMetaData rsd = rs.getMetaData();
            columnCount = rsd.getColumnCount();
            DefaultTableModel temp1 = (DefaultTableModel) product.getModel();
            temp1.setRowCount(0);
            while (rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= columnCount; i++) {
                    v2.add(rs.getString("SU"));
                    v2.add(rs.getString("Product_Name"));

                    v2.add(rs.getInt("QTY"));
                    pquant = rs.getInt("QTY");
                    v2.add(pquant * rs.getInt("Cost"));
                }
                temp1.addRow(v2);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
            

        }
        
    }

    public static void main(String args[]) {
        new Bill_Generations();
    }

}
