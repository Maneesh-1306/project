package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookHotel extends JFrame {
    private JPanel contentPane;
    JTextField t1, t2;
    Choice c1, c2, c3;
    JLabel l2, l3, l4, l5;

    public BookHotel(String username) {
        setBounds(420, 220, 1100, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("BOOK HOTEL");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 300, 53);
        contentPane.add(lblTitle);

        JLabel lblUsername = new JLabel("Username :");
        lblUsername.setBounds(35, 70, 200, 14);
        contentPane.add(lblUsername);

        JLabel lblUsernameVal = new JLabel(username);
        lblUsernameVal.setBounds(271, 70, 200, 14);
        contentPane.add(lblUsernameVal);

        JLabel lblSelectHotel = new JLabel("Select Hotel :");
        lblSelectHotel.setBounds(35, 110, 200, 14);
        contentPane.add(lblSelectHotel);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM hotel");
            while (rs.next()) {
                c1.add(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 110, 150, 30);
        add(c1);

        JLabel lblPersons = new JLabel("Total Persons:");
        lblPersons.setBounds(35, 150, 200, 14);
        contentPane.add(lblPersons);

        t1 = new JTextField("0");
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);

        JLabel lblDays = new JLabel("Number of Days:");
        lblDays.setBounds(35, 190, 200, 14);
        contentPane.add(lblDays);

        t2 = new JTextField("0");
        t2.setBounds(271, 190, 150, 20);
        contentPane.add(t2);

        JLabel lblAC = new JLabel("AC / Non-AC:");
        lblAC.setBounds(35, 230, 200, 14);
        contentPane.add(lblAC);

        c2 = new Choice();
        c2.add("AC");
        c2.add("Non-AC");
        c2.setBounds(271, 230, 150, 30);
        add(c2);

        JLabel lblFood = new JLabel("Food Included:");
        lblFood.setBounds(35, 270, 200, 14);
        contentPane.add(lblFood);

        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(271, 270, 150, 30);
        add(c3);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(35, 310, 200, 14);
        contentPane.add(lblId);

        l2 = new JLabel();
        l2.setBounds(271, 310, 200, 14);
        contentPane.add(l2);

        JLabel lblNumber = new JLabel("Number:");
        lblNumber.setBounds(35, 350, 200, 14);
        contentPane.add(lblNumber);

        l3 = new JLabel();
        l3.setBounds(271, 350, 200, 14);
        contentPane.add(l3);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(35, 390, 200, 14);
        contentPane.add(lblPhone);

        l4 = new JLabel();
        l4.setBounds(271, 390, 200, 14);
        contentPane.add(l4);

        JLabel lblPrice = new JLabel("Total Price:");
        lblPrice.setBounds(35, 430, 200, 14);
        contentPane.add(lblPrice);

        l5 = new JLabel();
        l5.setForeground(Color.RED);
        l5.setBounds(271, 430, 200, 14);
        contentPane.add(l5);

        // Fetch customer details
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
            if (rs.next()) {
                l2.setText(rs.getString("id"));
                l3.setText(rs.getString("number"));
                l4.setText(rs.getString("phone"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton btnPrice = new JButton("Check Price");
        btnPrice.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String hotel = c1.getSelectedItem();
                ResultSet rs = c.s.executeQuery("SELECT * FROM hotel WHERE name = '" + hotel + "'");
                if (rs.next()) {
                    int cost = Integer.parseInt(rs.getString("costperperson"));
                    int ac = Integer.parseInt(rs.getString("acroom"));
                    int food = Integer.parseInt(rs.getString("foodincluded"));

                    int persons = Integer.parseInt(t1.getText());
                    int days = Integer.parseInt(t2.getText());
                    String acSelected = c2.getSelectedItem();
                    String foodSelected = c3.getSelectedItem();

                    int total = cost;
                    if (acSelected.equals("AC")) total += ac;
                    if (foodSelected.equals("Yes")) total += food;

                    total *= persons * days;

                    l5.setText("Rs " + total);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnPrice.setBounds(50, 470, 120, 30);
        btnPrice.setBackground(Color.BLACK);
        btnPrice.setForeground(Color.WHITE);
        contentPane.add(btnPrice);

        JButton btnBook = new JButton("Book");
        btnBook.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String query = "INSERT INTO bookHotel VALUES('" +
                        username + "', '" + c1.getSelectedItem() + "', '" + t1.getText() + "', '" +
                        t2.getText() + "', '" + c2.getSelectedItem() + "', '" + c3.getSelectedItem() + "', '" +
                        l2.getText() + "', '" + l3.getText() + "', '" + l4.getText() + "', '" + l5.getText() + "')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Hotel Booked Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnBook.setBounds(200, 470, 120, 30);
        btnBook.setBackground(Color.BLACK);
        btnBook.setForeground(Color.WHITE);
        contentPane.add(btnBook);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> setVisible(false));
        btnBack.setBounds(350, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Optional: add image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/book.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        JLabel lblImage = new JLabel(new ImageIcon(i2));
        lblImage.setBounds(450, 100, 700, 300);
        contentPane.add(lblImage);

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        new BookHotel("john_doe").setVisible(true); // Use an actual username from your DB
    }
}
