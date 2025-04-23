package travel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteCustomer extends JFrame {
    private JPanel contentPane;
    Choice c1;
    JLabel l2, l3, l4, l5, l6, l7, l8, l9;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DeleteCustomer frame = new DeleteCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DeleteCustomer() throws SQLException {
        setBounds(580, 220, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("travel/management/system/icons/delete.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(500, 100, 300, 300);
        contentPane.add(l1);

        JLabel lblTitle = new JLabel("DELETE CUSTOMER DETAILS");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 300, 53);
        contentPane.add(lblTitle);

        JLabel lbUsername = new JLabel("Username :");
        lbUsername.setBounds(35, 70, 200, 14);
        contentPane.add(lbUsername);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("username"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 70, 150, 30);
        contentPane.add(c1);

        String[] labels = {"ID :", "Number :", "Name :", "Gender :", "Country :", "Permanent Address :", "Phone :", "Email :"};
        JLabel[] dataLabels = new JLabel[8];
        int y = 110;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(35, y, 200, 14);
            contentPane.add(label);

            dataLabels[i] = new JLabel();
            dataLabels[i].setBounds(271, y, 200, 14);
            contentPane.add(dataLabels[i]);

            y += 40;
        }

        l2 = dataLabels[0];
        l3 = dataLabels[1];
        l4 = dataLabels[2];
        l5 = dataLabels[3];
        l6 = dataLabels[4];
        l7 = dataLabels[5];
        l8 = dataLabels[6];
        l9 = dataLabels[7];

        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(e -> {
            Conn c = new Conn();
            String username = c1.getSelectedItem();
            try {
                ResultSet rs = c.s.executeQuery("select * from customer where username = '" + username + "'");
                if (rs.next()) {
                    l2.setText(rs.getString("id"));
                    l3.setText(rs.getString("number"));
                    l4.setText(rs.getString("name"));
                    l5.setText(rs.getString("gender"));
                    l6.setText(rs.getString("country"));
                    l7.setText(rs.getString("address"));
                    l8.setText(rs.getString("phone"));
                    l9.setText(rs.getString("email"));
                }
                rs.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnCheck.setBounds(425, 70, 80, 22);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            Conn c = new Conn();
            String username = c1.getSelectedItem();
            try {
                String q1 = "delete from customer where username = '" + username + "'";
                c.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Customer Detail Deleted Successfully");
                setVisible(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Deleting Customer: " + ex.getMessage());
            }
        });
        btnDelete.setBounds(100, 430, 120, 30);
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        contentPane.add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> setVisible(false));
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        getContentPane().setBackground(Color.WHITE);
    }
}
